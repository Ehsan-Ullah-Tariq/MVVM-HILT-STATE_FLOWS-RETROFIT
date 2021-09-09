package com.android.test.utils

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Displays a message when app comes to foreground and goes to background.
 */
class AppLifecycleObserver(context: Context) : LifecycleObserver {

    private val enterForegroundToast =
            Toast.makeText(context, "Welcome to the app!", Toast.LENGTH_SHORT)

    private val enterBackgroundToast =
            Toast.makeText(context, "Goodbye!", Toast.LENGTH_SHORT)

    private val destroyBackgroundToast =
            Toast.makeText(context, "I'm Destroyed!", Toast.LENGTH_SHORT)

    /**
     * Shows foreground {@link android.widget.Toast} after attempting to cancel the background one.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onEnterForeground() {
//        enterForegroundToast.showAfterCanceling(enterBackgroundToast)
    }

    /**
     * Shows background {@link android.widget.Toast} after attempting to cancel the foreground one.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onEnterBackground() {
//        enterBackgroundToast.showAfterCanceling(enterForegroundToast)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyBackground() {
//        destroyBackgroundToast.showAfterCanceling(destroyBackgroundToast)
    }

    private fun Toast.showAfterCanceling(toastToCancel: Toast) {
        toastToCancel.cancel()
        this.show()
    }
}