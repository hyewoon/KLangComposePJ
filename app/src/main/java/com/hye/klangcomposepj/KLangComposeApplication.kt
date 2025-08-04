package com.hye.klangcomposepj

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
* */
@HiltAndroidApp
class KLangComposeApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        myApplication = this
    }
    companion object{
        private lateinit var myApplication: KLangComposeApplication
            fun getInstance() = myApplication

        }
    }

