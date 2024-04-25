package com.example.kidsnavigation.database

import java.util.Date

class Medikament(
    val name:String,
    val dosis: Double,
    val zeitpunkt: String,
) {
    var start: Date?=null
    var end: Date?=null
}