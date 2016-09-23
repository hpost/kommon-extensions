package cc.femto.kommon.extensions

import retrofit2.adapter.rxjava.Result
import rx.Observable

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
