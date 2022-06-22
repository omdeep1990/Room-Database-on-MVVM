package com.omdeep.roomdatabaseonmvvmarchitecture.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "developer_table")
data class DeveloperEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "ftName")
    var fName : String,

    @ColumnInfo(name = "ltName")
    var lName : String,

    @ColumnInfo(name = "profes")
    var prof : String,

    @ColumnInfo(name = "mobilNo")
    var mobNo : String

        )