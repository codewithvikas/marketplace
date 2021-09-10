package com.patna.marketplace

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.patna.marketplace.model.Fact
import com.patna.marketplace.model.FactCategory
import com.patna.marketplace.model.FactDao
import com.patna.marketplace.model.MarketPlaceDatabase
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
        private lateinit var factDao: FactDao
        private lateinit var db: MarketPlaceDatabase

        @Before
        fun createDb() {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            // Using an in-memory database because the information stored here disappears when the
            // process is killed.
            db = Room.inMemoryDatabaseBuilder(context, MarketPlaceDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
            factDao = db.factDao
        }

        @After
        @Throws(IOException::class)
        fun closeDb() {
            db.close()
        }

        @Test
        @Throws(Exception::class)
        fun insertAndGetFact() {
            val fact = Fact(category = FactCategory.ANIMAL,heading = "Cow is a pet",body = "Cow give milk")
            factDao.insert(fact)
            val factFromDb = factDao.get(1)
            assertEquals(factFromDb?.category, FactCategory.ANIMAL)
        }
}