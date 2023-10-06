package com.tugrulkara.todoapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.tugrulkara.todoapp.R
import com.tugrulkara.todoapp.databinding.FragmentCreateBinding
import com.tugrulkara.todoapp.ui.viewmodel.CreateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateFragment : Fragment() {

    private lateinit var binding: FragmentCreateBinding
    private lateinit var viewModel: CreateViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateBinding.inflate(inflater,container,false)

        binding.buttonSave.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val note = binding.editTextNote.text.toString()

            if (title.isNotEmpty() && note.isNotEmpty()){
                save(title, note)
                Navigation.findNavController(it).navigate(R.id.action_createFragment_to_homeFragment)
            }else{
                Snackbar.make(it,"Title and Note cannot be empty", Snackbar.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:CreateViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun save(title:String,note:String){
        viewModel.save(title,note)
    }

}