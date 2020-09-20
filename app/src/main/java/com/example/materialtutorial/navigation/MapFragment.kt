package com.example.materialtutorial.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialtutorial.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback{
    private lateinit var mMap : GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(activity).inflate(R.layout.fragment_map, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val initLatLng = LatLng(37.56, 126.97)
        val makerOptions = MarkerOptions()
        makerOptions.run {
            position(initLatLng)
        }
        googleMap?.run {
            addMarker(makerOptions)
            moveCamera(CameraUpdateFactory.newLatLng(initLatLng))
            animateCamera(CameraUpdateFactory.zoomTo(13F))
        }
    }
}