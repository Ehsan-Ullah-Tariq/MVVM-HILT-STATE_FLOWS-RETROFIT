package com.android.test.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import cn.pedant.SweetAlert.SweetAlertDialog
import com.android.test.R
import com.android.test.adapters.ItemsAdapter
import com.android.test.databinding.ActivityMainBinding
import com.android.test.listeners.ItemsListener
import com.android.test.network.Resource
import com.android.test.network.models.HomeDataModelItem
import com.android.test.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemsListener {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var itemsAdapter: ItemsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        itemsAdapter = ItemsAdapter(this, this)
        initRecyclerView()
        getHomeData()

    }

    private fun initRecyclerView() {
        binding.rvItems.adapter = itemsAdapter
    }

    private fun getHomeData() {
        homeViewModel.homeDataRequest()
        val pDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#47b54a")
        pDialog.titleText = "Loading.."
        pDialog.show()
        pDialog.setCancelable(false)
        observeData(pDialog)
    }


    private fun observeData(pDialog: SweetAlertDialog) {
        lifecycleScope.launchWhenCreated {

            homeViewModel.getHomeDataResponse.collect {
                when (it) {
                    is Resource.Success -> {
                        pDialog.dismissWithAnimation()

                        val listOfData: MutableList<HomeDataModelItem> = it.value as MutableList<HomeDataModelItem>
                        itemsAdapter.submitList(listOfData)
                    }

                    is Resource.Failure -> {
                        pDialog.dismissWithAnimation()
                        Toast.makeText(
                            this@MainActivity,
                            "Sorry! something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    Resource.Loading -> {

                    }
                    else -> {
                        pDialog.dismissWithAnimation()
                    }
                }
            }

        }
    }

    override fun onProductClick(position: Int, item: HomeDataModelItem) {
        val intent = Intent(this,ItemDetailsActivity::class.java)
        ItemDetailsActivity.item = item
        startActivity(intent)
    }
}