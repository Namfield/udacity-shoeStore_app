package com.udacity.shoestore.shoeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoesListLayoutBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeType
import com.udacity.shoestore.shoe.ShoeShareViewModel

class ShoeListFragment : Fragment() {
    // variable for the ViewModel
    private lateinit var viewModel: ShoeShareViewModel
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // to override the menu items
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = FragmentShoeListBinding.inflate(
            inflater,
            container,
            false
        )

        // Request the shared ViewModel from the activity
        viewModel = ViewModelProvider(requireActivity()).get(ShoeShareViewModel::class.java)
        // Set the ViewModel for data binding
        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner // Use viewLifecycleOwner

        // Observe the LiveData indicating the navigation from Shoe List to Shoe Detail
        viewModel.navigateToShoeDetail.observe(viewLifecycleOwner,
            Observer { shouldNavigate ->
                if (shouldNavigate == true) {
                    // Navigate from Shoe List to Shoe Detail
                    findNavController().navigate(R.id.action_shoeList_to_shoeDetail)
                    // Reset the navigation state
                    viewModel.doneNavigateToAddShoe()
                }
            })

        // Observe the LiveData for the list of shoes to update the list accordingly
        viewModel.shoesList.observe(viewLifecycleOwner,
            Observer { listShoes: MutableList<Shoe> -> updateShoeList(listShoes) }
        )

        return binding.root
    }

    private fun updateShoeList(listShoes: MutableList<Shoe>) {
        val shoeListLayout = binding.shoeListLinearLayout

        // Clear the existing views
        shoeListLayout.removeAllViews()

        for (shoe in listShoes) {
            // Inflate the layout for each shoe and add it to the shoe list
            val shoeBinding: ShoesListLayoutBinding = ShoesListLayoutBinding.inflate(
                layoutInflater,
                shoeListLayout,
                false
            )
            // Get shoes information
            shoeBinding.nameShoeList.text = shoe.name
            shoeBinding.nameCompanyList.text = shoe.company
            shoeBinding.sizeShoeList.text = shoe.size.toString()
            shoeBinding.descriptionShoeList.text = shoe.description
            when (shoe.shoeType) {
                ShoeType.RUNNING -> shoeBinding.imageShoeList.setImageResource(R.drawable.running_shoes)
                ShoeType.SOCCER -> shoeBinding.imageShoeList.setImageResource(R.drawable.soccer_shoes)
                ShoeType.SNEAKERS -> shoeBinding.imageShoeList.setImageResource(R.drawable.sneakers)
                ShoeType.LOAFERS -> shoeBinding.imageShoeList.setImageResource(R.drawable.loafers)
                ShoeType.BOOTS -> shoeBinding.imageShoeList.setImageResource(R.drawable.boots)
            }
            // Add the view of each shoe to the layout of the shoe list
            shoeListLayout.addView(shoeBinding.root)
        }
    }

    // Populate the menu for logout
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_list_menu, menu)
    }

    // Handle when the users press the menu option (logout)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.action_shoeList_to_loginFragment)
        return super.onOptionsItemSelected(item)
    }
}