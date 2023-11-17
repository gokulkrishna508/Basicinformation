package com.example.basicinformationjusour.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicinformationjusour.databinding.CellLocationLayoutRvBinding
import com.example.basicinformationjusour.model.LocationData

class LocationFlexBoxAdapter : RecyclerView.Adapter<LocationFlexBoxAdapter.ViewHolder>() {

     var list= mutableListOf<LocationData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=CellLocationLayoutRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=list[position]
        holder.itemBinding.apply {
            locationRVText.text=item.location

            Log.e("locally4","<<< ${locationRVText.text}")

            locationRVClose.click {

                try {
                    list.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position,itemCount)
                }catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }


    class ViewHolder(val itemBinding: CellLocationLayoutRvBinding): RecyclerView.ViewHolder(itemBinding.root)
}