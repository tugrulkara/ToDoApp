package com.tugrulkara.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tugrulkara.todoapp.data.entity.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    suspend fun getList() : List<Note>

    @Insert
    suspend fun save(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes WHERE note_title like '%' || :searchText || '%' ")
    suspend fun search(searchText:String) : List<Note>

}