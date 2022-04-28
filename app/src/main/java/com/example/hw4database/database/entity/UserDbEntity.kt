package com.example.hw4database.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hw4database.model.User

@Entity(tableName = "user")
data class UserDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "first_name")
    var firstName: String,
    @ColumnInfo(name = "second_name")
    var secondName: String,
) {

    fun toUser(): User = User(
        id = id,
        firstName = firstName,
        secondName = secondName,
    )
}

