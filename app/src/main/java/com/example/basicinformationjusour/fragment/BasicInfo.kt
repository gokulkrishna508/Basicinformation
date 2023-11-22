package com.example.basicinformationjusour.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.database.getStringOrNull
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.basicinformationjusour.R
import com.example.basicinformationjusour.databinding.FragmentBasicInfoBinding
import com.example.basicinformationjusour.fragment.university.UniversityBottomSheet
import com.example.basicinformationjusour.fragment.university.UniversityMajorBS
import com.example.basicinformationjusour.utils.Keys
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.io.File
import java.util.Calendar

private val DOCUMENT_TYPE_MIME_TO_EXTENSION_MAP = mapOf(
    "application/pdf" to "pdf"
)
class BasicInfo : Fragment() {
    private lateinit var binding: FragmentBasicInfoBinding
    private var isValid=true
     var count=0
    private val now = Calendar.getInstance()
    private val currentYear: Int = now.get(Calendar.YEAR)
    private val currentMonth: Int = now.get(Calendar.MONTH)
    private val currentDay: Int = now.get(Calendar.DAY_OF_MONTH)

   /* private var cameraImageFile: File? = null
    private var cameraImageUri: Uri? = null
    private var galleryImageFile: File? = null
    private var galleryImageUri: Uri? = null*/


    private val launcher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { fileUri ->
            try {
                val fileName = fileUri.getOriginalFileName(requireContext())
                binding.nocLetterTV.text = fileName
            }catch (e:Exception){
                toast("error occurred....")
            }
        }
    }

    private val enrollmentLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { fileUri ->
            try {
                val fileName = fileUri.getOriginalFileName(requireContext())
                binding.enrollmentLetterTV.text = fileName
            }catch (e:Exception){
                toast("error occurred....")
            }
        }
    }

    private val resumeLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { fileUri ->
            try {
                val fileName = fileUri.getOriginalFileName(requireContext())
                binding.resumeTV.text = fileName
            }catch (e:Exception){
                toast("error occurred....")
            }
        }
    }


    private val galleryPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        {
            if (it.values.all { it }) {
               // pickVisualMediaContract.launch(ActivityResultContracts.GetContent().toString())

                launcher.launch(arrayOf(
                    "application/pdf"
                ))
            }
        }

    private val enrollmentPdfPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        {
            if (it.values.all { it }) {
                enrollmentLauncher.launch(arrayOf(
                    "application/pdf"
                ))
            }
        }

    private val resumePdfPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        {
            if (it.values.all { it }) {
                resumeLauncher.launch(arrayOf(
                    "application/pdf"
                ))
            }
        }



    /* private val pickVisualMediaContract = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                val cursor = context?.contentResolver?.query(
                    uri, arrayOf(MediaStore.Images.ImageColumns.MIME_TYPE), null,
                    arrayOf(), null
                )
               if (cursor != null && cursor.moveToNext()) {
                    val mimeType =
                        cursor.getStringOrNull(cursor.getColumnIndex(MediaStore.Images.ImageColumns.MIME_TYPE))
                    cursor.close()
                    if (mimeType in DOCUMENT_TYPE_MIME_TO_EXTENSION_MAP.keys) {
                        val inputStream = context?.contentResolver?.openInputStream(uri)
                        val cal = Calendar.getInstance()
                        val timeString = String.format(
                            "%04d%02d%02d_%02d%02d%02d_%03d",
                            cal[Calendar.YEAR], cal[Calendar.MONTH] + 1, cal[Calendar.DAY_OF_MONTH],
                            cal[Calendar.HOUR_OF_DAY], cal[Calendar.MINUTE], cal[Calendar.SECOND],
                            cal[Calendar.MILLISECOND]

                        )
                        val galleryFileName =
                            "TestGalleryImage${timeString}.${DOCUMENT_TYPE_MIME_TO_EXTENSION_MAP[mimeType]}"
                        val directory = File(context?.cacheDir, "IMAGE")
                        if (!directory.exists()) {
                            directory.mkdirs()
                        }
                        val imageFile = File(directory, galleryFileName)
                        val outPutStream = imageFile.outputStream()
                        inputStream?.copyTo(outPutStream)
                        inputStream?.close()
                        outPutStream.close()
                        println("@GALL ${imageFile.absolutePath}")
                        //   binding.preview.setImageURI(uri)
                        binding.nocLetterTV.text = uri.lastPathSegment.toString()

                    }
                }
            }
        }*/

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



            privacyPolicyTick.setOnClickListener(){
                privacyPolicyUnTick.visibility=View.VISIBLE
                privacyPolicyTick.visibility=View.INVISIBLE
            }

            privacyPolicyUnTick.setOnClickListener(){
                privacyPolicyTick.visibility=View.VISIBLE
                privacyPolicyUnTick.visibility=View.INVISIBLE
            }


            firstNameEditText.doOnTextChanged { _, _, _, _ ->
                firstNameTextErrorListener.text= resources.getString(R.string.this_field_is_required)
                firstNameTextErrorListener.visibility=View.INVISIBLE

            }

            lastNameEditText.doOnTextChanged { _, _, _, _ ->
                lastNameTextErrorListener.text= resources.getString(R.string.this_field_is_required)
                lastNameTextErrorListener.visibility=View.INVISIBLE
            }

            dobTV.doOnTextChanged { _, _, _, _ ->
                dobErrorListener.text= resources.getString(R.string.this_field_is_required)
                dobErrorListener.visibility=View.INVISIBLE

            }

            universityTV.doOnTextChanged { _, _, _, _ ->
                universityErrorListener.text= resources.getString(R.string.this_field_is_required)
                universityErrorListener.visibility=View.INVISIBLE

            }

            universityMajorTV.doOnTextChanged { _, _, _, _ ->
                universityMajorEL.text= resources.getString(R.string.this_field_is_required)
                universityMajorEL.visibility=View.INVISIBLE

            }

            universityStartTV.doOnTextChanged { _, _, _, _ ->
                universityStartEL.text= resources.getString(R.string.this_field_is_required)
                universityStartEL.visibility=View.INVISIBLE

            }

            expectedGraduationTV.doOnTextChanged { _, _, _, _ ->
                expectedGraduationEL.text= resources.getString(R.string.this_field_is_required)
                expectedGraduationEL.visibility=View.INVISIBLE

            }

            nocLetterTV.doOnTextChanged { _, _, _, _ ->
                nocLetterEL.text= resources.getString(R.string.this_field_is_required)
                nocLetterEL.visibility=View.INVISIBLE

            }

            enrollmentLetterTV.doOnTextChanged { _, _, _, _ ->
                enrollmentLetterEL.text= resources.getString(R.string.this_field_is_required)
                enrollmentLetterEL.visibility=View.INVISIBLE

            }

            localAddressET.doOnTextChanged { _, _, _, _ ->
                localAddressEL.text= resources.getString(R.string.this_field_is_required)
                localAddressEL.visibility=View.INVISIBLE

            }

            emailET.doOnTextChanged { _, _, _, _ ->
                emailEL.text= resources.getString(R.string.this_field_is_required)
                emailEL.visibility=View.INVISIBLE

            }

            phoneEditText.doOnTextChanged { _, _, _, _ ->
                phoneEL.text= resources.getString(R.string.this_field_is_required)
                phoneEL.visibility=View.INVISIBLE
            }

            resumeTV.doOnTextChanged { _, _, _, _ ->
                resumeEL.text= resources.getString(R.string.this_field_is_required)
                resumeEL.visibility=View.INVISIBLE
            }

            dobLT.setOnClickListener(){
                dobPicker()
            }

            universityStartLT.setOnClickListener(){
                universityStartDate()
            }

            universityLT.setOnClickListener(){
                UniversityBottomSheet().apply {
                    universityNameCallBack={
                        universityTV.text=it.university
                    }
                }.show(childFragmentManager,"UNIVERSITY")
            }

            universityMajorLT.setOnClickListener()
            {
                UniversityMajorBS().apply {
                    universitySubjectCallback={
                        universityMajorTV.text=it.subject
                    }
                }.show(childFragmentManager,"SUBJECT")
            }

            nocLetterButton.setOnClickListener(){
                if (nocLetterTV.text.toString().isNotEmpty()){
                    toast("Image Updated")
                }else
                    toast("Image not updated")
            }

            enrollmentUploadButton.setOnClickListener(){
                if (enrollmentLetterTV.text.toString().isNotEmpty()){
                    toast("Image Updated")
                }else
                    toast("Image not updated")
            }

            resumeUploadButton.setOnClickListener(){
                if (resumeTV.text.toString().isNotEmpty()){
                    toast("Image Updated")
                }else
                    toast("Image not updated")
            }



            nocLetterLT.setOnClickListener(){
                setUpViews()
            }

            enrollmentLetterLT.setOnClickListener(){
                setUpEnrollmentViews()
            }

            expectedGraduationLT.setOnClickListener(){
                expectedGraduationDate()
            }

            resumeLT.setOnClickListener(){
                setUpResumeViews()
            }






            preferenceIcon.setOnClickListener(){
                findNavController().navigate(R.id.action_basicInfo_to_preference)
            }

            englishSubCLT.setOnClickListener {
                count=1
                changeLanguageButton(count, englishIconView, arabIconView,bothIconView,englishSubCLT,arabSubCLT,bothSubCLT)
            }

            arabSubCLT.setOnClickListener {
                count=2
                changeLanguageButton(count, arabIconView, englishIconView,bothIconView,arabSubCLT,englishSubCLT,bothSubCLT)
            }

            bothSubCLT.setOnClickListener {
                count=3
                changeLanguageButton(count, bothIconView, englishIconView,arabIconView,bothSubCLT,englishSubCLT,arabSubCLT)
            }




            continueButton.setOnClickListener(){
                validation()
                if (isValid){
                    Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_basicInfo_to_approval)
                }else
                    Toast.makeText(context, "UnSuccessful", Toast.LENGTH_SHORT).show()
            }

        }
    }

   private fun changeLanguageButton(index: Int, icon: AppCompatImageView, unSelectedIcon: AppCompatImageView, thirdUnSelectedIcon: AppCompatImageView,
                             background: ConstraintLayout, unSelectBackground: ConstraintLayout, thirdUnselectedBackground: ConstraintLayout) {
        when(index) {
            index -> {
                icon.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.tick) })
                background.setBackgroundResource(R.drawable.button_border)
                unSelectedIcon.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.radio_border) })
                unSelectBackground.setBackgroundResource(R.drawable.un_click_button_border)
                thirdUnSelectedIcon.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.radio_border) })
                thirdUnselectedBackground.setBackgroundResource(R.drawable.un_click_button_border)
            }

        }
    }

    private fun validation(){
        isValid=true
        binding.apply {
            if (firstNameEditText.text.toString().trim().isEmpty()){
                firstNameTextErrorListener.visibility=View.VISIBLE
                isValid=false
            }

            else if (firstNameEditText.text.toString().trim().length<=2){
                firstNameTextErrorListener.text=resources.getString(R.string.above_2_characters)
                firstNameTextErrorListener.visibility=View.VISIBLE
                isValid=false
            }

            if (firstNameEditText.text.toString().trim().matches(".*[0-9].*".toRegex())){
                firstNameTextErrorListener.text=resources.getString(R.string.characters_allowed)
                firstNameTextErrorListener.visibility=View.VISIBLE
                isValid=false
            }

            if (lastNameEditText.text.toString().trim().isEmpty()){
                lastNameTextErrorListener.visibility=View.VISIBLE
                isValid=false
            }

            else if (lastNameEditText.text.toString().trim().length<=2){
                lastNameTextErrorListener.text=resources.getString(R.string.above_2_characters)
                lastNameTextErrorListener.visibility=View.VISIBLE
                isValid=false
            }

            if (lastNameEditText.text.toString().trim().matches(".*[0-9].*".toRegex())){
                lastNameTextErrorListener.text=resources.getString(R.string.characters_allowed)
                lastNameTextErrorListener.visibility=View.VISIBLE
                isValid=false
            }

            if (dobTV.text.toString().trim().isEmpty()){
                dobErrorListener.visibility=View.VISIBLE
                isValid=false
            }

            if (universityTV.text.toString().trim().isEmpty()){
                universityErrorListener.visibility=View.VISIBLE
                isValid=false
            }

            if (universityMajorTV.text.toString().trim().isEmpty()){
                universityMajorEL.visibility=View.VISIBLE
                isValid=false
            }

            if (universityStartTV.text.toString().trim().isEmpty()){
                universityStartEL.visibility=View.VISIBLE
                isValid=false
            }

            if (expectedGraduationTV.text.toString().trim().isEmpty()){
                expectedGraduationEL.visibility=View.VISIBLE
                isValid=false
            }

            if (nocLetterTV.text.toString().trim().isEmpty()){
                nocLetterEL.visibility=View.VISIBLE
                isValid=false
            }

            if (enrollmentLetterTV.text.toString().trim().isEmpty()){
                enrollmentLetterEL.visibility=View.VISIBLE
                isValid=false
            }

            if (localAddressET.text.toString().trim().isEmpty()){
                localAddressEL.visibility=View.VISIBLE
                isValid=false
            }

            if (emailET.text.toString().trim().isEmpty()){
                emailEL.visibility = View.VISIBLE
                isValid=false
            }

            else if (!Patterns.EMAIL_ADDRESS.matcher(emailET.text.toString().trim()).matches()) {
                emailEL.text = resources.getString(R.string.invalid_email)
                emailEL.visibility = View.VISIBLE
                isValid=false
            }

            if (phoneEditText.text.toString().trim().isEmpty()){
                phoneEL.visibility = View.VISIBLE
                isValid=false

            }

            else if (phoneEditText.text.toString().trim().length < 8) {
                phoneEL.text = resources.getString(R.string.characters_8_required)
                phoneEL.visibility = View.VISIBLE
                isValid=false
            }
            if (phoneEditText.text.toString().trim().matches(".*[A-Z].*".toRegex())) {
                phoneEL.text = resources.getString(R.string.characters_allowed)
                phoneEL.visibility = View.VISIBLE
                isValid= false
            }
            if (phoneEditText.text.toString().trim().isEmpty()) {
                phoneEL.visibility = View.VISIBLE
                isValid=false
            }

            if (resumeTV.text.toString().trim().isEmpty()){
                resumeEL.visibility = View.VISIBLE
                isValid=false
            }


            isValid
        }
    }

    @SuppressLint("SetTextI18n")
    private fun dobPicker() {
        val calendarFragment = context?.let {
            DatePickerDialog(it,
                { view, year, monthOfYear, dayOfMonth ->
                    binding.dobTV.text= (dayOfMonth.toString() + "/"
                            + (monthOfYear + 1) + "/" + year)
                }, currentYear, currentMonth, currentDay
            )
        }
        calendarFragment?.datePicker?.maxDate=System.currentTimeMillis()
        calendarFragment?.show()
    }

    @SuppressLint("SetTextI18n")
    private fun universityStartDate(){
        val calendarFragment = context?.let {
            DatePickerDialog(it,
                { view, year, monthOfYear, dayOfMonth ->
                    binding.universityStartTV.text= (dayOfMonth.toString() + "/"
                            + (monthOfYear + 1) + "/" + year)
                }, currentYear, currentMonth, currentDay
            )
        }
        calendarFragment?.datePicker?.maxDate=System.currentTimeMillis()
        calendarFragment?.show()
    }

    @SuppressLint("SetTextI18n")
    private fun expectedGraduationDate(){
        val calendarFragment = context?.let {
            DatePickerDialog(it,
                { view, year, monthOfYear, dayOfMonth ->
                    binding.expectedGraduationTV.text= (dayOfMonth.toString() + "/"
                            + (monthOfYear + 1) + "/" + year)
                }, currentYear, currentMonth, currentDay
            )
        }
        calendarFragment?.datePicker?.minDate=System.currentTimeMillis()
        calendarFragment?.show()
    }

    private fun setUpViews() {
        binding.apply {
            nocLetterTV.setOnClickListener {
                galleryPermission.launch(
                    if (Build.VERSION.SDK_INT >= 33) {
                        arrayOf(
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    } else {
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                )
            }
        }
    }

    private fun setUpEnrollmentViews() {
        binding.apply {
            enrollmentLetterTV.setOnClickListener {
                enrollmentPdfPermission.launch(
                    if (Build.VERSION.SDK_INT >= 33) {
                        arrayOf(
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    } else {
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                )
            }
        }
    }

    private fun setUpResumeViews() {
        binding.apply {
            resumeTV.setOnClickListener {
                resumePdfPermission.launch(
                    if (Build.VERSION.SDK_INT >= 33) {
                        arrayOf(
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    } else {
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                )
            }
        }
    }
}


fun Uri.getOriginalFileName(context: Context): String? {
    return context.contentResolver.query(this, null, null, null, null)?.use {
        val nameColumnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        it.moveToFirst()
        it.getString(nameColumnIndex)
    }
}

fun Fragment.toast(message:String){
    Toast.makeText(this.requireContext(),message,Toast.LENGTH_SHORT).show()
}