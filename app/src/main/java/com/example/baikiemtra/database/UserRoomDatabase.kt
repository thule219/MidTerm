package com.example.baikiemtra.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.baikiemtra.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = true
)
abstract class UserRoomDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        private var INSTANCE: UserRoomDatabase?=null
        private val DB_NAME = "user_db"

        fun getDatabase(context: Context): UserRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}