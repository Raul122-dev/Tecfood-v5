package com.miempresa.tecfoodv5.Activitys.LoginRegister

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.miempresa.tecfoodv5.Connections.*
import com.miempresa.tecfoodv5.R
import kotlinx.android.synthetic.main.activity_register_perfil.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.jar.Manifest

class RegisterPerfilActivity : AppCompatActivity() {

    lateinit var img_user:File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_perfil)

        var intent = intent
        val id_user = intent.getStringExtra("id_user")

        btn_img_perfil_user.setOnClickListener(){
            requestPermission()
        }

        btn_register_perfil.setOnClickListener(){
            if( TextUtils.isEmpty(txt_distrito.getText().toString()) ||
                TextUtils.isEmpty(txt_plato_fav.getText().toString()) ||
                TextUtils.isEmpty(txt_number_telef.getText().toString())){

                var message = "Campos Requeridos";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            }else{
                var registerPerfilRequest = RegisterPerfilRequest()
                if (id_user != null) {
                    registerPerfilRequest.user = id_user.toInt()
                }
                registerPerfilRequest.distrito = txt_distrito.text.toString()
                registerPerfilRequest.plato_fav = txt_plato_fav.text.toString()
                registerPerfilRequest.phone_number = txt_number_telef.text.toString()
                registerPerfilRequest.picture = img_user

                registerUserPerfil(registerPerfilRequest)
            }
        }
    }

    private fun requestPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            when{
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    pickPhotoFromGalery()
                }

                else -> requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }

        }else{
            pickPhotoFromGalery()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted->

        if(isGranted){
            pickPhotoFromGalery()
        }else{
            Toast.makeText(this, "Necesitas los permisos necesarios", Toast.LENGTH_SHORT).show()
        }

    }


    @SuppressLint("NewApi")
    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result->

        if(result.resultCode == Activity.RESULT_OK){
            val data = result.data?.data
            img_select.setImageURI(data)
            //img_user = BitmapFactory.decodeFile(data.toString())

            var source = ImageDecoder.createSource(this.contentResolver, data!!)

            img_user = File.createTempFile(data)

            Log.i("ruta", data.path.toString())
        }
    }


    private fun pickPhotoFromGalery(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityGallery.launch(intent)
    }

    fun registerUserPerfil(registerPerfilRequest: RegisterPerfilRequest) {
        /*
        val registerPerfilResponseCall: Call<RegisterPerfilResponse> = ApiLogueo().getService().registerPosts(registerPerfilRequest)
        registerPerfilResponseCall.enqueue(object : Callback<RegisterPerfilResponse> {

            override fun onResponse(call: Call<RegisterPerfilResponse>,response: Response<RegisterPerfilResponse>) {
                if(response.isSuccessful()){

                    val intent = Intent(this@RegisterPerfilActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    var message = "Un error a ocurrido";
                    Toast.makeText(this@RegisterPerfilActivity, message, Toast.LENGTH_SHORT).show();
                }
            }

            override fun onFailure(call: Call<RegisterPerfilResponse>, t: Throwable) {
                var message:String = t.getLocalizedMessage();
                Toast.makeText(this@RegisterPerfilActivity, message, Toast.LENGTH_SHORT).show();
            }
        })*/

        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.tecfood.club/")
            .build();

        var service = retrofit.create<UserService>(UserService::class.java)

        service.registerPosts(registerPerfilRequest).enqueue(object : Callback<RegisterPerfilRequest>{
            override fun onResponse(
                call: Call<RegisterPerfilRequest>,
                response: Response<RegisterPerfilRequest>
            ) {

                if(response.isSuccessful()){

                    val intent = Intent(this@RegisterPerfilActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    var message = "Un error a ocurrido";
                    Toast.makeText(this@RegisterPerfilActivity, message, Toast.LENGTH_SHORT).show();
                }

            }

            override fun onFailure(call: Call<RegisterPerfilRequest>, t: Throwable) {
                Toast.makeText(this@RegisterPerfilActivity, "salio mal", Toast.LENGTH_SHORT).show();
            }
        })

    }
}