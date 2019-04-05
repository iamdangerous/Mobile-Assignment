package com.rahul.sd2test.repository

interface UserRepository {

    fun getUsers(offset:Int, limit:Int, callback: GetUserCallback)
}