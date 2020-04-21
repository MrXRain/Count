package com.rain.myapplication.view

import android.util.ArrayMap
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rain.myapplication.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.view.*

/**
 * @author rain
 */
class ListAdapter : RecyclerView.Adapter<ListAdapter.CountHolder>() {

    private var datas: ArrayMap<Int, MutableList<Float>> = ArrayMap()

    fun setItem(items:ArrayMap<Int, MutableList<Float>>) {
        datas.clear()
        datas.putAll(items)
    }

    class CountHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return CountHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: CountHolder, position: Int) {
        val year = datas.keyAt(position)
        val data = datas[year]!!
        holder.itemView.tvCount.text = "$year—${data.sum()}"

        for (index in data.indices) {
            if (index < data.size - 1) {
                if (data[index] > data[index + 1]) {
                    holder.itemView.card.setCardBackgroundColor(holder.itemView.context.resources.getColor(R.color.colorAccent))
                    holder.itemView.setOnClickListener {
                        Toast.makeText(holder.itemView.context,"季度数据降低",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}