package com.eas.challenge.base


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eas.sdk.commons.models.Note


@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDAO(): NoteDAO

    companion object {
        private const val DB_NAME = "Notes"
        private var instance: NoteDatabase? = null

        @Synchronized
        fun getInstance(context: Context): NoteDatabase? {
            if (instance == null) {
                synchronized(NoteDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        NoteDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

    }
}