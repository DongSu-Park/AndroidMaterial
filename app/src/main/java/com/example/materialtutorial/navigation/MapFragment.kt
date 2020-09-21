package com.example.materialtutorial.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.materialtutorial.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback{
    private lateinit var mMap : GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(activity).inflate(R.layout.fragment_map, container, false)

        val mapFragment : SupportMapFragment = fragmentManager?.findFragmentById(R.id.layout_MapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!

        val initLatLng = LatLng(37.56, 126.97)
        val makerOptions = MarkerOptions()
        makerOptions.position(initLatLng)

        googleMap.run {
            addMarker(makerOptions)
            moveCamera(CameraUpdateFactory.newLatLng(initLatLng))
            animateCamera(CameraUpdateFactory.zoomTo(13F))
        }
    }
}