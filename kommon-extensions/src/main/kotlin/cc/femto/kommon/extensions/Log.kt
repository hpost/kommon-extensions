package cc.femto.kommon.extensions

import timber.log.Timber

inline fun Any.v(msg: String, t: Throwable? = null) = Timber.v(t, msg)

inline fun Any.i(msg: String, t: Throwable? = null) = Timber.i(t, msg)

inline fun Any.w(msg: String, t: Throwable? = null) = Timber.w(t, msg)

inline fun Any.e(msg: String, t: Throwable? = null) = Timber.e(t, msg)

inline fun Any.wtf(msg: String, t: Throwable? = null) = Timber.wtf(t, msg)
