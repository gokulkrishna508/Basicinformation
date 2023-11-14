package com.example.basicinformationjusour.fragment.university

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicinformationjusour.R
import com.example.basicinformationjusour.adapter.UniversityMajorAdapter
import com.example.basicinformationjusour.databinding.FragmentUniversityMajorBSBinding
import com.example.basicinformationjusour.databinding.UniversityMajorItemViewBinding
import com.example.basicinformationjusour.model.UniversityMajorData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UniversityMajorBS : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentUniversityMajorBSBinding
    var universitySubjectCallback:((UniversityMajorData)->Unit)?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentUniversityMajorBSBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        universityMajorRV()
    }

    private fun universityMajorRV(){
        binding.apply {
            universityMajorRV.layoutManager=LinearLayoutManager(context)
            universityMajorRV.adapter=UniversityMajorAdapter(universityMajorList()){
                universitySubjectCallback?.invoke(it)
                dismiss()
            }
        }
    }

    private fun universityMajorList(): ArrayList<UniversityMajorData>{
        return arrayListOf(
            UniversityMajorData("Bachelor of Computer Application"),
            UniversityMajorData("Bachelor of Commerce"),
            UniversityMajorData("Bachelor of Business Administration"),
            UniversityMajorData("B.Tech"),
            UniversityMajorData("M.Tech"),
            UniversityMajorData("B.A Mathematics"),
            UniversityMajorData("B.A English")
        )
    }

}