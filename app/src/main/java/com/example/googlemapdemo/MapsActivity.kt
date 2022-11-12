package com.example.googlemapdemo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.googlemapdemo.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    // kitna zoom hoga

//    1: World
//
//    5: Landmass/continent
//
//    10: City
//
//    15: Streets
//
//    20: Buildings

    val zoomLevel = 15f

    // added multiple markers
    var sydney = LatLng(28.548701796034933, 77.30089809613798)
    var TamWorth = LatLng(28.546326838184505, 77.29501869450495)
    var NewCastle = LatLng(28.5453990485739, 77.3089093679328)

    // creating array list for adding all our locations.
    private val locationArrayList: ArrayList<LatLng> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // multiple marker
        locationArrayList.add(sydney)
        locationArrayList.add(TamWorth)
        locationArrayList.add(NewCastle)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // for single marker
//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(28.664753551639436, 77.23055222199677)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))


        // for custom marker
        // Add a marker in Sydney and move the camera
        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(28.664753551639436, 77.23055222199677)
//        mMap.addMarker(
//            MarkerOptions().position(sydney)
//                .title("Marker in Sydney") // below line is use to add custom marker on our map.
//                .icon(BitmapFromVector(applicationContext, com.example.googlemapdemo.R.drawable.ic_baseline_home_24))
//        )
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))


        // multiple marker
        for (i in 0 until locationArrayList.size){
            mMap.addMarker(MarkerOptions().position(locationArrayList.get(i)).title("Marker in Sydney").icon(BitmapFromVector(applicationContext, com.example.googlemapdemo.R.drawable.ic_baseline_home_24)))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationArrayList.get(i), zoomLevel))
        }

        mMap.setOnMarkerClickListener(this)

    }

    override fun onMarkerClick(p0: Marker) : Boolean {
        Toast.makeText(this, p0.title, Toast.LENGTH_SHORT).show()
        return true
    }


    // custom icon
    private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        // below line is use to generate a drawable.
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        // below line is use to set bounds to our vector drawable.
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )

        // below line is use to create a bitmap for our
        // drawable which we have added.
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // below line is use to add bitmap in our canvas.
        val canvas = Canvas(bitmap)

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas)

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}