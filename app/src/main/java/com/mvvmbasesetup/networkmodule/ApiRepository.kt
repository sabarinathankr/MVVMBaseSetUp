package com.mvvmbasesetup.networkmodule

import androidx.lifecycle.LiveData

interface ApiRepository {
    fun test(): LiveData<Result<String>>
}