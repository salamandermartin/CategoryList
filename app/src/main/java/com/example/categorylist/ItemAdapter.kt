package com.example.categorylist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_item.view.*

class ItemAdapter(
	private val items: MutableList<Item>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

	class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
		return ItemViewHolder(
			LayoutInflater.from(parent.context).inflate(
				R.layout.category_item, //xml file with layout for view
				parent, //viewGroup
				false
			)
		)
	}

	fun addItem(item: Item){
		items.add(item)
		notifyItemInserted(items.size - 1) //updates recycler view dimension
	}

	fun deleteItems(){
		items.removeAll{ item ->
			item.isChecked
		}
		notifyDataSetChanged() //updates that recycler view dataset has changed
	}

	private fun toggleStrikeThrough(tvItemText: TextView, isChecked: Boolean){
		if(isChecked){
			tvItemText.paintFlags = tvItemText.paintFlags or STRIKE_THRU_TEXT_FLAG
		} else {
			tvItemText.paintFlags = tvItemText.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
		}
	}

	override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
		val currItem = items[position]
		holder.itemView.apply{
			tvItemText.text = currItem.title
			cbCheckbox.isChecked = currItem.isChecked//in build.grade.app -- include kotlin-android-extensions
			toggleStrikeThrough(tvItemText, currItem.isChecked)
			cbCheckbox.setOnCheckedChangeListener { _ , isChecked ->
				toggleStrikeThrough(tvItemText, isChecked)
				currItem.isChecked = !currItem.isChecked
			}
		}
	}

	override fun getItemCount(): Int {
		return items.size
	}
}