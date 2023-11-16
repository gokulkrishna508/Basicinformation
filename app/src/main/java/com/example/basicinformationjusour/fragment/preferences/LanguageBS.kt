package com.example.basicinformationjusour.fragment.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicinformationjusour.adapter.LanguageAdapter
import com.example.basicinformationjusour.databinding.FragmentLanguageBSBinding
import com.example.basicinformationjusour.model.LanguageData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LanguageBS : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLanguageBSBinding
    var languageCallBack: ((LanguageData)->Unit)?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentLanguageBSBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        languageRV()
    }

    private fun languageRV(){
        binding.apply {
            languageRV.layoutManager=LinearLayoutManager(context)
            languageRV.adapter=LanguageAdapter(languageList()){
                languageCallBack?.invoke(it)
                dismiss()
            }
        }
    }

    private fun languageList(): ArrayList<LanguageData>{
        return arrayListOf(
            LanguageData("English"),
            LanguageData("Arab"),
            LanguageData("Hindi")

        )
    }
}