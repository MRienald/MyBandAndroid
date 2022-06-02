package com.mrienaldi.myband.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.inyongtisto.myhelper.extension.isEmpty
import com.mrienaldi.myband.core.data.source.remote.network.State
import com.mrienaldi.myband.core.data.source.remote.request.LoginRequest
import com.mrienaldi.myband.databinding.ActivityLoginBinding
import com.mrienaldi.myband.util.Prefs
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel : LoginViewModel by viewModel()

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setData()
    }

    private fun setData(){
//        viewModel.text.observe(this, {
//            binding.edtUsremail.setText(it)
//        })

        binding.btnMasuk.setOnClickListener {
            login()
        }
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