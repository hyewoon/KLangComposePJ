package com.hye.klangcomposepj.common

import android.app.Application

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

