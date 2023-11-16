package com.example.basicinformationjusour.fragment.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basicinformationjusour.databinding.FragmentAcademicOrHolidayTimeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AcademicOrHolidayTime : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAcademicOrHolidayTimeBinding
    var academicCallBack:((data: String)->Unit)?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=FragmentAcademicOrHolidayTimeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            var academic : String ?=null

            yesButton.setOnClickListener(){
                academic="Yes"
                academic?.let { academicCallBack?.invoke(it) }
                dismiss()
            }

            noButton.setOnClickListener{
                academic="No"
                academic?.let { academicCallBack?.invoke(it) }
                dismiss()
            }
        }
    }
}