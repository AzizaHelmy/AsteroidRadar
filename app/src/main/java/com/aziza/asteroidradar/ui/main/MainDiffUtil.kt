package com.aziza.asteroidradar.ui.main

import androidx.recyclerview.widget.DiffUtil

class MainDiffUtil<Item : IDiffUtil> : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem.getUniqueIdentifier() == newItem.getUniqueIdentifier()

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem.getContent() == newItem.getContent()

    companion object {
        fun <Item : IDiffUtil> getInstance(): MainDiffUtil<Item> {
            return MainDiffUtil()
        }

    }
}