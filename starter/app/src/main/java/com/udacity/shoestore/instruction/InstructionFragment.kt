package com.udacity.shoestore.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {
    // variable for the ViewModel
    private lateinit var viewModel: InstructionViewModel
    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instruction,
            container,
            false
        )

        // request the viewModel
        viewModel = ViewModelProvider(this).get(InstructionViewModel::class.java)
        // set the ViewModel for data binding
        binding.instructionViewModel = viewModel
        binding.lifecycleOwner = this

        // observe the LiveData indicating the navigation to Shoe List destination
        viewModel.navigateToShoeList.observe(viewLifecycleOwner,
            Observer { shouldNavigate ->
                if (shouldNavigate == true) {
                    // navigate to shoe list screen
                    findNavController().navigate(R.id.action_instruction_to_shoe_list)
                    // reset the navigation state
                    viewModel.doneNavigateToShoeList()
                }
            })

        return binding.root
    }
}