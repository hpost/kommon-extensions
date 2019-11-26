package cc.femto.kommon.extensions

import io.reactivex.Observable
import io.reactivex.rxkotlin.zipWith
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
                    info("Retrying in ${2 pow attempt}s (${throwable.message})")
                else
                    debug("Failing after $maxAttempts maxAttempts (${throwable.message})")
            }
            .flatMap { (throwable, attempt) ->
                if (attempt == maxAttempts)
                    Observable.error(throwable)
                else
                    Observable.timer((2 pow attempt).toLong(), TimeUnit.SECONDS)
            }
}
