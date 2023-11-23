package com.example.basicinformationjusour.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.findNavController
import com.example.basicinformationjusour.R
import com.example.basicinformationjusour.adapter.ExperienceCallFBAdapter
import com.example.basicinformationjusour.adapter.LocationFlexBoxAdapter
import com.example.basicinformationjusour.databinding.FragmentPreferenceBinding
import com.example.basicinformationjusour.fragment.preferences.AcademicOrHolidayTime
import com.example.basicinformationjusour.fragment.preferences.EmployerBS
import com.example.basicinformationjusour.fragment.preferences.HybridOrRemoteBS
import com.example.basicinformationjusour.fragment.preferences.LanguageBS
import com.example.basicinformationjusour.fragment.preferences.LocationBS
import com.example.basicinformationjusour.fragment.preferences.PreferredSectorEmployedBS
import com.example.basicinformationjusour.fragment.preferences.SkillExperienceBS
import com.example.basicinformationjusour.model.LocationData
import com.example.basicinformationjusour.model.SkillExperienceData
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import java.util.Calendar


const val TAG = "PreferenceFragment"
class PreferenceFragment : Fragment() {
    private lateinit var binding: FragmentPreferenceBinding
    private var isValid=true
    private var languageFlag=false
    private var weekFlag=false
    private var policyFlag=false
    private var flag= false
    private var count=1
    private val now = Calendar.getInstance()
    private val currentYear: Int = now.get(Calendar.YEAR)
    private val currentMonth: Int = now.get(Calendar.MONTH)
    private val currentDay: Int = now.get(Calendar.DAY_OF_MONTH)

    var skillList= mutableListOf<SkillExperienceData>()
    var locationList= mutableListOf<LocationData>()

    var toExperienceCallBack: ((MutableList<SkillExperienceData>)-> Unit) ? = null
    var toLocationCallBack: ((MutableList<LocationData>)-> Unit) ?=null

    private lateinit var adapter:ExperienceCallFBAdapter
    private lateinit var locationRVAdapter: LocationFlexBoxAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentPreferenceBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged", "SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {

            val layoutManager = FlexboxLayoutManager(requireContext())
            layoutManager.flexWrap = FlexWrap.WRAP
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.justifyContent = JustifyContent.FLEX_START
            layoutManager.alignItems = AlignItems.FLEX_START

            experienceWorkRV.layoutManager = layoutManager
            adapter = ExperienceCallFBAdapter()
            experienceWorkRV.adapter= adapter


            fun changeLanguageButton(index: Int, icon: AppCompatImageView, unSelectedIcon: AppCompatImageView, thirdUnSelectedIcon: AppCompatImageView,
                                     background: ConstraintLayout, unSelectBackground: ConstraintLayout,thirdUnselectedBackground: ConstraintLayout) {
                when(index) {
                    index -> {
                        icon.setImageDrawable(ContextCompat.getDrawable(root.context, R.drawable.tick))
                        background.setBackgroundResource(R.drawable.button_border)
                        unSelectedIcon.setImageDrawable(ContextCompat.getDrawable(root.context, R.drawable.radio_border))
                        unSelectBackground.setBackgroundResource(R.drawable.un_click_button_border)
                        thirdUnSelectedIcon.setImageDrawable(ContextCompat.getDrawable(root.context, R.drawable.radio_border))
                        thirdUnselectedBackground.setBackgroundResource(R.drawable.un_click_button_border)
                    }
                }
            }

            englishSubCLT.setOnClickListener {
                count=1
                languageEL.visibility=View.INVISIBLE
                changeLanguageButton(count, englishIconView, arabIconView,bothIconView,englishSubCLT,arabSubCLT,bothSubCLT)
                languageFlag=true
            }

            arabSubCLT.setOnClickListener {
                count=2
                languageEL.visibility=View.INVISIBLE
                changeLanguageButton(count, arabIconView, englishIconView,bothIconView,arabSubCLT,englishSubCLT,bothSubCLT)
                languageFlag=true
            }

            bothSubCLT.setOnClickListener {
                count=3
                languageEL.visibility=View.INVISIBLE
                changeLanguageButton(count, bothIconView, englishIconView,arabIconView,bothSubCLT,englishSubCLT,arabSubCLT)
                languageFlag=true
            }

            weekdaySubCLT.setOnClickListener{
                count=1
                weekWorkEL.visibility=View.INVISIBLE
                changeSelectedButton(count,weekDayIconView,weekEndIconView, weekdaySubCLT, weekEndSubCLT)
                weekFlag=true
            }

            weekEndSubCLT.setOnClickListener{
                count=2
                weekWorkEL.visibility=View.INVISIBLE
                changeSelectedButton(count, weekEndIconView, weekDayIconView, weekEndSubCLT, weekdaySubCLT)
                weekFlag=true
            }

            yesButtonSubCLT.setOnClickListener{
                count=1
                yesNoButtonEL.visibility=View.INVISIBLE
                changeSelectedButton(count,yesButtonIconView,noButtonIconView, yesButtonSubCLT, noButtonSubCLT)
                flag=true
            }

            noButtonSubCLT.setOnClickListener{
                count=2
                yesNoButtonEL.visibility=View.INVISIBLE
                changeSelectedButton(count, noButtonIconView, yesButtonIconView, noButtonSubCLT, yesButtonSubCLT)
                flag=true
            }





            languagePreferLT.setOnClickListener{
                LanguageBS().apply {
                    languageCallBack={
                        languagePreferBoxTV.text=it.language
                    }
                }.show(childFragmentManager,"LANGUAGE")
            }

            dobSubCLT.setOnClickListener{
                dobPicker()
            }

            startDateSubCLT.setOnClickListener{
                startDate()
            }

            endDateSubCLT.setOnClickListener{
                endDate()
            }

            academicWorkCLT.setOnClickListener{
                AcademicOrHolidayTime().apply {
                    academicCallBack={
                        academicWorkTV.text=it
                    }
                }.show(childFragmentManager,"ACADEMIC")
            }

            remoteOrHybridSubCLT.setOnClickListener{
                HybridOrRemoteBS().apply {
                    workCallBack={
                        remoteOrHybridTV.text=it
                    }
                }.show(childFragmentManager,"HYBRID")
            }

            employerPreferredSectorCLT.setOnClickListener{
                PreferredSectorEmployedBS().apply {
                    sectorCallBack={
                        employerPreferredSectorTV.text=it
                    }
                }.show(childFragmentManager,"SECTOR")
            }

            employerCLT.setOnClickListener{
                EmployerBS().apply {
                    employerCallBack={
                        employerTV.text=it
                    }
                }.show(childFragmentManager,"EMPLOYER")
            }

            val locationLayoutManager = FlexboxLayoutManager(requireContext())
            locationRV.layoutManager=locationLayoutManager
            locationRVAdapter= LocationFlexBoxAdapter()
            locationRV.adapter=locationRVAdapter





            locationSubCLT.setOnClickListener {

                locationList = preferenceLocationList()
                val selectedList = locationRVAdapter.list.map { it.location }
                locationList.map { it.isSelected  = selectedList.contains(it.location) }

                Log.e("getLocation", locationRVAdapter.list.map { it.location }.size.toString() )

              LocationBS().apply {
                        list = locationList
                        locationCallBack = {
                            locationList = it
                            locationRVAdapter.apply {
                                list = it.map { it.copy() }.filter { it.isSelected }.toMutableList()
                                notifyDataSetChanged()

                                if(locationRVAdapter.list.map { it.location }.isNotEmpty()){
                                    locationEL.visibility=View.INVISIBLE
                                    isValid=true
                                }
                                toLocationCallBack?.invoke(list)
                            }
                        }
                    }.show(childFragmentManager, "LOCATION")

                if(locationRVAdapter.list.map { it.location }.isEmpty()){
                    locationEL.visibility=View.VISIBLE
                    isValid=false
                }
            }




            experienceInWorkSubCLT.setOnClickListener {

                skillList = preferenceSkillsList()
                val selectedSkillList = adapter.list.map { it.qualities }
                skillList.map { it.isSelected = selectedSkillList.contains(it.qualities) }

                Log.e("getskill", adapter.list.map { it.qualities }.size.toString() )

                SkillExperienceBS().apply {
                    skills = skillList
                    skillsCallBack = {
                        skillList = it

                        adapter.apply {
                            list = it.map { it.copy() }.filter { it.isSelected }.toMutableList()
                            notifyDataSetChanged()

                            if (adapter.list.map { it.qualities }.isNotEmpty()) {
                                experienceInWorkEL.visibility=View.INVISIBLE
                                isValid=true
                            }

                            toExperienceCallBack?.invoke(list)

                        }
                    }
                }.show(childFragmentManager, "SKILL")

                if(adapter.list.map { it.qualities }.isEmpty()){
                    experienceInWorkEL.visibility=View.VISIBLE
                    isValid=false
                }

            }







            languagePreferBoxTV.doOnTextChanged { _, _, _, _ ->
                languagePreferEL.visibility=View.INVISIBLE
            }

            academicWorkTV.doOnTextChanged { _, _, _, _ ->
                academicWorkEL.visibility=View.INVISIBLE
            }

            remoteOrHybridTV.doOnTextChanged { _, _, _, _ ->
                remoteOrHybridEL.visibility=View.INVISIBLE
            }

            dobTV.doOnTextChanged { _, _, _, _ ->
                dobEL.visibility=View.INVISIBLE
            }

            employerPreferredSectorTV.doOnTextChanged { _, _, _, _ ->
                employerPreferredSectorEL.visibility=View.INVISIBLE
            }

            employerTV.doOnTextChanged { _, _, _, _ ->
                employerEL.visibility=View.INVISIBLE
            }

            startDateTV.doOnTextChanged { _, _, _, _ ->
                startDateEL.visibility=View.INVISIBLE
            }

            endDateTV.doOnTextChanged { _, _, _, _ ->
                endDateEL.visibility=View.INVISIBLE
            }

            privacyPolicyTick.setOnClickListener{
                policyFlag=!policyFlag
                if (policyFlag){
                    privacyPolicyTick.setImageDrawable(ContextCompat.getDrawable(privacyPolicyTick.context,R.drawable.square_untick))
                    }else
                    privacyPolicyTick.setImageDrawable(ContextCompat.getDrawable(privacyPolicyTick.context,R.drawable.square_tick))
            }


            createButton.setOnClickListener{
                validation()
                if (isValid && !policyFlag){
                    Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_preference_to_startExploringFragment)
                }
                if (policyFlag){
                    toast("Agree the Terms and Conditions!!!")
                }
                else
                    Toast.makeText(context, "UnSuccessful", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun preferenceLocationList(): MutableList<LocationData>{
        return arrayListOf(
            LocationData("Armenia"),
            LocationData("India"),
            LocationData("Delhi"),
            LocationData("Qatar"),
            LocationData("Mumbai"),
            LocationData("Punjab")
        )
    }

    private fun preferenceSkillsList(): MutableList<SkillExperienceData>{
        return arrayListOf(
            SkillExperienceData("Analytical Skills"),
            SkillExperienceData("Communication"),
            SkillExperienceData("Innovation"),
            SkillExperienceData("LeaderShip"),
            SkillExperienceData("TeamWork")
        )
    }

    private fun changeSelectedButton(index: Int, icon: AppCompatImageView, unSelectedIcon: AppCompatImageView, background: ConstraintLayout, unSelectBackground: ConstraintLayout) {
        when(index) {
            index -> {
                icon.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.tick) })
                background.setBackgroundResource(R.drawable.button_border)
                unSelectedIcon.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.radio_border) })
                unSelectBackground.setBackgroundResource(R.drawable.un_click_button_border)
            }
        }
    }



    private fun validation(){
    isValid=true
    binding.apply {

        if (!flag) {
            yesNoButtonEL.visibility = View.VISIBLE
        }

        if (!languageFlag){
           languageEL.visibility=View.VISIBLE
        }

        if (!weekFlag){
            weekWorkEL.visibility=View.VISIBLE
        }

        if (languagePreferBoxTV.text.toString().trim().isEmpty()){
            languagePreferEL.visibility=View.VISIBLE
            isValid=false
        }

        if (academicWorkTV.text.toString().trim().isEmpty()){
            academicWorkEL.visibility=View.VISIBLE
            isValid=false
        }

        if (remoteOrHybridTV.text.toString().trim().isEmpty()){
            remoteOrHybridEL.visibility=View.VISIBLE
            isValid=false
        }

        if (dobTV.text.toString().trim().isEmpty()){
            dobEL.visibility=View.VISIBLE
            isValid=false
        }

        if (employerPreferredSectorTV.text.toString().trim().isEmpty()){
            employerPreferredSectorEL.visibility=View.VISIBLE
            isValid=false
        }

        if (employerTV.text.toString().trim().isEmpty()){
            employerEL.visibility=View.VISIBLE
            isValid=false
        }

        if (locationRVAdapter.list.map { it.location }.isEmpty()){
            locationEL.visibility=View.VISIBLE
            isValid=false
        }

        if (adapter.list.map { it.qualities }.isEmpty()){
            experienceInWorkEL.visibility=View.VISIBLE
            isValid=false
        }

         if (startDateTV.text.toString().trim().isEmpty()){
            startDateEL.visibility=View.VISIBLE
            isValid=false
        }

        if (endDateTV.text.toString().trim().isEmpty()){
            endDateEL.visibility=View.VISIBLE
            isValid=false
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


/*SkillExperienceBS().apply {
                    skillsCallBack = {
                        adapter.list = it.filter { it.isSelected }.toMutableList()
                        adapter.notifyDataSetChanged()
                    }
                    skills = adapter.list.filter { it.isSelected }.map {
                        it.qualities
                    }.toMutableList()

                }.show(childFragmentManager,"SKILL")*/