package com.android.test.listeners

import com.android.test.network.models.HomeDataModelItem


interface ItemsListener {
    fun onProductClick(position: Int, item: HomeDataModelItem)
}