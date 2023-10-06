package com.tugrulkara.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.tugrulkara.todoapp.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(var repository: LocalRepository) : ViewModel() {

    fun update(note_id:Int,title:String,note:String,check_info:Boolean){
        CoroutineScope(Dispatchers.Main).launch {
            repository.update(note_id,title,note,check_info)
        }
    }

}