package com.omdeep.roomdatabaseonmvvmarchitecture.repository

import com.omdeep.roomdatabaseonmvvmarchitecture.roomDatabase.DeveloperDao
import com.omdeep.roomdatabaseonmvvmarchitecture.roomDatabase.DeveloperEntity

class DeveloperRepository(val dao: DeveloperDao) {
//TODO: All functions are called DeveloperDao interface
    //TODO: Insert function called
    suspend fun insert(developerEntity: DeveloperEntity) {
        dao.insertDeveloper(developerEntity)
    }

    //TODO: Update function called
    suspend fun update(developerEntity: DeveloperEntity) {
        dao.updateDeveloper(developerEntity)
    }

    //TODO: Delete function called
    suspend fun delete(developerEntity: DeveloperEntity) {
        dao.deleteDeveloper(developerEntity)
    }

    //TODO: Delete all function called
    suspend fun deleteAll() {
        dao.deleteAllDevelopers()
    }

    //TODO: Fetching function called
    val developers = dao.getDevelopers()

}