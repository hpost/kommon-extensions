package cc.femto.kommon.extensions

import io.reactivex.Observable
import io.reactivex.rxkotlin.zipWith
import retrofit2.adapter.rxjava2.Result
import java.util.concurrent.TimeUnit

/**
 * Resubscribe to source up to `maxAttempts` times with exponential backoff
 * @param maxAttempts Maximum number of retries
 * @return Observable with retry logic
 */
fun <T> Observable<T>.retryAfterTimeout(maxAttempts: Int = 3): Observable<T> = retryWhen { error ->
    error.zipWith(Observable.range(0, maxAttempts + 1))
            .doOnNext { (throwable, attempt) ->
                if (attempt < maxAttempts)
                    i("Retrying in ${2 pow attempt}s (${throwable.message})")
                else
                    d("Failing after $maxAttempts maxAttempts (${throwable.message})")
            }
            .flatMap { (throwable, attempt) ->
                if (attempt == maxAttempts)
                    Observable.error(throwable)
                else
                    Observable.timer((2 pow attempt).toLong(), TimeUnit.SECONDS)
            }
}

/**
 * Resubscribe to Retrofit request up to `maxAttempts` times with exponential backoff.
 * Converts the erroneous result to an exception which can be handled by RxJava's retry operators.
 * Wraps the final exception in a result object.
 * @param maxAttempts Maximum number of retries
 * @return Observable with retry logic that wraps the error in a Retrofit [Result]
 */
fun <T> Observable<Result<T>>.retryAfterErrorResult(maxAttempts: Int = 3): Observable<Result<T>> =
        doOnNext { if (it.isError || !it.response().isSuccessful) throw RuntimeException(it.error()) }
                .retryAfterTimeout(maxAttempts)
                .onErrorReturn { throwable -> Result.error(throwable) }
