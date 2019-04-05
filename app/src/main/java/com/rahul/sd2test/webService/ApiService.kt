package com.rahul.sd2test.webService

import retrofit2.http.GET

interface ApiService {

    @GET("/api/users")
    fun getUsers()
}