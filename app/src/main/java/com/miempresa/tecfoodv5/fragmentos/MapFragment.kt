package com.miempresa.tecfoodv5.fragmentos

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import com.miempresa.tecfoodv5.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    AdapterView.OnItemSelectedListener  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //Varible Globales
    private lateinit var mMap: GoogleMap

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    var jso: JSONObject? = null

    private var latitud_rest = ""
    private var longitud_rest = ""
    private var nombre_rest = ""
    var marcadorDestino: Marker? = null
    var coordenada = LatLng(0.0, 0.0)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val View = inflater.inflate(R.layout.fragment_map, container, false)

        //Inicializar el Fragment de mapa
        val MapFragment = childFragmentManager.findFragmentById(R.id.google_map) as? SupportMapFragment

        //Asybc Map
        MapFragment?.getMapAsync(this)

        return View
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            mMap = googleMap
        }
        //Settings del Mapa
        mMap.getUiSettings().setAllGesturesEnabled(true)
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.getUiSettings().setCompassEnabled(true)
        mMap.getUiSettings().setCompassEnabled(true)

        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123);
        }

        //Trazar ruta
        /*
        fusedLocationClient.lastLocation.addOnSuccessListener(activity!!) { location ->

            if (location != null){

                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 13f))

                var latitud_user = location.latitude
                var longitude_user = location.longitude

                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(currentLatLong)
                        .title("Yo estoy Aqui")
                )

                val queue = Volley.newRequestQueue(activity)
                val url = "https://maps.googleapis.com/maps/api/directions/json?origin="+latitud_user+","+longitude_user+"&destination="+latitud_rest+","+longitud_rest+"&key=AIzaSyAigjbJqJv4v3keEt21VUVAZzsaM-z2Yl4"
                val jsonObjectRequest = JsonObjectRequest(url, null,
                    Response.Listener { response ->
                        try {
                            jso = response
                            trazarRuta(jso!!)
                            Log.i("jsonRuta: ", "" + response)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    Response.ErrorListener {
                        Toast.makeText(
                            activity,
                            "Problemas al obtener datos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
                queue.add(jsonObjectRequest)

            }

        }*/

        //var latitud_rest_cord = latitud_rest.toDouble()
        //var longitud_rest_cord = longitud_rest.toDouble()

        coordenada = LatLng( -16.3930348 , -71.5367858 )
        marcadorDestino = mMap.addMarker(
            MarkerOptions()
                .position(coordenada)
                .title("Restaurante: prueba")
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 13f))

    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun trazarRuta(jso: JSONObject) {
        val jRoutes: JSONArray
        var jLegs: JSONArray
        var jSteps: JSONArray
        try {
            jRoutes = jso.getJSONArray("routes")
            for (i in 0 until jRoutes.length()) {
                jLegs = (jRoutes[i] as JSONObject).getJSONArray("legs")
                for (j in 0 until jLegs.length()) {
                    jSteps = (jLegs[j] as JSONObject).getJSONArray("steps")
                    for (k in 0 until jSteps.length()) {
                        val polyline = "" + ((jSteps[k] as JSONObject)["polyline"] as JSONObject)["points"]
                        Log.i("end", "" + polyline)
                        val list = PolyUtil.decode(polyline)
                        mMap.addPolyline(PolylineOptions().addAll(list).color(Color.CYAN).width(10f))
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}