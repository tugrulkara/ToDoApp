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
    suspend fun save(kisi: Note)

    @Update
    suspend fun update(kisi: Note)

    @Delete
    suspend fun delete(kisi: Note)

}