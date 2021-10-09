package com.github.ddnosh.arabbit.sample.module.ioc.dagger2

import javax.inject.Inject

class User @Inject constructor() {
    fun study(): String {
        return "Student is studying"
    }
}
