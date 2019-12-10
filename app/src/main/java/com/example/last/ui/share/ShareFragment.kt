package com.example.last.ui.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap
import android.R
import androidx.annotation.Nullable
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng


class ShareFragment : Fragment(), OnMapReadyCallback {

    lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(com.example.last.R.layout.fragment_send, container, false)
        return root
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(com.example.last.R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.mapType = GoogleMap.MAP_TYPE_HYBRID

        val uiSettings = map.uiSettings
        uiSettings.isZoomControlsEnabled = true

        val pp = LatLng(13.8147954, -88.8636758)
        map.addMarker(MarkerOptions().position(pp).title("Restaurante Ilobasco"))

        val zoomlevel = 16f

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pp, zoomlevel))
    }
}