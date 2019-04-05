package com.rahul.sd2test.webService

import com.rahul.sd2test.modal.UsersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("/api/users")
    fun getUsers(@QueryMap options: Map<String, String>): Single<UsersResponse>
}