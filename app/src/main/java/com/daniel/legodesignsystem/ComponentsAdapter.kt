package com.daniel.legodesignsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.daniel.legodesignsystem.model.ComponentItem

class ComponentsAdapter(
    private val componentItens: List<ComponentItem>,
    private val clickListener: View.OnClickListener
) :
    RecyclerView.Adapter<ComponentsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val component = componentItens[position]
        holder.bind(component)
        holder.itemView.setOnClickListener {
            clickListener.onClick(holder.itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.component_item_card, parent, false)
        )

    override fun getItemCount(): Int {
        return componentItens.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.cell_title_text)
        private val image = view.findViewById<ImageView>(R.id.cell_circular_image)

        fun bind(componentItem: ComponentItem) {
            title.text = componentItem.title
            image.setImageDrawable(ContextCompat.getDrawable(itemView.context, componentItem.icon))
        }

    }

}