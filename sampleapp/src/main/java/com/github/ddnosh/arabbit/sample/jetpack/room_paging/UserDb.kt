package com.github.ddnosh.arabbit.sample.jetpack.room_paging

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var instance: UserDb? = null

        @Synchronized
        fun get(context: Context): UserDb {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDb::class.java, "database-user"
                ).build()
            }
            return instance!!
        }
    }
}
