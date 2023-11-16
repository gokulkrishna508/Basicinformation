package com.example.basicinformationjusour.fragment.preferences

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicinformationjusour.adapter.SkillExperienceAdapter
import com.example.basicinformationjusour.adapter.click
import com.example.basicinformationjusour.databinding.FragmentSkillExperienceBSBinding
import com.example.basicinformationjusour.model.SkillExperienceData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SkillExperienceBS : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSkillExperienceBSBinding
    var skillsCallBack: ((list:MutableList<SkillExperienceData>)-> Unit)?= null
    private lateinit var adapter:SkillExperienceAdapter
    var skills = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=FragmentSkillExperienceBSBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skillsExperienceRV()
    }

    private fun skillsExperienceRV(){
        binding.apply {
            val list = skillsList()
            list.map { it.isSelected = skills.contains(it.qualities) }

            adapter = SkillExperienceAdapter(list)
            skillExperienceRV.adapter = adapter

            skillSubmit.click {
                skillsCallBack?.invoke(adapter.skillExperienceList)
                dismiss()
            }

        }
    }

    private fun skillsList(): MutableList<SkillExperienceData>{
        return arrayListOf(
            SkillExperienceData("Analytical Skills"),
            SkillExperienceData("Communication"),
            SkillExperienceData("Innovation"),
            SkillExperienceData("LeaderShip"),
            SkillExperienceData("TeamWork")
        )
    }
}