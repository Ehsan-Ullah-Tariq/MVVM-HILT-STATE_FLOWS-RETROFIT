package com.android.test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.test.R
import com.android.test.databinding.ActivityItemDetailsBinding
import com.android.test.network.models.HomeDataModelItem
import com.bumptech.glide.Glide

class ItemDetailsActivity : AppCompatActivity() {

    companion object{
        var item:HomeDataModelItem? = null
    }

    private lateinit var binding: ActivityItemDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_item_details)

        binding.backBtn.setOnClickListener {
            finish()
        }

        setItemData()
    }

    private fun setItemData() {

        binding.item = item
        if (item?.type.equals("Article")){
            binding.itemTitle.text = item?.titile
        }else{
            binding.itemTitle.text = item?.name
        }


        binding.itemDescription.text = item?.content

        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/insta-clone-11076.appspot.com/o/kotlin.webp?alt=media&token=9d894815-9e30-42fe-867d-10522d5d7741").into(binding.itemImage)
    }



    override fun onDestroy() {
        item = null
        super.onDestroy()
    }

}