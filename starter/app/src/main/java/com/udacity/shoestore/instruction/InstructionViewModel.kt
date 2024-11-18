package com.udacity.shoestore.instruction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionViewModel : ViewModel() {
    // the LiveData to get when user press the button
    // to navigate to the Shoe List destination
    private val _navigateToShoeList = MutableLiveData<Boolean>()
    val navigateToShoeList: LiveData<Boolean>
        get() = _navigateToShoeList
    // initialize a text to summarize the main function of the shoe store app
    private val _summaryText = MutableLiveData<String>().apply {
        value = "This app helps you manage a shoe store by allowing you to edit the existing shoe" +
                "list or add new shoes (specifying name, size, and quantity"
    }
    // expose the summaryText as a LiveData for other components can observe its change
    val summaryText: LiveData<String> = _summaryText

    // when users press the button to navigate to the Shoe List screen
    fun onNavigateToShoeList() {
        _navigateToShoeList.value = true
    }

    // when the navigation to the Shoe List screen is done
    fun doneNavigateToShoeList() {
        _navigateToShoeList.value = false
    }
}