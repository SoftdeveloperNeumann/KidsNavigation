package com.example.kidsnavigation.database.entity

import java.util.Date

class Termin(
    val title:String,
    val desc: String = "",
    val start: Date,
    val stop: Date = start,

) {
}