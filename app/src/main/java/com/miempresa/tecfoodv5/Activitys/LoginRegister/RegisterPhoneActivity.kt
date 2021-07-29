package com.miempresa.tecfoodv5.Activitys.LoginRegister

import android.app.PendingIntent.getService
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.miempresa.tecfoodv5.Activitys.MainActivity
import com.miempresa.tecfoodv5.Connections.ApiLogueo
import com.miempresa.tecfoodv5.Connections.LoginRequest
import com.miempresa.tecfoodv5.Connections.LoginResponse
import com.miempresa.tecfoodv5.R
import kotlinx.android.synthetic.main.activity_register_phone.*
import retrofit2.Call;
import retrofit2.Callback
import retrofit2.Response

class RegisterPhoneActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_phone)



        btn_login.setOnClickListener(){
            if(TextUtils.isEmpty(txt_user.getText().toString())|| TextUtils.isEmpty(txt_contraseña.getText().toString())){
                var message = "Campos Requeridos";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }else{
                var loginRequest = LoginRequest()
                loginRequest.username = txt_user.getText().toString();
                loginRequest.password = txt_contraseña.getText().toString();

                loginUser(loginRequest);
            }
        }

    }
    fun loginUser(loginRequest: LoginRequest) {

        val loginResponseCall: Call<LoginResponse> = ApiLogueo().getService().loginUser(loginRequest)
        loginResponseCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful()){

                    val loginResponse : LoginResponse? = response.body()

                    val intent = Intent(this@RegisterPhoneActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    var message = "Un error a ocurrido";
                    Toast.makeText(this@RegisterPhoneActivity, message, Toast.LENGTH_SHORT).show();
                }

            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                var message:String = t.getLocalizedMessage();
                Toast.makeText(this@RegisterPhoneActivity, message, Toast.LENGTH_SHORT).show();
            }
        })

    }

}
