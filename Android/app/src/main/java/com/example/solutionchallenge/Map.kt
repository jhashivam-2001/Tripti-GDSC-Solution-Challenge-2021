package com.example.solutionchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Map : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map2)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        Toast.makeText(this, "Showing available locations", Toast.LENGTH_SHORT).show()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val l1 = LatLng(25.620861, 85.084023)
        val l2=LatLng(25.594950,85.207901)
        val l3=LatLng(25.568130,85.142043)
        val l4=LatLng(25.577241,85.037959)
        val l5=LatLng(25.581884,85.245905)
        mMap.addMarker(MarkerOptions().position(l1).title("Location 1"))
        mMap.addMarker(MarkerOptions().position(l2).title("Location 2"))
        mMap.addMarker(MarkerOptions().position(l3).title("Location 3"))
        mMap.addMarker(MarkerOptions().position(l4).title("Location 4"))
        mMap.addMarker(MarkerOptions().position(l5).title("Location 5"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(l1))

    }
}