package com.rahul.sd2test.repository

import com.rahul.sd2test.modal.UsersResponse

interface GetUserCallback{
    fun onSuccess(usersResponse: UsersResponse)
    fun onFail()
}