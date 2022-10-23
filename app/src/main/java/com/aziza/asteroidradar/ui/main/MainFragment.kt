package com.aziza.asteroidradar.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aziza.asteroidradar.R
import com.aziza.asteroidradar.databinding.FragmentMainBinding
import com.aziza.asteroidradar.model.Asteroid

class MainFragment : Fragment(), IOnCLLickListener {
    private var _binding: FragmentMainBinding? = null

    private val mainViewModel: MainViewModel by viewModels() {
        MainViewModelFactory(requireContext())
    }
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MainAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(layoutInflater)

        setHasOptionsMenu(true)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        getAllAsteroid()
    }

    @SuppressLint("LogNotTimber")
    private fun getAllAsteroid() {
        Log.e("TAG", " view ")
        mainViewModel.asteroidFilter.observe(viewLifecycleOwner) {
            Log.e("TAG", " view2 ")
            mainViewModel.asteroidResult.observe(viewLifecycleOwner) {
                Log.e("TAG", " view$it ")
                adapter.submitList(it)
            }
        }

    }

    private fun setUpRecyclerView() {
        _binding!!.asteroidRecycler.adapter = adapter
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.week_asteroids -> mainViewModel.getAsteroidONextWeek()
            R.id.today_asteroids -> mainViewModel.getAsteroidOfToday()
            R.id.saved_asteroids -> mainViewModel.getSavedAsteroid()
        }
        return true
    }
}