package com.mrienaldi.myband.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.mrienaldi.myband.BottomNavigationActivity
import com.mrienaldi.myband.core.data.source.remote.network.State
import com.mrienaldi.myband.core.data.source.remote.request.LoginRequest
import com.mrienaldi.myband.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel : AuthViewModel by viewModel()

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setData()
        mainButton()
    }

    private fun mainButton() {

        binding.btnMasuk.setOnClickListener {
            login()
        }

        binding.btnSignUp.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun setData(){
    }

    private fun login(){

        if (binding.edtUsremail.isEmpty()) return
        if (binding.edtPassword.isEmpty()) return

        val body = LoginRequest(binding.edtUsremail.text.toString(), binding.edtPassword.text.toString())

        viewModel.login(body).observe(this, {

            when (it.state){
                State.SUCCESS->{
                    binding.pd.visibility = View.GONE
                    Toast.makeText(this, "Selamat Datang " + (it?.data?.name), Toast.LENGTH_SHORT).show()
                    pushActivity(BottomNavigationActivity::class.java)
                }
                State.LOADING->{
                    binding.pd.visibility = View.VISIBLE
                }
                State.ERROR->{
                    binding.pd.visibility = View.GONE
                    Toast.makeText(this, it.message ?: "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}