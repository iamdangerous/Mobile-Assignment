package com.rahul.sd2test.repository

import com.rahul.sd2test.webService.ApiService

class UserRepositoryImpl(val apiService: ApiService) : UserRepository {
    override fun getUsers(offset: Int, limit: Int) {
        apiService.getUsers()
    }


}