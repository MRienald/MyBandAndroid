package com.mrienaldi.myband.ui.catagory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mrienaldi.myband.databinding.FragmentCatagoryBinding

class CatagoryFragment : Fragment() {

    private var _binding: FragmentCatagoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val catagoryViewModel =
            ViewModelProvider(this).get(CatagoryViewModel::class.java)

        _binding = FragmentCatagoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCatagory
        catagoryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}