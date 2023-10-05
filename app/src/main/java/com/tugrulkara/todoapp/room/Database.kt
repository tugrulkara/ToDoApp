package com.tugrulkara.todoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tugrulkara.todoapp.data.entity.Note

@Database(entities = [Note::class], version = 1)
abstract class Database : RoomDatabase(){
    abstract fun getNotesDao() : NotesDao
}