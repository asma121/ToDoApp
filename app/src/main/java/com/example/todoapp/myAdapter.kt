package com.example.todoapp

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemRowBinding

class myAdapter(private val list: ArrayList<todoitem>): RecyclerView.Adapter<myAdapter.ItemViewHolder>(){
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val listitem = list[position]
        holder.binding.apply {
            textView.text = listitem.item
            checkBox.isChecked=listitem.checked
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    textView.setTextColor(Color.GRAY)
                }else{
                    textView.setTextColor(Color.BLACK)
                }
                listitem.checked = !listitem.checked
            }
        }
    }
    override fun getItemCount() = list.size

    fun deleteItems(){
        list.removeAll{ listitem -> listitem.checked }
        notifyDataSetChanged()
    }
}