package `in`.co.logicsoft.webview.room

import `in`.co.logicsoft.webview.room.message.Message
import `in`.co.logicsoft.webview.room.message.MessageDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Message::class], version = 1)
abstract class LsDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    companion object {
        var TESTING = false

        @Volatile
        private var INSTANCE: LsDatabase? = null

        fun getDatabase(context: Context): LsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance: LsDatabase = when (TESTING) {
                    true -> {
                        Room.inMemoryDatabaseBuilder(
                            context,
                            LsDatabase::class.java
                        ).allowMainThreadQueries().build()
                    }
                    else -> {
                        Room.databaseBuilder(
                            context.applicationContext,
                            LsDatabase::class.java,
                            "LsDatabase"
                        ).build()
                    }
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}