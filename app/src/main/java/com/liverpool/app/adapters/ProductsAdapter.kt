package com.liverpool.app.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.liverpool.app.R
import com.liverpool.app.model.RecordsResponse
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private val context: Context,
    private val dataSource: ArrayList<RecordsResponse>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.products_list, parent, false)

        val image = rowView.findViewById(R.id.imageViewProduct) as ImageView
        val title = rowView.findViewById(R.id.productTitle) as TextView
        val price = rowView.findViewById(R.id.priceTitle) as TextView
        val location = rowView.findViewById(R.id.locationTitle) as TextView

        val product = getItem(position) as RecordsResponse

        title.text = product.productDisplayName
        price.text = "$" + product.listPrice.toString()
        location.text = product.productId
        Picasso.get().load(product.smImage).into(image)

        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }


}