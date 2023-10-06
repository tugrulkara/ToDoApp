package com.tugrulkara.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.tugrulkara.todoapp.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(var repository: LocalRepository) : ViewModel() {

    fun save(title:String,note:String){

        CoroutineScope(Dispatchers.Main).launch {
            repository.save(title,note)
        }

    }


}