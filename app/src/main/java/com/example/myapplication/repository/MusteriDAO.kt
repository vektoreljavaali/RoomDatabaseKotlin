package com.example.myapplication.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.repository.entity.Musteri

@Dao
interface MusteriDAO {
    @Query("SELECT * FROM Musteri")
    fun getAll(): List<Musteri>

    @Query("SELECT * FROM Musteri WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Musteri>

    @Query("SELECT * FROM Musteri WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Musteri

    @Insert
    fun insertAll(vararg users: Musteri)

    @Delete
    fun delete(user: Musteri)
}