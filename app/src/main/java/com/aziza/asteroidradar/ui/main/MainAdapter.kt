package com.aziza.asteroidradar.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aziza.asteroidradar.R
import com.aziza.asteroidradar.databinding.ItemAsteroidRvBinding
import com.aziza.asteroidradar.model.Asteroid

class MainAdapter(val onClickListener: IOnCLLickListener) :
    ListAdapter<Asteroid, MainAdapter.MainViewHolder>(MainDiffUtil.getInstance()) {

    inner class MainViewHolder(private val binding: ItemAsteroidRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(asteroid: Asteroid) {
            binding.asteroid=asteroid
//            binding.nameTv.text = asteroid.codename
//            binding.dateTv.text = asteroid.closeApproachDate
//            binding.iconIv.setImageResource(R.drawable.asteroid_safe)
            binding.itemLayout.setOnClickListener {
                onClickListener.onItemClicked(asteroid)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemAsteroidRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}