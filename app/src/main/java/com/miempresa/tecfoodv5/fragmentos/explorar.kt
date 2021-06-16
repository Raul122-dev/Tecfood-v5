package com.miempresa.tecfoodv5.fragmentos

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.tecfoodv5.Adapters.Categoria_adapter
import com.miempresa.tecfoodv5.Connections.ApiService
import com.miempresa.tecfoodv5.Models.Restaurante
import com.miempresa.tecfoodv5.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [explorar.newInstance] factory method to
 * create an instance of this fragment.
 */
class explorar : Fragment() {
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

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_explorar, container, false)
        val rc_tipos = view.findViewById<RecyclerView>(R.id.rc_tipos)

        rc_tipos.layoutManager = GridLayoutManager(activity, 2)

        //Obtener datos de la Api (Restaurantes)
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.urlApi))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create<ApiService>(ApiService::class.java)

        var ubicacion_rest: ArrayList<String> = ArrayList()

        service.getAllRestaurants().enqueue(object : Callback<List<Restaurante>> {
            override fun onResponse(
                call: Call<List<Restaurante>>,
                response: retrofit2.Response<List<Restaurante>>
            ) {
                val restaurants = response.body()
                val restaurantes = restaurants as ArrayList<Restaurante>
                //Rc_user.adapter = Restaurante_user(restaurants as ArrayList<Restaurante>)

                for (i in 0 until restaurants.size) {
                    var ubicacion = restaurants.get(i).ubicacion
                    ubicacion_rest.add(ubicacion)
                }

                rc_tipos.adapter = Categoria_adapter(ubicacion_rest)

                //Log.i("mensaje", Gson().toJson(restaurants))
            }

            override fun onFailure(call: Call<List<Restaurante>>, t: Throwable) {
                t.printStackTrace()
            }

        })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment explorar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            explorar().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}