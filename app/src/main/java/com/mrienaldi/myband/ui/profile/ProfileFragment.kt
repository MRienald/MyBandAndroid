package com.mrienaldi.myband.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inyongtisto.myhelper.extension.pushActivity
import com.mrienaldi.myband.BottomNavigationActivity
import com.mrienaldi.myband.databinding.FragmentProfileBinding
import com.mrienaldi.myband.ui.updateProfile.UpdateProfileActivity
import com.mrienaldi.myband.util.Constants.USER_URL
import com.mrienaldi.myband.util.Prefs
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mainButton()
        return root
    }

    override fun onResume() {
        setUser()
        super.onResume()
    }

    private fun mainButton(){

        binding.btnLogout.setOnClickListener {
            Prefs.isLogin = false
            pushActivity(BottomNavigationActivity::class.java)
        }

        binding.btnUpdate.setOnClickListener {
            startActivity(Intent(requireActivity(), UpdateProfileActivity::class.java))
        }
    }

    private fun setUser(){
        val user = Prefs.getUser()
        if (user != null){
            binding.apply {
                tvUsername.text = user.username
                tvDate.text     = user.birthday
                tvCountry.text  = user.address

                Picasso.get().load(USER_URL + user.image).into(binding.imgProfile)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}