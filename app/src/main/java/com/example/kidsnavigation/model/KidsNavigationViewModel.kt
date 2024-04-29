package com.example.kidsnavigation.model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kidsnavigation.database.entity.Einnahmezeit
import com.example.kidsnavigation.database.entity.Medikament
import com.example.kidsnavigation.database.entity.MedikamentMitEinnahmeZeit
import com.example.kidsnavigation.repository.KidsNavigationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object KidsNavigationViewModel : ViewModel() {
    private var repository: KidsNavigationRepository? = null
    private var allMedikamentMitEinnahmeZeit: LiveData<List<MedikamentMitEinnahmeZeit>>? = null
    private var allMedics: LiveData<List<Medikament>>? = null

    operator fun invoke(app: Application): KidsNavigationViewModel {
        repository = KidsNavigationRepository(app)
        allMedikamentMitEinnahmeZeit = repository?.getAllMedikamenteMitEinnahmezeit()
        allMedics = repository?.getAllMedics()
        return this
    }

    fun getAllMedikamenteMitEinnahmeZeit(): LiveData<List<MedikamentMitEinnahmeZeit>>? {
        return allMedikamentMitEinnahmeZeit
    }

    fun getAllMedics(): LiveData<List<Medikament>>? {
        return allMedics
    }

    fun insert(einnahmeZeit: Einnahmezeit) {
        CoroutineScope(Dispatchers.IO).launch {
            repository?.insert(einnahmeZeit)
        }

    }

    fun insert(medikament: Medikament) {
        CoroutineScope(Dispatchers.IO).launch {
            repository?.insert(medikament)
        }
    }

    fun delete(medikament: Medikament) {
        CoroutineScope(Dispatchers.IO).launch {
            repository?.delete(medikament)
        }
    }

    fun delete(einnahmeZeit: Einnahmezeit) {
        CoroutineScope(Dispatchers.IO).launch {
            repository?.delete(einnahmeZeit)
        }
    }

}