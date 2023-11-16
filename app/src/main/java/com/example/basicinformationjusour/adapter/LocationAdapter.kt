package com.example.basicinformationjusour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicinformationjusour.databinding.CellLocationBinding
import com.example.basicinformationjusour.model.LocationData

class LocationAdapter(private val locationList: ArrayList<LocationData>,  val onCallBack: ((LocationData)-> Unit)): RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=CellLocationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=locationList[position]
        holder.itemBinding.language.text=item.location
        holder.itemBinding.language.setOnClickListener {
            onCallBack.invoke(LocationData(item.location))
        }
    }

    class ViewHolder(val itemBinding: CellLocationBinding): RecyclerView.ViewHolder(itemBinding.root)
}