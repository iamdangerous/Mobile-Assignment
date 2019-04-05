package com.rahul.sd2test.repository

import android.annotation.SuppressLint
import android.util.Log
import com.rahul.sd2test.webService.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class UserRepositoryImpl(val apiService: ApiService) : UserRepository {

    @SuppressLint("CheckResult")
    override fun getUsers(offset: Int, limit: Int, callback: GetUserCallback) {
        val map = HashMap<String, String>()
        map["offset"] = offset.toString()
        map["limit"] = limit.toString()
        apiService.getUsers(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onSuccess()
                Log.d("Hello", "Success")
            }, {
                callback.onFail()
                Log.d("Hello", "Success")
            })
    }


}