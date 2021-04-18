package com.github.ddnosh.arabbit.sample.jetpack.room_paging

import android.app.Application
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.ddnosh.arabbit.ext.launchIO
import com.github.ddnosh.arabbit.jetpack.viewmodel.BaseAndroidViewModel

class UserAndroidViewModel(application: Application) : BaseAndroidViewModel(application) {

    val dao = UserDb.get(application).userDao()
    var idNum = 1

    companion object {
        private const val PAGE_SIZE = 10
    }

    val users = LivePagedListBuilder(
            dao.getAllUser(), PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    ).build()

    fun add() = launchIO({
        dao.insert(newTenUser())
    })

    private fun newTenUser(): ArrayList<User> {
        val newUsers = ArrayList<User>()
        for (index in 1..10) {
            newUsers.add(User(0, "bob${idNum++}"))
        }
        return newUsers
    }
}