package com.udacity.shoestore.shoeDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.ShoeType
import com.udacity.shoestore.shoe.ShoeShareViewModel

class ShoeDetailFragment : Fragment() {
    // variable for the ViewModel
    private lateinit var viewModel: ShoeShareViewModel
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        // Request the shared ViewModel from the activity
        viewModel = ViewModelProvider(requireActivity()).get(ShoeShareViewModel::class.java)
        // Set the ViewModel for data binding
        binding.shoeDetailViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner // Use viewLifecycleOwner

        // Observe LiveData for save and cancel actions
        viewModel.saveNewShoePressed.observe(viewLifecycleOwner, Observer { saveShoeDetail ->
            if (saveShoeDetail == true) {
                findNavController().popBackStack()
                viewModel.doneNavigateToShoeListAfterSaving()
            }
        })

        viewModel.cancelAddShoePressed.observe(viewLifecycleOwner, Observer { cancelInputtingShoeDetail ->
            if (cancelInputtingShoeDetail == true) {
                findNavController().popBackStack()
                viewModel.doneNavigateToShoeListAfterCancelling()
            }
        })

        // Reference to the Spinner using the binding object
        val shoeTypeSpinner: Spinner = binding.shoeTypeSpinner

        // Array of shoe types
        val shoeTypes = arrayOf("RUNNING", "SOCCER", "Sneakers", "Loafers", "Boots")

        // Create an ArrayAdapter using the shoe types array
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, shoeTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set the adapter to the Spinner
        shoeTypeSpinner.adapter = adapter

        // Bind the selected shoe type
        shoeTypeSpinner.setSelection(shoeTypes.indexOf(viewModel.shoeType.value?.name))

        // Set an Item Selected Listener
        shoeTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedShoeType = ShoeType.valueOf(shoeTypes[position].toUpperCase())
                viewModel.shoeType.value = selectedShoeType // Update LiveData
                setShoeImage(shoeTypes[position]) // Call your method to update the image
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        return binding.root
    }

    // Method to update the image based on the selected shoe type
    private fun setShoeImage(shoeType: String) {
        // Reference the ImageView using the binding object
        val imageView: ImageView = binding.imageShoe
        when (shoeType) {
            "RUNNING" -> imageView.setImageResource(R.drawable.running_shoes)
            "SOCCER" -> imageView.setImageResource(R.drawable.soccer_shoes)
            "Sneakers" -> imageView.setImageResource(R.drawable.sneakers)
            "Loafers" -> imageView.setImageResource(R.drawable.loafers)
            "Boots" -> imageView.setImageResource(R.drawable.boots)
        }
    }
}