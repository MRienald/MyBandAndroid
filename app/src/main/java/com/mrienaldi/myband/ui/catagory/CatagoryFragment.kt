package com.mrienaldi.myband.ui.catagory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mrienaldi.myband.databinding.FragmentCatagoryBinding
import com.mrienaldi.myband.ui.catagory.adapter.CatagoryAdapter
import com.mrienaldi.myband.ui.home.HomeViewModel
import com.mrienaldi.myband.ui.home.adapter.ProductAdapterDefault

class CatagoryFragment : Fragment() {

    private var _binding: FragmentCatagoryBinding? = null

    private lateinit var viewModel: CatagoryViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapterCategory = CatagoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CatagoryViewModel::class.java)

        _binding = FragmentCatagoryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        setupAdapter()
        setData()
        return root
    }

    private fun setupAdapter(){
        binding.rvCatagory.adapter   = adapterCategory
    }

    private fun setData(){

        viewModel.listCatagory.observe(requireActivity(), {
            adapterCategory.addItems(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}