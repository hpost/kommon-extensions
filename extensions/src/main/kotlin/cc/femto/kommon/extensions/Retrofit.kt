package cc.femto.kommon.extensions

import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import java.io.IOException


val <T> Result<T>.isSuccess: Boolean
    get() = !isError && response().isSuccessful

/**
 * Multiplex the result Observable into success and error Observables.
 * Note that both the returned success and error Observables
 * will need a subscriptions to start the stream.
 *
 * @return An Observable pair which filters the source for success and error results
 */
fun <S, T : Result<S>> Observable<T>.multiplexResult(): Pair<Observable<T>, Observable<T>> {
    val result = this.publish().autoConnect(2)
    return result.filter { it.isSuccess } to result.filter { !it.isSuccess }
}

/**
 * Resubscribe to Retrofit request that failed with network errors
 * up to `maxAttempts` times with exponential backoff.
 *
 * @param maxAttempts Maximum number of retries
 * @return Observable with retry logic that wraps the error in a Retrofit [Result]
 */
fun <T> Observable<Result<T>>.retryOnNetworkError(maxAttempts: Int = 3): Observable<Result<T>> =
        doOnNext { if (it.isError) throw it.error() ?: IOException() }
                .retryAfterTimeout(maxAttempts)
                .onErrorReturn { Result.error(it) }
