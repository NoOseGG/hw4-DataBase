package com.example.hw4database

import android.content.Context
import androidx.room.Room
import com.example.hw4database.database.AppDataBase
import com.example.hw4database.repository.UserRepository

object Repositories {

    private lateinit var applicationContext: Context

    private val dataBase: AppDataBase by lazy {
        Room.databaseBuilder(applicationContext, AppDataBase::class.java, "database-room")
            .allowMainThreadQueries()
            .build()
    }

    val userRepository: UserRepository by lazy {
        UserRepository(dataBase.userDao())
    }

    fun init(context: Context) {
        applicationContext = context
    }
}
