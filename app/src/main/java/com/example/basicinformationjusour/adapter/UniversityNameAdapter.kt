package com.example.basicinformationjusour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicinformationjusour.databinding.UniversityNameItemViewBinding
import com.example.basicinformationjusour.model.UniversityData

class UniversityNameAdapter(private  val universityList: ArrayList<UniversityData>, private val onClick: (item: UniversityData)-> Unit): RecyclerView.Adapter<UniversityNameAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=UniversityNameItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return universityList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=universityList[position]
        holder.itemBinding.universityName.text=item.university
        holder.itemBinding.universityName.setOnClickListener(){
            onClick.invoke(UniversityData(item.university))
        }
    }

    class ViewHolder( val itemBinding: UniversityNameItemViewBinding ): RecyclerView.ViewHolder(itemBinding.root)
}