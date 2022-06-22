package com.omdeep.roomdatabaseonmvvmarchitecture.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DeveloperEntity::class], version = 1)
abstract class DeveloperDatabase : RoomDatabase() {
    abstract val dao : DeveloperDao

    companion object {
        private val INSTANCE : DeveloperDatabase? = null
        fun getInstance(context : Context) : DeveloperDatabase {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                DeveloperDatabase::class.java, "developerDb").build()
            }
            return instance
        }
    }
}