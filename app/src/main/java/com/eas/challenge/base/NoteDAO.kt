package com.eas.challenge.base

import androidx.room.*
import com.eas.sdk.commons.models.Note


@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Delete
    fun remove(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note): Int

    @Query("SELECT * FROM Notes ")
    fun getAllMovies(): List<Note>

}