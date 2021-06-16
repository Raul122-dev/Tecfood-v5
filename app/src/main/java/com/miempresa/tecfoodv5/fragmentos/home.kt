package com.miempresa.tecfoodv5.fragmentos

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.WindowDecorActionBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.tecfoodv5.Adapters.Restaurante_user
import com.miempresa.tecfoodv5.Connections.ApiService
import com.miempresa.tecfoodv5.Models.Restaurante
import com.miempresa.tecfoodv5.R
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_home.*
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
 * Use the [home.newInstance] factory method to
 * create an instance of this fragment.
 */
class home : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val Rc_user = view.findViewById<RecyclerView>(R.id.Rc_user)

        Rc_user.layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL, false)

        //Obtener datos de la Api (Restaurantes)
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.urlApi))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create<ApiService>(ApiService::class.java)



        service.getAllRestaurants().enqueue(object : Callback<List<Restaurante>> {
            override fun onResponse(
                call: Call<List<Restaurante>>,
                response: retrofit2.Response<List<Restaurante>>
            ) {
                val restaurants = response.body()
                Rc_user.adapter = Restaurante_user(restaurants as ArrayList<Restaurante>)

                //Log.i("mensaje", Gson().toJson(restaurants))
            }

            override fun onFailure(call: Call<List<Restaurante>>, t: Throwable) {
                t.printStackTrace()
            }

        })

        //val layout = view.findViewById<View>(R.id.blur_fondo)

        //blurView = view.findViewById<BlurView>(R.id.blur_fondo)


        //layout.background.setAlpha(100)

        return view
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}