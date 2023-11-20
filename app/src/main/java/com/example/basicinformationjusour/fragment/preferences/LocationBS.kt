package com.example.basicinformationjusour.fragment.preferences

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicinformationjusour.adapter.LocationAdapter
import com.example.basicinformationjusour.adapter.click
import com.example.basicinformationjusour.databinding.FragmentLocationBSBinding
import com.example.basicinformationjusour.fragment.PreferenceFragment
import com.example.basicinformationjusour.model.LocationData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LocationBS : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentLocationBSBinding
    private lateinit var adapter: LocationAdapter
    var locationCallBack: ((list: MutableList<LocationData>)-> Unit)?=null
    var selectedList= mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= FragmentLocationBSBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationRV()
    }

    private fun locationRV(){
        binding.apply {
            PreferenceFragment().apply {
                toLocationCallBack = {
                    //val list = it
                    Log.e("getDatas", "$it" )
                    adapter = LocationAdapter(it)
                    locationRV.adapter = adapter
                }
            }

//                list.map { it.isSelected = selectedList.contains(it.location) }



                locationSubmit.click {
                    locationCallBack?.invoke(adapter.locationList)
                    dismiss()
            }
        }
    }

    /*private fun locationList(): MutableList<LocationData>{
        return arrayListOf(
            LocationData("Armenia"),
            LocationData("India"),
            LocationData("Delhi"),
            LocationData("Qatar"),
            LocationData("Mumbai"),
            LocationData("Punjab")
        )
    }*/
}