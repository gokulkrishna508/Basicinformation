package com.example.basicinformationjusour.fragment.university

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicinformationjusour.R
import com.example.basicinformationjusour.adapter.UniversityNameAdapter
import com.example.basicinformationjusour.databinding.FragmentUniversityBottomSheetBinding
import com.example.basicinformationjusour.model.UniversityData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UniversityBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentUniversityBottomSheetBinding
    var universityNameCallBack: ((UniversityData)-> Unit)?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentUniversityBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        universityRecyclerView()

    }

    private fun universityRecyclerView(){
        binding.apply {
            universityNameRecyclerview.layoutManager=LinearLayoutManager(context)
            universityNameRecyclerview.adapter=UniversityNameAdapter(universityList()){
                universityNameCallBack?.invoke(it)
                dismiss()
            }
        }
    }

    private fun universityList(): ArrayList<UniversityData>{
        return arrayListOf(
            UniversityData("M.G University"),
            UniversityData("Kerala University"),
            UniversityData("Oxford University"),
            UniversityData("J.N.U"),
            UniversityData("Cambridge University"),
            UniversityData("British Columbia University")
        )
    }

}