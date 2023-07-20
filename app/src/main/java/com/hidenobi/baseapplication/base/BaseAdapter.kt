package com.hidenobi.baseapplication.base

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(
    var listItem: MutableList<T>,
    var onClickListener: BaseAdapterListener<T>
) : RecyclerView.Adapter<BaseAdapter<T, VB>.ViewHolder>() {

/*  TODO when use BaseAdapter, example ExampleAdapter
    ExampleAdapter(list:MutableList<ExampleEntity>,private val onClick:ExampleAdapterListener<ExampleEntity>)
    :BaseAdapter<ExampleEntity,ExampleItem>(list,onClick){

    TODO implement all abstract function
    TODO write interface implement BaseAdapterListener if need
    TODO
        override createBinding(parent: ViewGroup): VB {
            val inflate = LayoutInflate.from(parent.context)
            return VB.inflate(inflate, parent, false)
        }
    }
 */
    inner class ViewHolder(private val binding: VB) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            setView(item, adapterPosition, binding)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun notifyData() {
        notifyDataSetChanged()
    }

    abstract fun setView(item: T, position: Int, binding: VB)
    abstract fun createBinding(parent: ViewGroup): VB

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = createBinding(parent)
        return ViewHolder(binding)
    }

}