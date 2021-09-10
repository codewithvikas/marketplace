package com.patna.marketplace.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FactDao {

    @Insert
    fun insert(fact:Fact)

    @Update
    fun update(fact: Fact)

    @Query("SELECT * FROM fact_table WHERE factId = :key")
    fun get(key:Long):Fact

    @Query("DELETE from fact_table")
    fun clear()

    @Query("SELECT * FROM fact_table WHERE category = :factCategory ORDER BY factId DESC")
    fun getAllFactsByCategory(factCategory: FactCategory): LiveData<List<Fact>>

    @Query("SELECT * FROM fact_table")
    fun getAllFacts():List<Fact>
}