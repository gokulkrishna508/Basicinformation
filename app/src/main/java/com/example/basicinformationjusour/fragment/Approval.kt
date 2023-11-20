package com.example.basicinformationjusour.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.basicinformationjusour.R
import com.example.basicinformationjusour.databinding.FragmentApprovalBinding

class Approval : Fragment() {
    private lateinit var binding: FragmentApprovalBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=FragmentApprovalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.approvalContinueButton.setOnClickListener {
            findNavController().navigate(R.id.action_approval_to_preference)
        }
    }

}