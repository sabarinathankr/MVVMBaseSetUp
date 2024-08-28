package com.mvvmbasesetup.networkmodule

import androidx.lifecycle.LiveData

class ApiLiveDataSource :APICallBacks(),  ApiRepository {
    private  var urlFactory: URLFactory = RetrofitHelper.getApiInstance()

    override fun test(): LiveData<Result<String>> {
      return  rawResponse(urlFactory.test())
    }
}