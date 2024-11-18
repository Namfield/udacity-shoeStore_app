package com.udacity.shoestore.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeType

class ShoeShareViewModel : ViewModel() {
    val shoe: MutableLiveData<Shoe> = MutableLiveData()
    // The variable to hold the shoe list (initialize to an empty list)
    private val _shoesList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoesList: LiveData<MutableList<Shoe>>
        get() = _shoesList
    // the variable to hold the event when the button to add new shoe is pressed
    private val _navigateToShoeDetail = MutableLiveData<Boolean>()
    val navigateToShoeDetail: LiveData<Boolean>
        get() = _navigateToShoeDetail
    // the variable to hold the event when users save information of the new shoe
    private val _saveNewShoePressed = MutableLiveData<Boolean>()
    val saveNewShoePressed: LiveData<Boolean>
        get() = _saveNewShoePressed
    // the variable to hold the event when users cancel inputting information of the new shoe
    private val _cancelAddShoePressed = MutableLiveData<Boolean>()
    val cancelAddShoePressed: LiveData<Boolean>
        get() = _cancelAddShoePressed
    // For two-way binding with shoe information
    val shoeName = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()
    val shoeType = MutableLiveData<ShoeType>()

    init {
        // Initialize the shoe with some default values if necessary
        shoe.value = Shoe("Shoe Name",
            "0.0",
            "Shoe Company",
            "Shoe Description",
            ShoeType.RUNNING)
    }

    // when users press the button to add a new shoe
    fun onNavigateToAddShoe() {
        _navigateToShoeDetail.value = true
    }

    // when the navigation to AddShoe destination is done
    fun doneNavigateToAddShoe() {
        _navigateToShoeDetail.value = false
    }

    // navigate to Shoe List when users save the newly added shoes to the shoe list
    fun onNavigateToShoeListAfterSaving() {
        _saveNewShoePressed.value = true
    }

    // finish navigating to Shoe List when users save the newly added shoes to the shoe list
    fun doneNavigateToShoeListAfterSaving() {
        _saveNewShoePressed.value = false
    }

    // navigate to Shoe List when users cancel adding shoes to the shoe list
    fun onNavigateToShoeListAfterCancelling() {
        _cancelAddShoePressed.value = true
    }

    // finish navigating to Shoe List when users save the newly added shoes to the shoe list
    fun doneNavigateToShoeListAfterCancelling() {
        _cancelAddShoePressed.value = false
    }

    // add the new shoes that users input to the existing Shoe list
    fun addNewShoe(shoe: Shoe) {
        // retrieve the existing shoe list
        val shoeList = _shoesList.value ?: emptyList()
        // update the shoe list by adding the new shoe to the existing shoe list
        val updatedList = shoeList.toMutableList()
        updatedList.add(shoe)
        _shoesList.value = updatedList
    }

    // save the shoe details
    fun saveShoeDetails() {
        val newShoe = Shoe(
            name = shoeName.value ?: "",
            size = shoeSize.value ?: "",
            company = shoeCompany.value ?: "",
            description = shoeDescription.value ?: "",
            shoeType = shoeType.value ?: ShoeType.RUNNING
        )
        // add the new shoe to the list
        addNewShoe(newShoe)
    }
}