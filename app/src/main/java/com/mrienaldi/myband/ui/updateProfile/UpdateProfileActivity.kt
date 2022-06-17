package com.mrienaldi.myband.ui.updateProfile

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.github.drjacky.imagepicker.ImagePicker
import com.inyongtisto.myhelper.extension.isEmpty
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.toMultipartBody
import com.mrienaldi.myband.BottomNavigationActivity
import com.mrienaldi.myband.core.data.source.remote.network.State
import com.mrienaldi.myband.core.data.source.remote.request.LoginRequest
import com.mrienaldi.myband.core.data.source.remote.request.RegisterRequest
import com.mrienaldi.myband.core.data.source.remote.request.UpdateProfileRequest
import com.mrienaldi.myband.databinding.ActivityLoginBinding
import com.mrienaldi.myband.databinding.ActivityRegisterBinding
import com.mrienaldi.myband.databinding.ActivityUpdateProfileBinding
import com.mrienaldi.myband.ui.auth.AuthViewModel
import com.mrienaldi.myband.util.Constants
import com.mrienaldi.myband.util.Prefs
import com.mrienaldi.myband.util.Prefs.user
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class UpdateProfileActivity : AppCompatActivity() {

    private val viewModel : AuthViewModel by viewModel()

    private var _binding: ActivityUpdateProfileBinding? = null
    private val binding get() = _binding!!
    private var fileImage:File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.toolbar, "Update Profile")

        mainButton()
        setData()
    }

    private fun setData(){
        val user = Prefs.getUser()
        if (user != null){
            binding.apply {
                edtUsername.setText(user.username)
                edtName.setText(user.name)
                edtEmail.setText(user.email)
                edtPhone.setText(user.phone)
                edtBirthday.setText(user.birthday)
                edtAddress.setText(user.address)

                Picasso.get().load(Constants.USER_URL + user.image).into(binding.imgProfile)
            }
        }
    }

    private fun mainButton(){
        binding.btnSimpan.setOnClickListener {
            if (fileImage != null) {
                upload()
            }else {
                update()
            }
        }

        binding.imgProfile.setOnClickListener {
            pickImage()
        }
    }

    private fun pickImage(){
        ImagePicker.with(this)
            .crop()
            .maxResultSize(1080, 1080, true)
            .createIntentFromDialog { launcher.launch(it) }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data!!
            fileImage = File(uri.path!!)
            // Use the uri to load the image
            Picasso.get().load(fileImage!!).into(binding.imgProfile)
        }
    }

    private fun update(){

        if (binding.edtUsername.isEmpty()) return
        if (binding.edtName.isEmpty()) return
        if (binding.edtEmail.isEmpty()) return
        if (binding.edtPhone.isEmpty()) return
        if (binding.edtBirthday.isEmpty()) return
        if (binding.edtAddress.isEmpty()) return

        val idUser  = Prefs.getUser()?.id
        val body    = UpdateProfileRequest(
            idUser ?: 0,
            binding.edtUsername.text.toString(),
            binding.edtName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPhone.text.toString(),
            binding.edtBirthday.text.toString(),
            binding.edtAddress.text.toString(),

        )

        viewModel.updateUser(body).observe(this, {

            when (it.state){
                State.SUCCESS->{
                    binding.pd.visibility = View.GONE
//                    Toast.makeText(this, "Selamat Datang " + (it?.data?.name), Toast.LENGTH_SHORT).show()
                    onBackPressed()
//                    pushActivity(LoginActivity::class.java)
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

    private fun upload(){

        val idUser = Prefs.getUser()?.id
        val file = fileImage.toMultipartBody()

        viewModel.uploadUser(idUser, file).observe(this, {

            when (it.state){
                State.SUCCESS->{
//                    binding.pd.visibility = View.GONE
                    update()
//                    pushActivity(LoginActivity::class.java)
                }
                State.LOADING->{
                    binding.pd.visibility = View.VISIBLE
                }
                State.ERROR->{
//                    binding.pd.visibility = View.GONE
                    Toast.makeText(this, it.message ?: "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}