package com.example.hw4database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hw4database.database.entity.UserDbEntity

@Database(entities = [UserDbEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
}