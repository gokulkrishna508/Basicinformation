package com.example.basicinformationjusour.fragment.noc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basicinformationjusour.databinding.FragmentNocBottomSheetBinding

class NocBottomSheet : Fragment() {

    private lateinit var binding: FragmentNocBottomSheetBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentNocBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

}