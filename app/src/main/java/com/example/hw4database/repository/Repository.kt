package com.example.hw4database.repository

import com.example.hw4database.model.User

interface Repository {

    fun getAllUsers(): List<User>

    fun deleteUser(user: User)

    fun insertUser(user: User)

    fun updateUser(oldUser: User, newUser: User)
}