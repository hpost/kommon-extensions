package cc.femto.kommon.extensions

infix fun Int.pow(exponent: Int) = Math.pow(this.toDouble(), exponent.toDouble()).toInt()
