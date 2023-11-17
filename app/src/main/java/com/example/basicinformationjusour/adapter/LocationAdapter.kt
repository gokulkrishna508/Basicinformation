package com.example.basicinformationjusour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.basicinformationjusour.R
import com.example.basicinformationjusour.databinding.CellLocationBinding
import com.example.basicinformationjusour.model.LocationData

class LocationAdapter( val locationList: MutableList<LocationData>): RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=CellLocationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=locationList[position]

        holder.itemBinding.apply {

            language.text=item.location

            if (item.isSelected)
                iconSelect.setImageDrawable(ContextCompat.getDrawable(iconSelect.context, R.drawable.ic_selected))
            else
                iconSelect.setImageDrawable(ContextCompat.getDrawable(root.context,R.drawable.ic_un_selected))

            root.click {
                if (item.isSelected){
                    item.isSelected=false
                    notifyItemChanged(position)
                }else{
                    item.isSelected=true
                    notifyItemChanged(position)
                }
            }
        }

    }

    class ViewHolder(val itemBinding: CellLocationBinding): RecyclerView.ViewHolder(itemBinding.root)
}