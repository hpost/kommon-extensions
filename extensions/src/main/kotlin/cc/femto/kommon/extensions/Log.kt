package cc.femto.kommon.extensions

import timber.log.Timber

inline fun Any.verbose(msg: String, t: Throwable? = null) = Timber.v(t, msg)

inline fun Any.debug(msg: String, t: Throwable? = null) = Timber.d(t, msg)

inline fun Any.info(msg: String, t: Throwable? = null) = Timber.i(t, msg)

inline fun Any.warning(msg: String, t: Throwable? = null) = Timber.w(t, msg)

inline fun Any.error(msg: String, t: Throwable? = null) = Timber.e(t, msg)

inline fun Any.wtf(msg: String, t: Throwable? = null) = Timber.wtf(t, msg)
