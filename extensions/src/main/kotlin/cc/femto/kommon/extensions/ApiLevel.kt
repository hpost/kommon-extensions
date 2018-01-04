package cc.femto.kommon.extensions

import android.annotation.TargetApi
import android.os.Build

@TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
inline fun api22(block: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
        block()
    }
}

@TargetApi(Build.VERSION_CODES.M)
inline fun api23(block: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        block()
    }
}

@TargetApi(Build.VERSION_CODES.N)
inline fun api24(block: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        block()
    }
}

@TargetApi(Build.VERSION_CODES.N_MR1)
inline fun api25(block: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
        block()
    }
}

@TargetApi(Build.VERSION_CODES.O)
inline fun api26(block: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        block()
    }
}
