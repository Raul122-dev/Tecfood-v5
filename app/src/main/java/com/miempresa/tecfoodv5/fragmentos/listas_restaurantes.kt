package com.miempresa.tecfoodv5.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.tecfoodv5.R
import com.miempresa.tecfoodv5.Restaurantes.Restaurantes
import com.miempresa.tecfoodv5.Restaurantes.restaurant_adapter

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

        var rvrestaurantes = view.findViewById<RecyclerView>(R.id.rvrestaurantes)

        var rest_nuevo =
            Restaurantes("Nueva Palomino", "Yanahuara", 5.0,
                "https://images.vexels.com/media/users/3/190869/isolated/preview/68208da69d14a72dd02e4e413866ed6f-edificio-ilustraci-oacute-n-plana-restaurante-by-vexels.png")
        var rest_nuevo1 =
            Restaurantes("Nueva Palomino", "Yanahuara", 5.0,
                "https://images.vexels.com/media/users/3/190869/isolated/preview/68208da69d14a72dd02e4e413866ed6f-edificio-ilustraci-oacute-n-plana-restaurante-by-vexels.png")
        var rest_nuevo2 =
            Restaurantes("Nueva Palomino", "Yanahuara", 5.0,
                "https://images.vexels.com/media/users/3/190869/isolated/preview/68208da69d14a72dd02e4e413866ed6f-edificio-ilustraci-oacute-n-plana-restaurante-by-vexels.png")
        var rest_nuevo3 =
            Restaurantes("Nueva Palomino", "Yanahuara", 5.0,
                "https://images.vexels.com/media/users/3/190869/isolated/preview/68208da69d14a72dd02e4e413866ed6f-edificio-ilustraci-oacute-n-plana-restaurante-by-vexels.png")
        var rest_nuevo4 =
            Restaurantes("Nueva Palomino", "Yanahuara", 5.0,
                "https://images.vexels.com/media/users/3/190869/isolated/preview/68208da69d14a72dd02e4e413866ed6f-edificio-ilustraci-oacute-n-plana-restaurante-by-vexels.png")
        var rest_nuevo5 =
            Restaurantes("Nueva Palomino", "Yanahuara", 5.0,
                "https://images.vexels.com/media/users/3/190869/isolated/preview/68208da69d14a72dd02e4e413866ed6f-edificio-ilustraci-oacute-n-plana-restaurante-by-vexels.png")

        val restaurantes = ArrayList<Restaurantes>()
        restaurantes.add(rest_nuevo)
        restaurantes.add(rest_nuevo1)
        restaurantes.add(rest_nuevo2)
        restaurantes.add(rest_nuevo3)
        restaurantes.add(rest_nuevo4)
        restaurantes.add(rest_nuevo5)

        rvrestaurantes.layoutManager = LinearLayoutManager(activity)
        rvrestaurantes.adapter = restaurant_adapter(restaurantes)

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