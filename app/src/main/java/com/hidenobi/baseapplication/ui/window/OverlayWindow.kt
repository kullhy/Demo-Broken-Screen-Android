package com.hidenobi.baseapplication.ui.window

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import com.hidenobi.baseapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class OverlayWindow(
    @DrawableRes drawableId: Int? = R.drawable.broken_1,
    private val context: Context
) {
    private var view: View

    private val params: WindowManager.LayoutParams = WindowManager.LayoutParams(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
        PixelFormat.TRANSLUCENT,
    )
    private var windowManager: WindowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private var layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        view = layoutInflater.inflate(
            context.resources.getLayout(R.layout.overlay_window),
            null,
            false
        )
        drawableId?.let {
            view.background = AppCompatResources.getDrawable(context, it)
        }
        showOverlay()

    }

    private fun showOverlay() {
        try {
            if (view.windowToken == null && view.parent == null) {
                windowManager.addView(view, params)
            }

        } catch (e: Exception) {
            Log.e(TAG, "showOverlay: ", e)
        }
    }

    fun updateView(@DrawableRes newDrawableId: Int) {
        view.background = AppCompatResources.getDrawable(context, newDrawableId)
        view.invalidate()
        try {
            windowManager.updateViewLayout(view, params)
        } catch (e: Exception) {
            Log.e(TAG, "updateView: ", e)
        }
    }

    fun updateView(path: String) {
        try {
            scope.launch {
                val url = URL(path)
                var drawable: Drawable?
                withContext(Dispatchers.IO) {
                    drawable = BitmapDrawable(
                        context.resources,
                        BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    )
                }
                view.background = drawable
                view.invalidate()

                windowManager.updateViewLayout(view, params)
            }


        } catch (e: Exception) {
            Log.e(TAG, "updateView: ", e)
        }
    }

    fun closeWindow() {
        try {
            windowManager.removeView(view)
        } catch (e: Exception) {
            Log.e(TAG, "closeWindow: ", e)
        }
    }

    companion object {
        const val TAG = "TAG-OverlayWindow"
    }

}