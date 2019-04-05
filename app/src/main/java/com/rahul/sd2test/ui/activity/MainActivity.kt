package com.rahul.sd2test.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.sd2test.R
import com.rahul.sd2test.modal.User
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

    val presenter: MainActivityPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        addListeners()
        presenter.getUsersFromApi(arrayList.size, DEFAULT_LIMIT, userCallback)
    }

    fun initUi() {
        rv.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(this, arrayList)
        rv.adapter = userAdapter
    }

    fun addListeners() {
        userAdapter.setCallback(object : UserAdapter.IUserAdapter {
            override fun loadMore() {

                if (!hasMoreItems) {
                    Toast.makeText(this@MainActivity, "All items loaded", Toast.LENGTH_SHORT).show()
                    return
                }

                if (!isLoaderAlreadyShowing()) {
                    presenter.getUsersFromApi(arrayList.size, DEFAULT_LIMIT, userCallback)
                    addProgressBarOnLastItem()
                }
            }
        })

        userCallback = object :GetUserCallback{

            override fun onSuccess() {

            }

            override fun onFail() {

            }
        }
    }

    fun addProgressBarOnLastItem() {
        val user = User()
        user.loadMore = true
        arrayList.add(user)
        rv.post { userAdapter.notifyItemInserted(arrayList.size - 1) }
    }

    fun isLoaderAlreadyShowing(): Boolean {
        if (arrayList.size > 0) {
            val lastIndex = arrayList.size - 1
            if (arrayList[lastIndex].loadMore) {
                return true
            }
        }
        return false
    }
}
