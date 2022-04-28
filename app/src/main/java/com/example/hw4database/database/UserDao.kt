package com.example.hw4database.database

import androidx.room.*
import com.example.hw4database.database.entity.UserDbEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<UserDbEntity>

    @Query("SELECT * FROM user WHERE first_name = :firstName AND second_name = :secondName")
    fun getUser(firstName: String, secondName: String): UserDbEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(userDbEntity: UserDbEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userDbEntity: UserDbEntity)

    @Delete
    fun deleteUser(userDbEntity: UserDbEntity)

}