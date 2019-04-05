package com.rahul.sd2test.presenter.activity

import com.rahul.sd2test.presenter.BasePresenter
import com.rahul.sd2test.repository.GetUserCallback
import com.rahul.sd2test.repository.UserRepository
import io.reactivex.disposables.Disposable

class MainActivityPresenter(val repository: UserRepository) : BasePresenter {

    private val disposables = ArrayList<Disposable>()

    fun getUsersFromApi(offset: Int, limit: Int, callback: GetUserCallback) {
        disposables.add(repository.getUsers(offset, limit, callback))
    }

    override fun cancel() {
        disposables.forEach {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}