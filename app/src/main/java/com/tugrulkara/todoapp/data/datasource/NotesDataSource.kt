package com.tugrulkara.todoapp.data.datasource

import com.tugrulkara.todoapp.data.entity.Note
import com.tugrulkara.todoapp.room.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesDataSource(var nDao:NotesDao) {

    suspend fun save(title:String,note:String){
        val newNote = Note(0,title,note)
        nDao.save(newNote)
    }

    suspend fun getList() : List<Note> = withContext(Dispatchers.IO){
        return@withContext nDao.getList()
    }

    suspend fun delete(note_id: Int){
        val deleteNote = Note(note_id,"","")
        nDao.delete(deleteNote)
    }

    suspend fun update(note_id:Int,title:String,note:String,check_info:Boolean){
        val updateNote = Note(note_id,title,note,check_info)
        nDao.update(updateNote)
    }

    suspend fun search(searchText:String) : List<Note> = withContext(Dispatchers.IO){
        return@withContext nDao.search(searchText)
    }


}