package com.example.kidsnavigation.database

class Messwert {


    var name:String = ""
    var messwert: Pair<String,Double> = Pair("",0.0)
    var date: Long = 0

    companion object{
        var kategorien: MutableList<String> = mutableListOf()
    }

}