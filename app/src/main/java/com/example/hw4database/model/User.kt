package com.example.hw4database.model

import com.example.hw4database.database.entity.UserDbEntity

data class User(
    val id: Long = 0,
    val firstName: String,
    val secondName: String,
) {

    fun toUserDbEntity() = UserDbEntity(
        id = id,
        firstName = firstName,
        secondName = secondName,
    )

    override fun toString(): String {
        return "$firstName $secondName"
    }
}
