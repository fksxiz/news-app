package com.example.supobasetesting.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.supobasetesting.Common.Order
import com.example.supobasetesting.R

class OrdersAdapter:RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    var orders = emptyList<Order>()
    class OrdersViewHolder(view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item,parent,false)
        return OrdersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.titleTextView).text = orders[position].title
            findViewById<TextView>(R.id.contentTextView).text = orders[position].content
        }
        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context,"Clicked at $position element",Toast.LENGTH_SHORT).show()
        }
    }
}