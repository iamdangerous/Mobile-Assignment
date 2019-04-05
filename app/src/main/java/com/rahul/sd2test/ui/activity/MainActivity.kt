package com.rahul.sd2test.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.sd2test.MyApp
import com.rahul.sd2test.R
import com.rahul.sd2test.modal.User
import com.rahul.sd2test.modal.UsersResponse
import com.rahul.sd2test.presenter.activity.MainActivityPresenter
import com.rahul.sd2test.repository.GetUserCallback
import com.rahul.sd2test.ui.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity : BaseActivity() {
    val DEFAULT_LIMIT = 7
    var hasMoreItems = true
    val arrayList = ArrayList<User>()
    lateinit var userAdapter: UserAdapter
    lateinit var userCallback: GetUserCallback
    var recyclerViewScrollFix = false

    val presenter: MainActivityPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        addListeners()
        getData()
    }

    private fun initUi() {
        rv.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(this, arrayList)
        rv.adapter = userAdapter
    }

    private fun getData() {
        presenter.getUsersFromApi(arrayList.size, DEFAULT_LIMIT, userCallback)
    }

    private fun addListeners() {
        userAdapter.setCallback(object : UserAdapter.IUserAdapter {
            override fun loadMore() {
                if (areAllItemsLoaded()) {
                    return
                }
                if (!isLoaderAlreadyShowing()) {
                    getData()
                    addProgressBarOnLastItem()
                }
            }
        })

        userCallback = object : GetUserCallback {

            override fun onSuccess(usersResponse: UsersResponse) {
                showUserData(usersResponse)
            }

            override fun onFail() {
                toggleProgressBar(false)
            }
        }
    }

    private fun addProgressBarOnLastItem() {
        val user = User()
        user.loadMore = true
        arrayList.add(user)
        rv.post { userAdapter.notifyItemInserted(arrayList.size - 1) }
    }

    private fun isLoaderAlreadyShowing(): Boolean {
        if (arrayList.size > 0) {
            val lastIndex = arrayList.size - 1
            if (arrayList[lastIndex].loadMore) {
                return true
            }
        }
        return false
    }

    private fun areAllItemsLoaded(): Boolean {
        if (!hasMoreItems) {
            return true
        }
        return false
    }

    private fun showUserData(usersResponse: UsersResponse) {
        toggleProgressBar(false)

        if (arrayList.size == 0) {
            recyclerViewScrollFix = false
        }

        val users = usersResponse.data.users
        hasMoreItems = usersResponse.data.hasMore
        if (users.size == 0) {

            //disable progress bar
            if (arrayList.size > 0) {
                val lastIndex = arrayList.size - 1
                if (arrayList[lastIndex].loadMore) {
                    arrayList.removeAt(lastIndex)
                    rv.post { userAdapter.notifyItemRemoved(lastIndex) }
                }
            }

        } else {

            if (arrayList.size > 0) {
                val lastIndex = arrayList.size - 1
                if (arrayList[lastIndex].loadMore) {
                    arrayList.removeAt(lastIndex)
                    rv.post { userAdapter.notifyItemRemoved(lastIndex) }
                }
            }

            val prevPos = arrayList.size
            arrayList.addAll(users)
            rv.post { userAdapter.notifyItemRangeInserted(prevPos, arrayList.size) }

        }

        if (!recyclerViewScrollFix) {
            rv.postDelayed({
                rv.scrollToPosition(0)
                recyclerViewScrollFix = true
            }, 200)
        }
    }

    private fun toggleProgressBar(show: Boolean) {
        if (show)
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cancel()
    }
}
