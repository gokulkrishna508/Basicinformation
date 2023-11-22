package com.example.basicinformationjusour.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicinformationjusour.databinding.CellSkillExperienceBinding
import com.example.basicinformationjusour.model.SkillExperienceData

class ExperienceCallFBAdapter : RecyclerView.Adapter<ExperienceCallFBAdapter.ViewHolder>() {

    var list = mutableListOf<SkillExperienceData>()
    var onClick: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=CellSkillExperienceBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=list[position]
        holder.bind(item)
    }

    inner class ViewHolder(val itemBinding: CellSkillExperienceBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bind(item: SkillExperienceData){
            itemBinding.apply {
                textSkills.text = item.qualities

                imgClose.click {
                    try {
                        list.removeAt(bindingAdapterPosition)
                        notifyItemRemoved(bindingAdapterPosition)
                        notifyItemRangeChanged(bindingAdapterPosition, itemCount)

                    } catch (e: Exception) {
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