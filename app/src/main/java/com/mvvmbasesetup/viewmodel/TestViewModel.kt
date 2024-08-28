package com.mvvmbasesetup.viewmodel

import androidx.lifecycle.LiveData
import com.mvvmbasesetup.base.BaseViewModel
import com.mvvmbasesetup.networkmodule.ApiLiveDataSource
import com.mvvmbasesetup.networkmodule.ApiRepository

class TestViewModel : BaseViewModel() {

    private val apiLiveDataSource = ApiLiveDataSource()


    fun test() : LiveData<Result<String>> {
        return apiLiveDataSource.test()
    }
}