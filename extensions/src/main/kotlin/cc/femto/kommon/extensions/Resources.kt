package cc.femto.kommon.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import androidx.annotation.BoolRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import cc.femto.kommon.Kommon

fun Context.dip(value: Int): Int = (value * (resources.displayMetrics.density)).toInt()
fun Context.dimen(@DimenRes resourceId: Int): Float = resources.getDimension(resourceId)
fun Context.integer(@IntegerRes resourceId: Int): Int = resources.getInteger(resourceId)
fun Context.bool(@BoolRes resourceId: Int): Boolean = resources.getBoolean(resourceId)
fun Context.color(@ColorRes resourceId: Int): Int = ContextCompat.getColor(this, resourceId)
fun Context.colorStateList(@ColorRes resourceId: Int): ColorStateList? = ContextCompat.getColorStateList(this, resourceId)
fun Context.drawable(@DrawableRes resourceId: Int): Drawable? = ContextCompat.getDrawable(this, resourceId)
fun Context.drawable(@DrawableRes resourceId: Int, tintColorResId: Int): Drawable? =
        ContextCompat.getDrawable(this, resourceId)?.apply {
            setTint(color(tintColorResId))
        }

fun Context.string(@StringRes resourceId: Int): String = resources.getString(resourceId)
fun Context.string(@StringRes resourceId: Int, vararg args: Any?): String = resources.getString(resourceId, *args)
fun Context.quantityString(@PluralsRes resourceId: Int, quantity: Int): String = resources.getQuantityString(resourceId, quantity, quantity)

fun View.dip(value: Int): Int = (value * (resources.displayMetrics.density)).toInt()
fun View.dimen(@DimenRes resourceId: Int): Float = resources.getDimension(resourceId)
fun View.integer(@IntegerRes resourceId: Int): Int = resources.getInteger(resourceId)
fun View.bool(@BoolRes resourceId: Int): Boolean = resources.getBoolean(resourceId)
fun View.color(@ColorRes resourceId: Int): Int = ContextCompat.getColor(ctx, resourceId)
fun View.colorStateList(@ColorRes resourceId: Int): ColorStateList? = ContextCompat.getColorStateList(ctx, resourceId)
fun View.drawable(@DrawableRes resourceId: Int): Drawable? = ContextCompat.getDrawable(ctx, resourceId)
fun View.drawable(@DrawableRes resourceId: Int, tintColorResId: Int): Drawable? =
        ContextCompat.getDrawable(ctx, resourceId)?.apply {
            setTint(color(tintColorResId))
        }

fun View.string(@StringRes resourceId: Int): String = resources.getString(resourceId)
fun View.string(@StringRes resourceId: Int, vararg args: Any?): String = resources.getString(resourceId, *args)
fun View.quantityString(@PluralsRes resourceId: Int, quantity: Int, vararg args: Any?): String = resources.getQuantityString(resourceId, quantity, quantity, *args)

fun dip(value: Int): Int = (value * (Kommon.ctx.resources.displayMetrics.density)).toInt()
fun dimen(@DimenRes resourceId: Int): Float = Kommon.ctx.resources.getDimension(resourceId)
fun integer(@IntegerRes resourceId: Int): Int = Kommon.ctx.resources.getInteger(resourceId)
fun bool(@BoolRes resourceId: Int): Boolean = Kommon.ctx.resources.getBoolean(resourceId)
fun color(@ColorRes resourceId: Int): Int = ContextCompat.getColor(Kommon.ctx, resourceId)
fun colorStateList(@ColorRes resourceId: Int): ColorStateList? = ContextCompat.getColorStateList(Kommon.ctx, resourceId)
fun drawable(@DrawableRes resourceId: Int): Drawable? = ContextCompat.getDrawable(Kommon.ctx, resourceId)
fun drawable(@DrawableRes resourceId: Int, tintColorResId: Int): Drawable? =
        ContextCompat.getDrawable(Kommon.ctx, resourceId)?.apply {
            setTint(color(tintColorResId))
        }

fun string(@StringRes resourceId: Int): String = Kommon.ctx.resources.getString(resourceId)
fun string(@StringRes resourceId: Int, vararg args: Any?): String = Kommon.ctx.resources.getString(resourceId, *args)
fun quantityString(@PluralsRes resourceId: Int, quantity: Int): String = Kommon.ctx.resources.getQuantityString(resourceId, quantity, quantity)

val toolbarHeight: Int by lazy {
    val value = TypedValue()
    Kommon.ctx.theme.resolveAttribute(android.R.attr.actionBarSize, value, true)
    TypedValue.complexToDimensionPixelSize(value.data, Kommon.ctx.resources.displayMetrics)
}
