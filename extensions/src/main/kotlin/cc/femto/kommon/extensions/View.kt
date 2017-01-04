package cc.femto.kommon.extensions

import android.content.Context
import android.graphics.Rect
import android.support.annotation.ColorRes
import android.support.annotation.IdRes
import android.support.annotation.MenuRes
import android.support.design.internal.NavigationMenu
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.SupportMenuInflater
import android.support.v7.view.menu.MenuBuilder
import android.support.v7.view.menu.MenuPopupHelper
import android.view.*

val View.ctx: Context
    get() = context

val View.activity: AppCompatActivity
    get() = context as AppCompatActivity

val View.window: Window
    get() = activity.window

fun View.hideKeyboard() {
    activity.hideKeyboard()
}

fun View.snack(msg: CharSequence, @ColorRes colorResId: Int? = null,
               duration: Int = Snackbar.LENGTH_SHORT, build: (Snackbar.() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, msg, duration)
    colorResId?.let { snackbar.view.setBackgroundColor(color(colorResId)) }
    build?.let { snackbar.build() }
    snackbar.show()
}

inline fun <T : View> T.globalLayout(crossinline body: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            body()
        }
    })
}

inline fun <T : View> T.layoutChange(crossinline body: T.() -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
            removeOnLayoutChangeListener(this)
            body()
        }
    })
}

inline fun <T : View> T.preDraw(proceedDrawingPass: Boolean = true, crossinline body: T.() -> Unit) {
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            viewTreeObserver.removeOnPreDrawListener(this)
            body()
            return proceedDrawingPass
        }
    })
}

fun View.popup(@MenuRes menuRes: Int, gravity: Int = Gravity.END, showIcons: Boolean = true, callback: (@IdRes Int) -> Unit) {
    val menu = NavigationMenu(context)
    val menuCallback = object : MenuBuilder.Callback {
        override fun onMenuItemSelected(menu: MenuBuilder?, item: MenuItem?): Boolean {
            callback.invoke(item?.itemId ?: -1)
            return true
        }

        override fun onMenuModeChange(menu: MenuBuilder?) {
        }
    }
    menu.setCallback(menuCallback)
    SupportMenuInflater(context).inflate(menuRes, menu)
    val popupHelper = MenuPopupHelper(context, menu, this)
    popupHelper.gravity = gravity
    popupHelper.setForceShowIcon(showIcons)
    popupHelper.show()
}

fun View.intersectsWith(other: View): Boolean {
    val view1Loc = intArrayOf(0, 0)
    getLocationOnScreen(view1Loc)
    val view1Rect = Rect(view1Loc[0],
            view1Loc[1],
            view1Loc[0] + width,
            view1Loc[1] + height)
    val view2Loc = intArrayOf(0, 0)
    other.getLocationOnScreen(view2Loc)
    val view2Rect = Rect(view2Loc[0],
            view2Loc[1],
            view2Loc[0] + other.width,
            view2Loc[1] + other.height)
    return view1Rect.intersect(view2Rect)
}
