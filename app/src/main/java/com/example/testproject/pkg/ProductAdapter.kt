package com.example.testproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class DestinationAdapter(
    private val context: Context,
    private val imgList: List<Int>,
    private val titleList: List<String>,
    private val descriptionList: List<String>
) : RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder>() {

    // create view holder function
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.singledestination, parent, false)
        return DestinationViewHolder(itemView)
    }

    // binding the resources
    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        holder.image.setImageResource(imgList[position])
        holder.title.text = titleList[position]
        holder.description.text = descriptionList[position]
    }

    // number of items
    override fun getItemCount(): Int = imgList.size

    class DestinationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: CircleImageView = itemView.findViewById(R.id.destination_image)
        val title: TextView = itemView.findViewById(R.id.destination_title)
        val description: TextView = itemView.findViewById(R.id.destination_description)
    }
}