package com.example.kidsnavigation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kidsnavigation.database.dao.MedikamentDao
import com.example.kidsnavigation.database.entity.Einnahmezeit
import com.example.kidsnavigation.database.entity.Medikament

@Database(entities = [Medikament::class,Einnahmezeit::class], version = 1)
abstract class KidsNavigationDatabase: RoomDatabase() {

    abstract fun medikamentDao(): MedikamentDao

    object Factory{

        private var instance: KidsNavigationDatabase? =null

        fun getInstance(context:Context): KidsNavigationDatabase{
            if(instance == null){
                synchronized(KidsNavigationDatabase::class){
                    if (instance == null){
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            KidsNavigationDatabase::class.java,
                            "kids_navigation"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }

            return instance!!
        }
    }
}