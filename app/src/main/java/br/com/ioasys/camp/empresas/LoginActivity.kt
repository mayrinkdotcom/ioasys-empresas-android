package br.com.ioasys.camp.empresas

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity () {
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
        button = findViewById(R.id.btnLogin)
        Log.i("LoginActivity", "onCreate")

        button.setOnClickListener {
            Log.i("LoginActivity", "Botão clicado")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("LoginActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LoginActivity", "LoginActivity entrou em onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LoginActivity", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LoginActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LoginActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LoginActivity", "onDestroy")
    }
}