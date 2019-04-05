package com.rahul.sd2test.repository

import android.annotation.SuppressLint
import com.rahul.sd2test.modal.UsersResponse
import com.rahul.sd2test.webService.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class UserRepositoryImpl(val apiService: ApiService) : UserRepository {

    @SuppressLint("CheckResult")
    override fun getUsers(offset: Int, limit: Int, callback: GetUserCallback): Disposable {
        val map = HashMap<String, String>()
        map["offset"] = offset.toString()
        map["limit"] = limit.toString()
        return apiService.getUsers(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onSuccess(it)
            }, {
                callback.onFail()
            })
    }


}