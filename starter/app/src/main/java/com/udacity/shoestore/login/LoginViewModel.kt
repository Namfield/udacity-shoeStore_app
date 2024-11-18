package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    // the LiveData to observe Login options of users
    private val _navigateToWelcome = MutableLiveData<Boolean>()
    val navigateToWelcome: LiveData<Boolean>
        get() = _navigateToWelcome

    // when users press a button on the Login screen, navigate to the welcome screen
    fun onNavigateToWelcome() {
        _navigateToWelcome.value = true
    }

    // when the navigation from Login to Welcome screen is done
    fun doneNavigateToWelcome() {
        _navigateToWelcome.value = false
    }
}