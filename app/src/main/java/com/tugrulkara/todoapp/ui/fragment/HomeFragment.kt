package com.tugrulkara.todoapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugrulkara.todoapp.R
import com.tugrulkara.todoapp.databinding.FragmentHomeBinding
import com.tugrulkara.todoapp.ui.adapter.ToDoListAdapter
import com.tugrulkara.todoapp.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.noteList.observe(viewLifecycleOwner){
            val kisilerAdapter = ToDoListAdapter(requireContext(),it,viewModel)
            binding.recyclerView.adapter = kisilerAdapter
        }

        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                search(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return false
            }
        })


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:HomeViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun search(searchText:String){
        viewModel.search(searchText)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
    }

}