package com.aziza.asteroidradar.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aziza.asteroidradar.ui.main.IDiffUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Asteroid(
    @PrimaryKey val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
) : Parcelable, IDiffUtil {
    override fun getUniqueIdentifier(): Any = id

    override fun getContent(): String = toString()
}