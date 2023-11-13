package com.example.basicinformationjusour.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.basicinformationjusour.R
import com.example.basicinformationjusour.databinding.FragmentBasicInfoBinding

class BasicInfo : Fragment() {
    private lateinit var binding: FragmentBasicInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentBasicInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            englishButton.setOnClickListener(){
                englishUnTickButton.visibility=View.VISIBLE
                englishButton.visibility=View.INVISIBLE
            }

            englishUnTickButton.setOnClickListener(){
                englishUnTickButton.visibility=View.INVISIBLE
                englishButton.visibility=View.VISIBLE
            }

            arabButton.setOnClickListener(){
                arabBlueTickButton.visibility=View.VISIBLE
                arabButton.visibility=View.INVISIBLE
            }

            arabBlueTickButton.setOnClickListener(){
                arabBlueTickButton.visibility=View.INVISIBLE
                arabButton.visibility=View.VISIBLE
            }

            bothButton.setOnClickListener(){
                bothBlueTickButton.visibility=View.VISIBLE
                bothButton.visibility=View.INVISIBLE
            }

            bothBlueTickButton.setOnClickListener(){
                bothBlueTickButton.visibility=View.INVISIBLE
                bothButton.visibility=View.VISIBLE
            }
        }
    }
}