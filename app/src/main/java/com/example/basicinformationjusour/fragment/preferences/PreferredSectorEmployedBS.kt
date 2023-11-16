package com.example.basicinformationjusour.fragment.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basicinformationjusour.databinding.FragmentPreferredSectorEmployedBSBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PreferredSectorEmployedBS : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentPreferredSectorEmployedBSBinding
    var sectorCallBack: ((data: String)-> Unit)?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=FragmentPreferredSectorEmployedBSBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            var sector: String?=null

            design.setOnClickListener{
                sector="Design"
                sector?.let { sectorCallBack?.invoke(it) }
                dismiss()
            }
            technical.setOnClickListener{
                sector="Technical"
                sector?.let { sectorCallBack?.invoke(it) }
                dismiss()
            }
            software.setOnClickListener{
                sector="Software"
                sector?.let { sectorCallBack?.invoke(it) }
                dismiss()
            }

            other.setOnClickListener{
                sector="Other"
                sector?.let { sectorCallBack?.invoke(it) }
                dismiss()
            }

        }
    }
}