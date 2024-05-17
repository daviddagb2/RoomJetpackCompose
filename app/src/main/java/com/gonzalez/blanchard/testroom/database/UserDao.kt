package com.gonzalez.blanchard.testroom.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    @Insert
    fun insertUser(user: User)
}