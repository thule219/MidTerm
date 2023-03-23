package com.example.baikiemtra.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val id: Int?,
    @ColumnInfo val email:String,
    @ColumnInfo val userName:String,
    @ColumnInfo val password:String,

    )
