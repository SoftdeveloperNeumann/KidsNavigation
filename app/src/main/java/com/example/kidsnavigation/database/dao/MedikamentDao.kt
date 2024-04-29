package com.example.kidsnavigation.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kidsnavigation.database.entity.Einnahmezeit
import com.example.kidsnavigation.database.entity.Medikament
import com.example.kidsnavigation.database.entity.MedikamentMitEinnahmeZeit

@Dao
interface MedikamentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedikament(medikament: Medikament)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEinnahmezeit(einnahmezeit: Einnahmezeit)

    @Delete
    fun deleteMedikament(medikament: Medikament)

    @Delete
    fun deleteEinnahmezeit(einnahmezeit: Einnahmezeit)

    @Query(value = "select * from einnahmezeit join medikament on medikamentId = medikament.id  order by zeit")
    fun getAllMedikamente():LiveData<List<MedikamentMitEinnahmeZeit>>

    @Query("select * from medikament")
    fun getAllMedics():LiveData<List<Medikament>>

}