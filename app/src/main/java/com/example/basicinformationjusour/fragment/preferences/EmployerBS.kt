package com.example.basicinformationjusour.fragment.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basicinformationjusour.databinding.FragmentEmployerBSBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EmployerBS : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentEmployerBSBinding
    var employerCallBack: ((data: String)-> Unit)?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=FragmentEmployerBSBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            var employer : String

            yesButton.setOnClickListener{
                employer="Yes"
                employer.let { employerCallBack?.invoke(it) }
                dismiss()
            }

            noButton.setOnClickListener{
                employer="No"
                employer.let { employerCallBack?.invoke(it) }
                dismiss()
            }
        }
    }
}