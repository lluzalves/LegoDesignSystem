package com.daniel.legodesignsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daniel.legodesignsystem.databinding.ActivityMainBinding
import com.daniel.legodesignsystem.model.Component
import com.daniel.legodesignsystem.model.ComponentItem

class MainActivity : View.OnClickListener, AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        val view = binding.root

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_components)
        recyclerView.apply {
            val componentList = listOf(
                ComponentItem(
                    "Button",
                    R.drawable.ic_baseline_add_24,
                    Component.BUTTONS
                )
            )
            val adapter = ComponentsAdapter(
                componentList,
                this@MainActivity
            )
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            layoutManager = linearLayoutManager
            this.adapter = adapter
        }
    }

    override fun onClick(view: View) {
        Intent(this@MainActivity, com.legods.component.button.LegoButtonViewActivity::class.java)
            .run {
                startActivity(this)
            }
    }
}