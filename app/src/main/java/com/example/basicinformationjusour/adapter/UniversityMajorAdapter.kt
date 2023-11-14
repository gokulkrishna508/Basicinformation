package com.example.basicinformationjusour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicinformationjusour.databinding.UniversityMajorItemViewBinding
import com.example.basicinformationjusour.model.UniversityMajorData

class UniversityMajorAdapter(private val subjectList: ArrayList<UniversityMajorData>, private val onClick: (item: UniversityMajorData)-> Unit): RecyclerView.Adapter<UniversityMajorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=UniversityMajorItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=subjectList[position]
        holder.itemViewBinding.universitySubject.text=item.subject
        holder.itemViewBinding.universitySubject.setOnClickListener(){
            onClick.invoke(UniversityMajorData(item.subject))
        }
    }

    class ViewHolder(val itemViewBinding: UniversityMajorItemViewBinding): RecyclerView.ViewHolder(itemViewBinding.root)
}