package com.miempresa.tecfoodv5.Connections

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.miempresa.tecfoodv5.Models.Restaurante
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api_Restaurantes {

    val url: String = "https://www.tecfood.club/74054946816/api/Restaurante/"

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://www.tecfood.club/74054946816/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun Restaurantes_all(context_view: Context) {

        Toast.makeText(context_view, "Obteniendo datos ", Toast.LENGTH_LONG).show()

        val queue = Volley.newRequestQueue(context_view)

        var Restaurantes_all = ArrayList<Restaurante>()

        val StringRequest = JsonArrayRequest(url,
            Response.Listener { response ->
                try {
                    for (i in 0 until response.length()){
                        val id = response.getJSONObject(i).getString("id")
                        val usuario = response.getJSONObject(i).getString("usuario")
                        val DNI = response.getJSONObject(i).getString("DNI")
                        val nombrerest = response.getJSONObject(i).getString("nombre_rest")
                        val ubicacion = response.getJSONObject(i).getString("ubicacion")
                        val direccion = response.getJSONObject(i).getString("direccion")
                        val descripcion = response.getJSONObject(i).getString("descripcion")
                        val contacto = response.getJSONObject(i).getString("contacto")
                        val puntuacion = response.getJSONObject(i).getString("puntuacion")
                        val latitud = response.getJSONObject(i).getString("latitude")
                        val longitud = response.getJSONObject(i).getString("longitude")
                        val creado = response.getJSONObject(i).getString("creado")
                        val imagen = response.getJSONObject(i).getString("vista")

                        Restaurantes_all.add(
                            Restaurante(id.toInt(), usuario.toInt(), DNI, nombrerest, ubicacion, direccion, descripcion, contacto, puntuacion, latitud, longitud, creado, imagen)
                        )
                    }

                } catch (e: JSONException) {
                    Toast.makeText(context_view,"Error al obtener los datos " + e, Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener {
                Toast.makeText(context_view,"Verifique que esta conectado a internet$it", Toast.LENGTH_LONG).show()
            }

        )

        queue.add(StringRequest)

    }

    fun getAllRestaurants(){



        val service = retrofit.create<ApiService>(ApiService::class.java)
        /*
        service.getAllRestaurants().enqueue(object : Callback<List<Restaurante>>{
            override fun onResponse(call: Call<List<Restaurante>>?, response: Response<List<Restaurante>>?){
                val restaurants = response?.body()
                Log.i("response", Gson().toJson(restaurants))
            }
            override fun onFailure(call: Call<List<Restaurante>>?, t: Throwable?){
                t?.printStackTrace()
            }
        })*/
    }

}


