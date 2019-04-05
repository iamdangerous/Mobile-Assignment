package com.rahul.sd2test.repository

import io.reactivex.disposables.Disposable

interface UserRepository {

    fun getUsers(offset: Int, limit: Int, callback: GetUserCallback): Disposable
}