package com.tugrulkara.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tugrulkara.todoapp.data.entity.Note
import com.tugrulkara.todoapp.data.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var repository: LocalRepository) : ViewModel() {

    var noteList = MutableLiveData<List<Note>>()

    init {
        getList()
    }


    fun getList(){
        CoroutineScope(Dispatchers.Main).launch {
           noteList.value = repository.getList()
        }
    }

    fun delete(note_id:Int){
        CoroutineScope(Dispatchers.Main).launch {
            repository.delete(note_id)
            getList()
        }
    }

    fun updateCheck(note: Note){
        CoroutineScope(Dispatchers.Main).launch {
            repository.update(note.note_id,note.note_title,note.note_text,note.check_info)
            getList()
        }
    }

    fun search(searchText:String){
        CoroutineScope(Dispatchers.Main).launch {
            noteList.value = repository.search(searchText)
        }
    }

}