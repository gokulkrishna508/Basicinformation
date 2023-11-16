package com.example.basicinformationjusour.fragment.preferences

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basicinformationjusour.R
import com.example.basicinformationjusour.databinding.FragmentHybridOrRemoteBSBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HybridOrRemoteBS : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentHybridOrRemoteBSBinding
    var workCallBack:((data: String)->Unit)?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentHybridOrRemoteBSBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            var job: String?=null
            hybridButton.setOnClickListener{
                job="Hybrid"
                job?.let { workCallBack?.invoke(it) }
                dismiss()
            }
            partiallyRemoteButton.setOnClickListener{
                job="Partially Remote"
                job?.let { workCallBack?.invoke(it) }
                dismiss()
            }
            fullyRemoteButton.setOnClickListener{
                job="Fully Remote"
                job?.let { workCallBack?.invoke(it) }
                dismiss()
            }
        }
    }

}