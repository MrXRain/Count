package com.rain.myapplication

import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MainPresenterTest :CoroutineScope by MainScope(){

    lateinit var presenter:MainPresenter
    lateinit var view:MainView

    @Before
    fun setUp() {
        view = MainActivity()
        presenter = MainPresenter(view)
    }

    @Test
    fun request() {
        async {
            presenter.request()
        }
    }

    @Test
    fun getView() {
    }
}