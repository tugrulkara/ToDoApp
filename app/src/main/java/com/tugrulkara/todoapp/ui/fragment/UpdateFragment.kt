package com.tugrulkara.todoapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.tugrulkara.todoapp.R
import com.tugrulkara.todoapp.databinding.FragmentUpdateBinding
import com.tugrulkara.todoapp.ui.viewmodel.UpdateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: UpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater,container,false)

        val bundle : UpdateFragmentArgs by navArgs()
        val note = bundle.note

        binding.editTextTitle.setText(note.note_title)
        binding.editTextNote.setText(note.note_text)
        binding.buttonUpdate.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val note_text = binding.editTextNote.text.toString()

            if (title.isNotEmpty() && note_text.isNotEmpty()){
                update(note.note_id,title,note_text,note.check_info)
                Navigation.findNavController(it).navigate(R.id.action_updateFragment_to_homeFragment)
            }else{
                Snackbar.make(it,"Title and Note cannot be empty", Snackbar.LENGTH_SHORT).show()
            }


        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:UpdateViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun update(note_id:Int,title:String,note:String,check_info:Boolean){
        viewModel.update(note_id,title,note,check_info)
    }

}