package com.miempresa.tecfoodv5.fragmentos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.tecfoodv5.R
import com.miempresa.tecfoodv5.Adapters.restaurant_adapter
import com.miempresa.tecfoodv5.Connections.ApiService
import com.miempresa.tecfoodv5.Models.Restaurante
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [listas_restaurantes.newInstance] factory method to
 * create an instance of this fragment.
 */
class listas_restaurantes : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view: View = inflater.inflate(R.layout.fragment_listas_restaurantes, container, false)
        val RVrestaurantes = view.findViewById<RecyclerView>(R.id.rvrestaurantes)

        //Carga de Layout de RecyclerView
        RVrestaurantes.layoutManager = LinearLayoutManager(activity)

        //Obtener datos de la Api (Restaurantes)
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.urlApi))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create<ApiService>(ApiService::class.java)

        service.getAllRestaurants().enqueue(object : Callback<List<Restaurante>>{
            override fun onResponse(
                call: Call<List<Restaurante>>,
                response: retrofit2.Response<List<Restaurante>>
            ) {
                val restaurants = response.body()
                RVrestaurantes.adapter = restaurant_adapter(restaurants as ArrayList<Restaurante>)
            }

            override fun onFailure(call: Call<List<Restaurante>>, t: Throwable) {
                t.printStackTrace()
            }

        })


        //rvrestaurantes.adapter = restaurant_adapter(restaurantes)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment listas_restaurantes.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            listas_restaurantes().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


