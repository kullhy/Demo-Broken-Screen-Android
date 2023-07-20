package com.hidenobi.baseapplication.data

import androidx.room.RoomDatabase

/*
    TODO need when app use RoomDatabase
@Database(entities = [], version = 1, exportSchema = false)
 */
abstract class AppDatabase : RoomDatabase() {
//    companion object {
//        private var appDatabase: AppDatabase? = null
//
//        @Synchronized
//        fun getDatabase(context: Context): AppDatabase {
//            if (appDatabase == null) {
//                appDatabase = Room.databaseBuilder(
//                    context,
//                    AppDatabase::class.java,
//                    "appDatabase.db"
//                ).fallbackToDestructiveMigration()
//                    .allowMainThreadQueries() // allow access database from main thread
//                    .build()
//            }
//            return appDatabase!!
//        }
//    }

}
