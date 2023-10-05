package com.tugrulkara.todoapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "note_id") @NotNull var note_id:Int,
    @ColumnInfo(name = "note_title") @NotNull var note_title:String,
    @ColumnInfo(name = "note_text") @NotNull var note_text:String
): Serializable
