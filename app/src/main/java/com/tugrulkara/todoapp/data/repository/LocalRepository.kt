package com.tugrulkara.todoapp.data.repository

import com.tugrulkara.todoapp.data.datasource.NotesDataSource
import com.tugrulkara.todoapp.data.entity.Note

class LocalRepository(var nDs : NotesDataSource) {

    suspend fun save(title:String,note:String) = nDs.save(title,note)
    suspend fun getList():List<Note> = nDs.getList()
    suspend fun delete(note_id:Int) = nDs.delete(note_id)
    suspend fun update(note_id:Int,title:String,note:String,check_info:Boolean) = nDs.update(note_id,title,note,check_info)
    suspend fun search(searchText:String) : List<Note> = nDs.search(searchText)

}