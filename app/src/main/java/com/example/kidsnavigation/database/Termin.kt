package com.example.kidsnavigation.database

import java.util.Date

class Termin(
    val title:String,
    val desc: String = "",
    val start: Date,
    val stop: Date = start,

) {
}