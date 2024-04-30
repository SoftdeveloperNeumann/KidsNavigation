package com.example.kidsnavigation.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "medikament")
data class Medikament(

    val name:String,
) {
    @PrimaryKey(autoGenerate = true) var m_id: Long=0
}

@Entity(tableName = "einnahmezeit",
        foreignKeys = [ForeignKey(entity = Medikament::class,
            parentColumns = ["m_id"],
            childColumns = ["medikamentId"],
            onDelete = ForeignKey.CASCADE)]
    )
data class Einnahmezeit(

    val dosis: Double,
    val zeit: String,
    val startDatum: String? =null,
    val endDatum: String? =null,
    val medikamentId: Long
){
    @PrimaryKey(autoGenerate = true) var id: Long=0

}

data class MedikamentMitEinnahmeZeit(
    @Embedded val medikament: Medikament,
    val id:Long,
    val dosis: Double,
    val zeit: String,
    val startDatum: String? =null,
    val endDatum: String? =null,
    val medikamentId: Long
)