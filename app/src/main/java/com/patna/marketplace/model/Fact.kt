package com.patna.marketplace.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "fact_table")
data class Fact(
    @PrimaryKey(autoGenerate = true)
    var factId:Long = 0L,
    val category: FactCategory,
    var heading:String,
    var body:String
    ){
    override fun toString(): String {
        return "${category.name}: ${heading},${body}"
    }
}
