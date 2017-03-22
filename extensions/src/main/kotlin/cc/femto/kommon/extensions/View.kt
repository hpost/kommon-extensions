package cc.femto.kommon.extensions

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.support.annotation.ColorRes
import android.support.annotation.MenuRes
import android.support.design.internal.NavigationMenu
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.SupportMenuInflater
import android.support.v7.view.menu.MenuBuilder
import android.support.v7.view.menu.MenuPopupHelper
import android.transition.Transition
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import cc.femto.kommon.util.TransitionAdapter

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

inline fun <T : View> T.onSharedElementExitTransitionStart(crossinline body: T.() -> Unit) {
    window.sharedElementExitTransition.addListener(object : TransitionAdapter() {
        override fun onTransitionStart(transition: Transition) {
            window.sharedElementExitTransition.removeListener(this)
            body()
        }
    })
}

inline fun <T : View> T.onSharedElementExitTransitionEnd(crossinline body: T.() -> Unit) {
    window.sharedElementExitTransition.addListener(object : TransitionAdapter() {
        override fun onTransitionEnd(transition: Transition) {
            window.sharedElementExitTransition.removeListener(this)
            body()
        }
    })
}

inline fun <T : View> T.onSharedElementEnterTransitionStart(crossinline body: T.() -> Unit) {
    window.sharedElementEnterTransition.addListener(object : TransitionAdapter() {
        override fun onTransitionStart(transition: Transition) {
            window.sharedElementEnterTransition.removeListener(this)
            body()
        }
    })
}

inline fun <T : View> T.onSharedElementEnterTransitionEnd(crossinline body: T.() -> Unit) {
    window.sharedElementEnterTransition.addListener(object : TransitionAdapter() {
        override fun onTransitionEnd(transition: Transition) {
            window.sharedElementEnterTransition.removeListener(this)
            body()
        }
    })
}

inline fun <T : View> T.onSharedElementReturnTransitionStart(crossinline body: T.() -> Unit) {
    window.sharedElementReturnTransition.addListener(object : TransitionAdapter() {
        override fun onTransitionStart(transition: Transition) {
            window.sharedElementReturnTransition.removeListener(this)
            body()
        }
    })
}

inline fun <T : View> T.onSharedElementReturnTransitionEnd(crossinline body: T.() -> Unit) {
    window.sharedElementReturnTransition.addListener(object : TransitionAdapter() {
        override fun onTransitionEnd(transition: Transition) {
            window.sharedElementReturnTransition.removeListener(this)
            body()
        }
    })
}

inline fun <T : View> T.onSharedElementReenterTransitionStart(crossinline body: T.() -> Unit) {
    window.sharedElementReenterTransition.addListener(object : TransitionAdapter() {
        override fun onTransitionStart(transition: Transition) {
            window.sharedElementReenterTransition.removeListener(this)
            body()
        }
    })
}

inline fun <T : View> T.onSharedElementReenterTransitionEnd(crossinline body: T.() -> Unit) {
    window.sharedElementReenterTransition.addListener(object : TransitionAdapter() {
        override fun onTransitionEnd(transition: Transition) {
            window.sharedElementReenterTransition.removeListener(this)
            body()
        }
    })
}

fun View.popup(@MenuRes menuRes: Int, gravity: Int = Gravity.END, showIcons: Boolean = true, callback: (Int) -> Unit) {
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

fun View.locationOnScreen(): Point {
    val viewLoc = intArrayOf(0, 0)
    getLocationOnScreen(viewLoc)
    return Point(viewLoc[0], viewLoc[1])
}

fun View.locationInWindow(): Point {
    val viewLoc = intArrayOf(0, 0)
    getLocationInWindow(viewLoc)
    return Point(viewLoc[0], viewLoc[1])
}

