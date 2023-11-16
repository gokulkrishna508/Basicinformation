package com.example.basicinformationjusour.fragment.preferences

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicinformationjusour.R
import com.example.basicinformationjusour.adapter.LocationAdapter
import com.example.basicinformationjusour.databinding.FragmentLovationBSBinding
import com.example.basicinformationjusour.model.LocationData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LocationBS : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLovationBSBinding
    var locationCallBack: ((item: LocationData)-> Unit)?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentLovationBSBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationRV()
    }

    private fun locationRV(){
        binding.apply {
            locationRV.layoutManager=LinearLayoutManager(context)
            locationRV.adapter=LocationAdapter(locationList()){
                locationCallBack?.invoke(it)
                dismiss()
            }
        }
    }

    private fun locationList(): ArrayList<LocationData>{
        return arrayListOf(
            LocationData("Armenia"),
            LocationData("India"),
            LocationData("Delhi"),
            LocationData("Qatar"),
            LocationData("Mumbai"),
            LocationData("Punjab")
        )
    }
}