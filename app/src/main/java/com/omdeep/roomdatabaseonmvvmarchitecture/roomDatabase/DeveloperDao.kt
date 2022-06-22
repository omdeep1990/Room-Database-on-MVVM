package com.omdeep.roomdatabaseonmvvmarchitecture.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DeveloperDao {

    //TODO: All CRUD operations used here
    @Insert
    suspend fun insertDeveloper(developerEntity: DeveloperEntity) : Long

    @Update
    suspend fun updateDeveloper(developerEntity: DeveloperEntity)

    @Delete
    suspend fun deleteDeveloper(developerEntity: DeveloperEntity)

    @Query("SELECT * FROM developer_table")
    fun getDevelopers() : LiveData<List<DeveloperEntity>>

    @Query("DELETE FROM developer_table")
    suspend fun deleteAllDevelopers()

}