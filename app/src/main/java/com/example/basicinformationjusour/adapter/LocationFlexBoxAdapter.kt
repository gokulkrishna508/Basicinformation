package com.example.basicinformationjusour.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicinformationjusour.databinding.CellLocationLayoutRvBinding
import com.example.basicinformationjusour.model.LocationData

class LocationFlexBoxAdapter : RecyclerView.Adapter<LocationFlexBoxAdapter.ViewHolder>() {

    var list= mutableListOf<LocationData>()
    var onClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=CellLocationLayoutRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=list[position]
     /*   holder.itemBinding.apply {
            locationRVText.text=item.location

            locationRVClose.click {

                try {
                    list.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position,itemCount)
                }catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }*/
        holder.bind(item)
    }


    inner class ViewHolder(val itemBinding: CellLocationLayoutRvBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item: LocationData){
            itemBinding.apply {
                locationRVText.text=item.location

                locationRVClose.click {
                    try {
                        list.removeAt(bindingAdapterPosition)
                        notifyItemRemoved(bindingAdapterPosition)
                        notifyItemRangeChanged(bindingAdapterPosition,itemCount)
                    }catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                root.setOnClickListener {
                    onClick?.invoke(bindingAdapterPosition)
                }
            }
        }
    }
}

//Log.e("locally4","<<< ${locationRVText.text}")