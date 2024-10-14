package com.example.mychat.Test.work

import javax.inject.Inject

class c3 @Inject constructor(private val work: Work) {
    fun eun(){
        work.work()
    }
}
