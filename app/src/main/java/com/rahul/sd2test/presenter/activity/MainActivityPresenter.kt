package com.rahul.sd2test.presenter.activity

import com.rahul.sd2test.presenter.BasePresenter
import com.rahul.sd2test.repository.UserRepository

class MainActivityPresenter(val repository: UserRepository) : BasePresenter {


    fun getUsersFromApi(offset:Int, limit:Int){
        repository.getUsers(offset,limit)
    }

    override fun start() {
    }

    override fun cancel() {
    }

}