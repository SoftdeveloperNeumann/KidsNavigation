package com.example.kidsnavigation.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.kidsnavigation.database.KidsNavigationDatabase
import com.example.kidsnavigation.database.dao.MedikamentDao
import com.example.kidsnavigation.database.entity.Einnahmezeit
import com.example.kidsnavigation.database.entity.Medikament
import com.example.kidsnavigation.database.entity.MedikamentMitEinnahmeZeit

class KidsNavigationRepository(app: Application) {
    private var medikamentDao: MedikamentDao
    private var allMedikamentMitEinnahmeZeit: LiveData<List<MedikamentMitEinnahmeZeit>>
    private var allMedics: LiveData<List<Medikament>>

    init {
        medikamentDao =
            KidsNavigationDatabase.Factory.getInstance(app.applicationContext).medikamentDao()
        allMedikamentMitEinnahmeZeit = medikamentDao.getAllMedikamente()
        allMedics = medikamentDao.getAllMedics()
    }

    fun getAllMedikamenteMitEinnahmezeit(): LiveData<List<MedikamentMitEinnahmeZeit>> {
        return allMedikamentMitEinnahmeZeit
    }

    fun getAllMedics(): LiveData<List<Medikament>> {
        return allMedics
    }

    fun insert(medikament: Medikament) {
        medikamentDao.insertMedikament(medikament)
    }

    fun insert(einnahmeZeit: Einnahmezeit) {
        medikamentDao.insertEinnahmezeit(einnahmeZeit)
    }

    fun delete(medikament: Medikament) {
        medikamentDao.deleteMedikament(medikament)
    }

    fun delete(einnahmeZeit: Einnahmezeit) {
        medikamentDao.deleteEinnahmezeit(einnahmeZeit)
    }
}