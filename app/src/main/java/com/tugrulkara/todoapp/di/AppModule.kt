package com.tugrulkara.todoapp.di

import android.content.Context
import androidx.room.Room
import com.tugrulkara.todoapp.room.Database
import com.tugrulkara.todoapp.room.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNotesDao(@ApplicationContext context: Context) : NotesDao{
        val db = Room.databaseBuilder(context,Database::class.java,"notes")
            .createFromAsset("notes").build()
        return db.getNotesDao()
    }

}