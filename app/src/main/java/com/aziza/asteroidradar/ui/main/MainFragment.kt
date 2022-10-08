package com.aziza.asteroidradar.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aziza.asteroidradar.databinding.FragmentMainBinding
import com.aziza.asteroidradar.model.Asteroid
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import timber.log.Timber

class MainFragment : Fragment(), IOnCLLickListener {
    private var _binding: FragmentMainBinding? = null
    private val mainViewModel: MainViewModel by viewModels()
    private val adapter by lazy {
        MainAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Timber.e("Welcome")
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        getAllAsteroid()
    }

    private fun getAllAsteroid() {
      // mainViewModel.
    }

    private fun setUpRecyclerView() {
        _binding!!.asteroidRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            val scaleAdapter = setUpAnimation()
            adapter = scaleAdapter
        }
    }

    private fun RecyclerView.setUpAnimation(): ScaleInAnimationAdapter {
        val alphaAdapter = AlphaInAnimationAdapter(adapter!!)
        val scaleAdapter = ScaleInAnimationAdapter(alphaAdapter).apply {
            setDuration(500)
            setFirstOnly(false)
        }
        return scaleAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClicked(asteroid: Asteroid) {
        navigateToDetails(asteroid)
    }

    private fun navigateToDetails(asteroid: Asteroid) {
        val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(asteroid)
        findNavController().navigate(action)
    }
}