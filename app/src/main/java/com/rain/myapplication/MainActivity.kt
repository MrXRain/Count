package com.rain.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import androidx.recyclerview.widget.LinearLayoutManager
import com.rain.myapplication.api.Api
import com.rain.myapplication.view.ListAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author rain
 */
class MainActivity : AppCompatActivity(),MainView {
    private val presenter:MainPresenter = MainPresenter(this)
    lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initData()
    }

    private fun initView() {
        adapter = ListAdapter()
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = adapter
        swipe.setOnRefreshListener {
            refresh()
        }
    }

    private fun initData() {
        Api.run {
            year = 2008
            endYaer = 2018
            init()
        }
        refresh()
    }

    override fun showData(datas: ArrayMap<Int, MutableList<Float>>) {
        swipe.isRefreshing = false
        adapter.setItem(datas)
        adapter.notifyDataSetChanged()
    }

    private fun refresh() {
        swipe.isRefreshing = true
        presenter.request()
    }

    override fun error(error: String) {
    }
}
