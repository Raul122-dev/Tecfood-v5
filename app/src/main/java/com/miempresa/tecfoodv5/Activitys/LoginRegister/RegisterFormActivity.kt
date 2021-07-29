package com.miempresa.tecfoodv5.Activitys.LoginRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import com.miempresa.tecfoodv5.Activitys.MainActivity
import com.miempresa.tecfoodv5.Connections.*
import com.miempresa.tecfoodv5.R
import kotlinx.android.synthetic.main.activity_register_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Unit.toString

class RegisterFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_form)

        btn_register.setOnClickListener(){
            if( TextUtils.isEmpty(txt_username.getText().toString()) ||
                TextUtils.isEmpty(txt_email.getText().toString()) ||
                TextUtils.isEmpty(txt_nombres.getText().toString()) ||
                TextUtils.isEmpty(txt_apellidos.getText().toString()) ||
                TextUtils.isEmpty(txt_contraseña.getText().toString()) ||
                TextUtils.isEmpty(txt_confirm.getText().toString())){

                var message = "Campos Requeridos";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            }else{

                var registerRequest = RegisterRequest()
                registerRequest.username = txt_username.getText().toString();
                registerRequest.first_name = txt_nombres.getText().toString();
                registerRequest.last_name = txt_apellidos.getText().toString();
                registerRequest.email = txt_email.getText().toString();
                registerRequest.password = txt_contraseña.getText().toString();

                registerUser(registerRequest);


            }
        }
    }

    fun registerUser(registerRequest: RegisterRequest) {
        val registerResponseCall: Call<RegisterResponse> = ApiLogueo().getService().registerUsers(registerRequest)
        registerResponseCall.enqueue(object : Callback<RegisterResponse> {

            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if(response.isSuccessful()){

                    val registerResponse : RegisterResponse? = response.body()
                    val id_user = registerResponse?.user?.id

                    val intent = Intent(this@RegisterFormActivity, RegisterPerfilActivity::class.java).putExtra("id_user", id_user)
                    startActivity(intent)
                    finish()


                    if (registerResponse != null) {
                        Log.i("Post", registerResponse.user.id.toString() +" and " + registerResponse.user.email )
                    }

                }else{
                    var message = "Un error a ocurrido";
                    Toast.makeText(this@RegisterFormActivity, message, Toast.LENGTH_SHORT).show();
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                var message:String = t.getLocalizedMessage();
                Toast.makeText(this@RegisterFormActivity, message, Toast.LENGTH_SHORT).show();
            }
        })

        // val Api = ApiLogueo()


        //Api.getService().registerUsers()
    }

}