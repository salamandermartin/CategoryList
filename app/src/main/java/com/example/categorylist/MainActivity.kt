package com.example.categorylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private lateinit var itemAdapter: ItemAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		itemAdapter = ItemAdapter(mutableListOf())

		rvItems.adapter = itemAdapter
		rvItems.layoutManager = LinearLayoutManager(this)

		btnAddItem.setOnClickListener {
			val itemTitle = etItemEntry.text.toString()
			if(itemTitle.isNotEmpty()){
				val item = Item(itemTitle)
				itemAdapter.addItem(item)
				etItemEntry.text.clear()
			}
		}

		btnDeleteItems.setOnClickListener {
			itemAdapter.deleteItems()
		}
	}

}