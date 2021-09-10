package com.patna.marketplace.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Fact::class],version = 1,exportSchema = false)
abstract class MarketPlaceDatabase:RoomDatabase() {

    abstract val factDao:FactDao

    companion object{
        @Volatile
        private var INSTANCE:MarketPlaceDatabase? = null

        fun getInstance(context: Context):MarketPlaceDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance==null){
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            MarketPlaceDatabase::class.java,
                            "marketplace_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}