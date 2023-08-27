package com.kr.zentech.Adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kr.zentech.ProductDetailsActivity
import com.kr.zentech.R
import com.kr.zentech.model.Products

class ProductAdapter(val requireContext: Context, private val listofproducts: ArrayList<Products>) :
    RecyclerView.Adapter<ProductAdapter.bomViewHolder>() {
    inner class bomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageview = itemView.findViewById<ImageView>(R.id.all_images)!!
        val title = itemView.findViewById<TextView>(R.id.product_title)!!
        val price = itemView.findViewById<TextView>(R.id.product_price)!!
        val rating = itemView.findViewById<RatingBar>(R.id.product_rating)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bomViewHolder {
        return bomViewHolder(
            LayoutInflater.from(requireContext).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: bomViewHolder, position: Int) {
        Glide.with(requireContext).load(listofproducts[position].thumbnail).into(holder.imageview)
        holder.title.text = listofproducts[position].title
        holder.price.text = listofproducts[position].price.toString()
        holder.rating.rating = listofproducts[position].rating.toFloat()
        holder.itemView.setOnClickListener {
            val product = listofproducts[position]
            val intent = Intent(requireContext, ProductDetailsActivity::class.java).apply {
                putExtra("id", product.id)
                putExtra("title", product.title)
                putExtra("description", product.description)
                putExtra("price", product.price)
                putExtra("discountPercentage", product.discountPercentage)
                putExtra("rating", product.rating)
                putExtra("stock", product.stock)
                putExtra("brand", product.brand)
                putExtra("category", product.category)
            }
            val listToPass: ArrayList<String> =
                ArrayList(listofproducts[position].images)
            intent.putStringArrayListExtra("descList", listToPass)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount() = listofproducts.size

}