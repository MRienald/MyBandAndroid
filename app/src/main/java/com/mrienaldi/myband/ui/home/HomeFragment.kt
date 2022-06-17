package com.mrienaldi.myband.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.mrienaldi.myband.R
import com.mrienaldi.myband.ui.home.adapter.ImageSlideAdapter
import com.mrienaldi.myband.core.data.source.model.ImageData
import com.mrienaldi.myband.databinding.FragmentHomeBinding
import com.mrienaldi.myband.ui.home.adapter.ProductAdapter
import com.mrienaldi.myband.ui.home.adapter.ProductAdapterDefault
import com.mrienaldi.myband.util.Constants
import com.mrienaldi.myband.util.Prefs
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: ImageSlideAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val adapterProduct = ProductAdapter()
    private val adapterProductDefault = ProductAdapterDefault()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable{
            var index = -1
            override fun run() {
                if (index == list.size) {
                    index = 0
                }
                Log.e("Runnable,", "$index")
                binding.viewpager.setCurrentItem(index)
                index++
                handler.postDelayed(this, 4000)
            }

        }

        list.add(
            ImageData(
                "http://192.168.0.106/myband/public/storage/banner/bannerA.JPG"
            )
        )

        list.add(
            ImageData(
                "http://192.168.0.106/myband/public/storage/banner/bannerB.JPG"
            )
        )

        list.add(
            ImageData(
                "http://192.168.0.106/myband/public/storage/banner/bannerC.JPG"
            )
        )

        list.add(
            ImageData(
                "http://192.168.0.106/myband/public/storage/banner/bannerD.JPG"
            )
        )

        list.add(
            ImageData(
                "http://192.168.0.106/myband/public/storage/banner/banner5.JPG"
            )
        )

        adapter = ImageSlideAdapter(list)
        binding.viewpager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })

        handler.post(runnable)

        setupAdapter()
        setData()
        setUser()

        return root
    }

    private fun setUser(){
        val user = Prefs.getUser()
        if (user != null){
            binding.apply {
                tvUsername.text = user.username
                Picasso.get().load(Constants.USER_URL + user.image).into(binding.imgProfile)
            }
        }
    }

    private fun setupAdapter(){
        binding.rvRelate.adapter    = adapterProduct
        binding.rvHots.adapter      = adapterProduct
        binding.rvDefault.adapter   = adapterProductDefault
    }

    private fun setData(){
        viewModel.listProduct.observe(requireActivity(), {
            adapterProduct.addItems(it)
        })

        viewModel.listProduct.observe(requireActivity(), {
            adapterProductDefault.addItems(it)
        })

    }

    private fun selectedDot(position: Int) {
        for(i in 0 until list.size){
            if (i == position){
                dots[i].setTextColor(ContextCompat.getColor(this.requireContext(), R.color.hard_purple))
            } else{
                dots[i].setTextColor(ContextCompat.getColor(this.requireContext(), R.color.white))
            }
        }
    }

    private fun setIndicator() {
        for (i in 0 until list.size){
            dots.add(TextView(this.requireContext()))
            dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()

            dots[i].textSize = 20f
            binding.dotsIndicator.addView(dots[i])
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        handler.post(runnable)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
}