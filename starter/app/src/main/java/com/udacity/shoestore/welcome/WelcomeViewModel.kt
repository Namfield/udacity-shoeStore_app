package com.udacity.shoestore.welcome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {
    // the LiveData to get when user press the button
    // to navigate to the Instruction destination
    private val _navigateToInstruction = MutableLiveData<Boolean>()
    val navigateToInstruction: LiveData<Boolean>
        get() = _navigateToInstruction

    // when users press the button on the Welcome screen to navigate to the instruction screen
    fun onNavigateToInstruction() {
        _navigateToInstruction.value = true
    }

    // when the navigation from Welcome to Instruction screen is done
    fun doneNavigateToInstruction() {
        _navigateToInstruction.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("WelcomeViewModel", "WelcomeViewModel is destroyed")
    }
}