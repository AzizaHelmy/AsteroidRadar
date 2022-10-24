package com.aziza.asteroidradar.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aziza.asteroidradar.databinding.ItemAsteroidRvBinding
import com.aziza.asteroidradar.model.Asteroid

class MainAdapter(val onClickListener: IOnCLLickListener) :
    ListAdapter<Asteroid, MainAdapter.MainViewHolder>(MainDiffUtil.getInstance()) {

    override fun submitList(list: List<Asteroid>?) {
        super.submitList(list)
        Log.e("TAG", "submitList:${list?.size} ")
    }

    override fun getItemCount(): Int {
       val size= super.getItemCount()
        Log.e("TAG", "getItemCount:$size " )
        return size
    }

    inner class MainViewHolder(private val binding: ItemAsteroidRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(asteroid: Asteroid) {
            binding.asteroid = asteroid
            Log.e("TAG", "bind: ")
            binding.itemLayout.setOnClickListener {
                onClickListener.onItemClicked(asteroid)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        Log.e("TAG", "onCreateViewHolder: ")
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
        Log.e("TAG", "uuuu ")

    }
}