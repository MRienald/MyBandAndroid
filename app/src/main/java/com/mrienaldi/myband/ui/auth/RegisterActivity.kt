package com.mrienaldi.myband.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.mrienaldi.myband.BottomNavigationActivity
import com.mrienaldi.myband.core.data.source.remote.network.State
import com.mrienaldi.myband.core.data.source.remote.request.LoginRequest
import com.mrienaldi.myband.core.data.source.remote.request.RegisterRequest
import com.mrienaldi.myband.databinding.ActivityLoginBinding
import com.mrienaldi.myband.databinding.ActivityRegisterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel : AuthViewModel by viewModel()

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setData()
    }

    private fun setData(){
//        viewModel.text.observe(this, {
//            binding.edtUsremail.setText(it)
//        })

        binding.btnSignin.setOnClickListener {
            register()
        }
    }

    private fun register(){

        if (binding.edtUsername.isEmpty()) return
        if (binding.edtName.isEmpty()) return
        if (binding.edtEmail.isEmpty()) return
        if (binding.edtPhone.isEmpty()) return
        if (binding.edtBirthday.isEmpty()) return
        if (binding.edtAddress.isEmpty()) return
        if (binding.edtPassword.isEmpty()) return
        if (binding.edtRepassword.isEmpty()) return


        val body = RegisterRequest(
            binding.edtUsername.text.toString(),
            binding.edtName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPhone.text.toString(),
            binding.edtBirthday.text.toString(),
            binding.edtAddress.text.toString(),
            binding.edtPassword.text.toString()
        )

        viewModel.register(body).observe(this, {

            when (it.state){
                State.SUCCESS->{
                    binding.pd.visibility = View.GONE
                    Toast.makeText(this, "Selamat Datang " + (it?.data?.name), Toast.LENGTH_SHORT).show()
                    pushActivity(LoginActivity::class.java)
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