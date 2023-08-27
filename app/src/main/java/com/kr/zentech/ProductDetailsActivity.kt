package com.kr.zentech

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.kr.zentech.Adapters.ImageSliderAdapter
import com.kr.zentech.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrls = intent.getStringArrayListExtra("descList") ?: emptyList()
        val imageSliderAdapter = ImageSliderAdapter(imageUrls)
        binding.sliderViewPager.adapter = imageSliderAdapter
        binding.sliderViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val price = intent.getDoubleExtra("price", 0.0)
        val discountPercentage = intent.getDoubleExtra("discountPercentage", 0.0)
        val rating = intent.getDoubleExtra("rating", 0.0)
        val stock = intent.getIntExtra("stock", 0)
        val brand = intent.getStringExtra("brand")
        val category = intent.getStringExtra("category")

        binding.apply {
            titleTextView.text = title
            descriptionTextView.text = description
            priceTextView.text = "Price: $$price"
            discountTextView.text = "Discount: ${discountPercentage}%"
            ratingTextView.text = "Rating: $rating"
            stockTextView.text = "Stock: $stock"
            brandTextView.text = "Brand: $brand"
            categoryTextView.text = "Category: $category"
        }
    }
}