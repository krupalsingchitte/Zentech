package com.kr.zentech.Fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kr.zentech.Adapters.ProductAdapter
import com.kr.zentech.R
import com.kr.zentech.databinding.FragmentProductsBinding
import com.kr.zentech.model.ProductEntity
import com.kr.zentech.model.Products
import com.kr.zentech.viewmodel.ProductsViewModel


class ProductsFragment : Fragment() {


    private lateinit var binding: FragmentProductsBinding
    private lateinit var viewModel: ProductsViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(layoutInflater, container, false)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[ProductsViewModel::class.java]


        viewModel.productsLiveData.observe(viewLifecycleOwner) { products ->
            displayImages(products)
        }

        viewModel.searchResultsLiveData.observe(viewLifecycleOwner) { searchResults ->
            displayImages(searchResults)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                showError(errorMessage)
            }
        }

        viewModel.loadProducts()
        setHasOptionsMenu(true)



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    performSearch(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                performSearch(newText.toString())
                return true
            }
        })
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                viewModel.loadProducts()
                return true
            }
        })
    }

    private fun performSearch(query: String) {
        viewModel.searchProducts(query)
    }

    private fun displayImages(listofproducts: List<Products>) {
        val recyclerView: RecyclerView = binding.allRcv
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = ProductAdapter(requireContext(), listofproducts as ArrayList<Products>)
        recyclerView.adapter = adapter
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }


}



