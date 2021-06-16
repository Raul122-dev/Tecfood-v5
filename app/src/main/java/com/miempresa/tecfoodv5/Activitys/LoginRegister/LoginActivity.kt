package com.miempresa.tecfoodv5.Activitys.LoginRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miempresa.tecfoodv5.Activitys.MainActivity
import com.miempresa.tecfoodv5.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txt_login_invitado.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        login_phone.setOnClickListener(){
            val intent = Intent(this, RegisterPhoneActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}