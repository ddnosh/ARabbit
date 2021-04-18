package com.github.ddnosh.arabbit.sample.jetpack.room_paging

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable


@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAllUser(): DataSource.Factory<Int, User>

    @Query("SELECT * FROM User")
    fun getAllUser2(): LiveData<List<User>>

    @Query("SELECT * from user")
    fun getAllUser3(): Flowable<List<User>>

    @Insert
    fun insert(users: List<User>)
}