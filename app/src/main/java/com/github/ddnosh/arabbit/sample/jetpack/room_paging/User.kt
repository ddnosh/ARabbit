package com.github.ddnosh.arabbit.sample.jetpack.room_paging

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey(autoGenerate = true) val id: Int,
                val name: String)