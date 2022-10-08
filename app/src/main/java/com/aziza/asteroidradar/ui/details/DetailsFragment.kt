package com.aziza.asteroidradar.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aziza.asteroidradar.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args != null) {
            _binding!!.apply {
                absoluteMagnitude.text = args.currentAsteroid.absoluteMagnitude.toString()
                // Picasso.get().load(args.currentAsteroid).into(activityMainImageOfTheDay)
                closeApproachDate.text = args.currentAsteroid.closeApproachDate
                estimatedDiameter.text = args.currentAsteroid.estimatedDiameter.toString()
                relativeVelocity.text = args.currentAsteroid.relativeVelocity.toString()
                distanceFromEarth.text = args.currentAsteroid.distanceFromEarth.toString()

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}