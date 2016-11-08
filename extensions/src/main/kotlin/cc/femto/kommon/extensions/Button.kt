package cc.femto.kommon.extensions

import android.graphics.drawable.Drawable
import android.widget.Button

var Button.compoundDrawableStart: Drawable?
    get() = compoundDrawablesRelative[0]
    set(value) = setCompoundDrawablesRelativeWithIntrinsicBounds(value,
            compoundDrawablesRelative[1],
            compoundDrawablesRelative[2],
            compoundDrawablesRelative[3])

var Button.compoundDrawableTop: Drawable?
    get() = compoundDrawablesRelative[1]
    set(value) = setCompoundDrawablesRelativeWithIntrinsicBounds(compoundDrawablesRelative[0],
            value,
            compoundDrawablesRelative[2],
            compoundDrawablesRelative[3])

var Button.compoundDrawableEnd: Drawable?
    get() = compoundDrawablesRelative[2]
    set(value) = setCompoundDrawablesRelativeWithIntrinsicBounds(compoundDrawablesRelative[0],
            compoundDrawablesRelative[1],
            value,
            compoundDrawablesRelative[3])

var Button.compoundDrawableBottom: Drawable?
    get() = compoundDrawablesRelative[3]
    set(value) = setCompoundDrawablesRelativeWithIntrinsicBounds(compoundDrawablesRelative[0],
            compoundDrawablesRelative[1],
            compoundDrawablesRelative[2],
            value)
