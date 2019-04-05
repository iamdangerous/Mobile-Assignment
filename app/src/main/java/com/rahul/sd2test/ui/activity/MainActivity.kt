package com.rahul.sd2test.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.sd2test.R
import com.rahul.sd2test.modal.User
import com.rahul.sd2test.presenter.activity.MainActivityPresenter
import com.rahul.sd2test.repository.UserRepositoryImpl
import com.rahul.sd2test.ui.adapter.UserAdapter
import com.rahul.sd2test.webService.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {
    val DEFAULT_LIMIT = 7
    var hasMoreItems = true
    val arrayList = ArrayList<User>()
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        addListeners()
    }

    fun initUi() {

        rv.layoutManager = LinearLayoutManager(this)

//        val apiService = ApiService()
//        val presenter = MainActivityPresenter()
//        val repository = UserRepositoryImpl()

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

                if(!isLoaderAlreadyShowing()){
//                    getUsersFromApi(arrayList.size, DEFAULT_LIMIT)
                    addProgressBarOnLastItem()
                }
            }
        })
    }

    fun addProgressBarOnLastItem() {
        val user = User()
        user.loadMore = true
        arrayList.add(user)
        rv.post { userAdapter.notifyItemInserted(arrayList.size - 1) }
    }

    fun isLoaderAlreadyShowing():Boolean {
        if (arrayList.size > 0) {
            val lastIndex = arrayList.size - 1
            if (arrayList[lastIndex].loadMore) {
                return true
            }
        }
        return false
    }
}
