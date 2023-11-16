package com.example.basicinformationjusour.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.basicinformationjusour.adapter.ExperienceCallFBAdapter
import com.example.basicinformationjusour.databinding.FragmentPreferenceBinding
import com.example.basicinformationjusour.fragment.preferences.AcademicOrHolidayTime
import com.example.basicinformationjusour.fragment.preferences.EmployerBS
import com.example.basicinformationjusour.fragment.preferences.HybridOrRemoteBS
import com.example.basicinformationjusour.fragment.preferences.LanguageBS
import com.example.basicinformationjusour.fragment.preferences.PreferredSectorEmployedBS
import com.example.basicinformationjusour.fragment.preferences.SkillExperienceBS
import com.example.basicinformationjusour.model.SkillExperienceData
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import java.util.Calendar

class PreferenceFragment : Fragment() {
    private lateinit var binding: FragmentPreferenceBinding
    private var isValid=true
    var flag= false
    private val now = Calendar.getInstance()
    private val currentYear: Int = now.get(Calendar.YEAR)
    private val currentMonth: Int = now.get(Calendar.MONTH)
    private val currentDay: Int = now.get(Calendar.DAY_OF_MONTH)

    lateinit var ESList: MutableList<SkillExperienceData>
    private lateinit var adapter:ExperienceCallFBAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentPreferenceBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.apply {

//            yesButton.setOnClickListener {
//                flag=!flag
//                if(flag){
//                    noBlueTickButton.visibility=View.INVISIBLE
//                }else
//                    noButton.visibility=View.VISIBLE
//
//            }

            val layoutManager = FlexboxLayoutManager(requireContext())
            layoutManager.flexWrap = FlexWrap.WRAP
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.justifyContent = JustifyContent.FLEX_START
            layoutManager.alignItems = AlignItems.FLEX_START
            experienceWorkRV.layoutManager = layoutManager


            adapter = ExperienceCallFBAdapter()
         //   experienceWorkRV.layoutManager=FlexboxLayoutManager(context,FlexDirection.ROW,FlexWrap.WRAP)
            experienceWorkRV.adapter= adapter

            noButton.setOnClickListener {
                flag=!flag
                if(flag){
                   // yesUnTickButton.visibility=View.VISIBLE
                    noBlueTickButton.visibility=View.VISIBLE
                    yesUnTickButton.visibility=View.VISIBLE
                }else
                    yesUnTickButton.visibility=View.VISIBLE
            }


            yesUnTickButton.setOnClickListener {
                flag=!flag
                if(flag){
                    yesUnTickButton.visibility=View.INVISIBLE
                    yesButton.visibility=View.VISIBLE
                    noButton.visibility=View.VISIBLE
                }else
                    yesUnTickButton.visibility=View.INVISIBLE
                    noBlueTickButton.visibility=View.INVISIBLE
            }
//
//            noBlueTickButton.setOnClickListener {
//                flag=!flag
//                if(flag){
//                    yesUnTickButton.visibility=View.VISIBLE
//                    noBlueTickButton.visibility=View.VISIBLE
//                }else
//                    yesButton.visibility=View.INVISIBLE
//            }




            languagePreferBoxTV.setOnClickListener{
                LanguageBS().apply {
                    languageCallBack={
                        languagePreferBoxTV.text=it.language
                    }
                }.show(childFragmentManager,"LANGUAGE")
            }

            dobTV.setOnClickListener{
                dobPicker()
            }

            startDateTV.setOnClickListener{
                startDate()
            }

            endDateTV.setOnClickListener{
                endDate()
            }

            academicWorkTV.setOnClickListener{
                AcademicOrHolidayTime().apply {
                    academicCallBack={
                        academicWorkTV.text=it
                    }
                }.show(childFragmentManager,"ACADEMIC")
            }

            remoteOrHybridTV.setOnClickListener{
                HybridOrRemoteBS().apply {
                    workCallBack={
                        remoteOrHybridTV.text=it
                    }
                }.show(childFragmentManager,"HYBRID")
            }

            employerPreferredSectorTV.setOnClickListener{
                PreferredSectorEmployedBS().apply {
                    sectorCallBack={
                        employerPreferredSectorTV.text=it
                    }
                }.show(childFragmentManager,"SECTOR")
            }

            employerTV.setOnClickListener{
                EmployerBS().apply {
                    employerCallBack={
                        employerTV.text=it
                    }
                }.show(childFragmentManager,"EMPLOYER")
            }

            locationTV.setOnClickListener{
//                LocationBS().apply {
//                    locationCallBack={
//                        locationTV.text=it.location
//                    }
//                }.show(childFragmentManager,"LOCATION")

              /*  try {
                    experienceRV()
                }catch (e: Exception){

                }*/

            }

            experienceInWorkSubCLT.setOnClickListener{
                SkillExperienceBS().apply {
                    skillsCallBack = {
                        adapter.list = it.filter { it.isSelected }.toMutableList()
                        adapter.notifyDataSetChanged()
                    }
                    skills = adapter.list.filter { it.isSelected }.map {
                        it.qualities
                    }.toMutableList()
                    Log.e("skills","<<< $skills")
                }.show(childFragmentManager,"SKILL")
            }





        }
    }







    @SuppressLint("SetTextI18n")
    private fun dobPicker() {
        val calendarFragment = context?.let {
            DatePickerDialog(it,
                { _, year, monthOfYear, dayOfMonth ->
                    binding.dobTV.text= (dayOfMonth.toString() + "/"
                            + (monthOfYear + 1) + "/" + year)
                }, currentYear, currentMonth, currentDay)
        }
        calendarFragment?.datePicker?.maxDate=System.currentTimeMillis()
        calendarFragment?.show()
    }

    @SuppressLint("SetTextI18n")
    private fun startDate(){
        val calendarView=context?.let {
            DatePickerDialog(it,
                { _,year,monthOfYear,dayOfMonth ->
                    binding.startDateTV.text= (dayOfMonth.toString() + "/"
                            + (monthOfYear + 1) + "/" + year)
                }, currentYear, currentMonth, currentDay)
        }
        calendarView?.datePicker?.maxDate=System.currentTimeMillis()
        calendarView?.show()
    }


    @SuppressLint("SetTextI18n")
    private fun endDate(){
        val calendarView=context?.let {
            DatePickerDialog(it,
                { _,year,monthOfYear,dayOfMonth ->
                    binding.endDateTV.text= (dayOfMonth.toString() + "/"
                            + (monthOfYear + 1) + "/" + year)
                }, currentYear, currentMonth, currentDay)
        }
        calendarView?.datePicker?.minDate=System.currentTimeMillis()
        calendarView?.show()
    }

}