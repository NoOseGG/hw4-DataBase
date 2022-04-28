package com.example.hw4database.repository


import com.example.hw4database.database.UserDao
import com.example.hw4database.database.entity.UserDbEntity
import com.example.hw4database.model.User

class UserRepository(
    private val userDao: UserDao
) : Repository {

    override fun getAllUsers(): List<User> {
        val userDbEntityList = userDao.getAllUsers()
        val userList = userDbEntityList.map {
            it.toUser()
        }
        return userList
    }

    override fun deleteUser(user: User) {
        val userDbEntity = getUser(user.firstName, user.secondName)
        userDao.deleteUser(userDbEntity)
    }

    override fun insertUser(user: User) {
        val userDbEntity = user.toUserDbEntity()
        userDao.insertUser(userDbEntity)
    }

    override fun updateUser(oldUser: User, newUser: User) {
        val userDbEntity = getUser(oldUser.firstName, oldUser.secondName)
        userDbEntity.firstName = newUser.firstName
        userDbEntity.secondName = newUser.secondName
        userDao.updateUser(userDbEntity)
    }

    // method for delete and update user
    private fun getUser(firstName: String, secondName: String): UserDbEntity {
        return userDao.getUser(firstName, secondName)
    }
}