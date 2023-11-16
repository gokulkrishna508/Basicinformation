package com.example.basicinformationjusour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basicinformationjusour.databinding.LanguageItemLayoutBinding
import com.example.basicinformationjusour.model.LanguageData


class LanguageAdapter(private val languageList: ArrayList<LanguageData>, private val onClick:(item: LanguageData)-> Unit): RecyclerView.Adapter<LanguageAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LanguageItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return languageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=languageList[position]
        holder.itemLayoutBinding.language.text=item.language
        holder.itemLayoutBinding.language.setOnClickListener(){
            onClick.invoke(LanguageData(item.language))
        }
    }
    class ViewHolder(val itemLayoutBinding: LanguageItemLayoutBinding): RecyclerView.ViewHolder(itemLayoutBinding.root)
}