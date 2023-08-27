package com.kr.zentech

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kr.zentech.Adapters.ProductAdapter
import com.kr.zentech.Fragments.ProductsFragment
import com.kr.zentech.Fragments.UserFragment
import com.kr.zentech.databinding.ActivityMainBinding
import com.kr.zentech.model.ProductListResponse
import com.kr.zentech.model.Products
import com.kr.zentech.retrofitApi.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> replaceFragment(ProductsFragment())
                R.id.ic_profile -> replaceFragment(UserFragment())
            }
            true
        }

    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentReplace, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }






}