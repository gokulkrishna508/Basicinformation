package com.example.basicinformationjusour.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.basicinformationjusour.R
import com.example.basicinformationjusour.databinding.CellSkillExperienceBottomSheetBinding
import com.example.basicinformationjusour.model.SkillExperienceData

class SkillExperienceAdapter(val skillExperienceList: MutableList<SkillExperienceData> ):
    RecyclerView.Adapter<SkillExperienceAdapter.ViewHolder>() {

    var onclick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=CellSkillExperienceBottomSheetBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return skillExperienceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=skillExperienceList[position]
        holder.bind(item)

    }

    inner class ViewHolder(val itemBinding: CellSkillExperienceBottomSheetBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(item: SkillExperienceData){
            itemBinding.apply {

                skillExperienceBSTV.text = item.qualities

                if(item.isSelected)
                    imgSelection.setImageDrawable(ContextCompat.getDrawable(imgSelection.context, R.drawable.ic_selected))
                else
                    imgSelection.setImageDrawable(ContextCompat.getDrawable(root.context, R.drawable.ic_un_selected))

                root.click {
                   /* if(item.isSelected){
                        item.isSelected = false
                        notifyItemChanged(bindingAdapterPosition)
                    }else{
                        item.isSelected = true
                        notifyItemChanged(bindingAdapterPosition)
                    }*/

                    onclick?.invoke(bindingAdapterPosition)

                }

            }
        }
    }

}

fun View.click(action: ()->Unit){
    this.setOnClickListener{
        action.invoke()
    }
}