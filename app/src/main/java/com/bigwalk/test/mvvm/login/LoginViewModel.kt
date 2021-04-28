package com.bigwalk.test.mvvm.login


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bigwalk.test.util.Event


class LoginViewModel : ViewModel() {



    val moveToMainEvent:MutableLiveData<Event<Unit>> = MutableLiveData()

    val requestLogin:MutableLiveData<Event<Unit>> = MutableLiveData()


    fun loginEvent(){
        requestLogin.value = Event(Unit)
    }

    fun loginSuccessEvent(){
        moveToMainEvent.value = Event(Unit)
    }
}