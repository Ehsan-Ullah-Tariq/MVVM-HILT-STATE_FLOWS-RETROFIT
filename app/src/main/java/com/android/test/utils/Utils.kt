package com.android.test.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

object Utils {

    @JvmStatic
    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }


    @BindingAdapter(value = ["setImageUrl"])
    fun ImageView.loadImage(imageUrl: String?) {

        this.post {
            Glide.with(context.applicationContext)
                .load(imageUrl)
                .into(this)

        }

    }

    fun RecyclerView.getCurrentVisiblePosition(layoutManager: RecyclerView.LayoutManager?): Int? {
        return when (layoutManager) {
            is LinearLayoutManager -> {
                (this.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
            }
            is GridLayoutManager -> {
                (this.layoutManager as GridLayoutManager?)!!.findFirstVisibleItemPosition()
            }
            else -> {
                null
            }
        }
    }

    fun ProgressBar.show() {
        visibility = View.VISIBLE
    }

    fun ProgressBar.hide() {
        visibility = View.GONE
    }

}