package com.example.kidsnavigation.database.entity


data class Messwert(
    var name: String = "",
    var messwert: Double = 0.0,
    var date: Long = 0
) {
    var mw_id: Long = 0
}


sealed class Gesundheitsparameter {
    var messwert: Messwert? = null

    data object Blutdruck : Gesundheitsparameter() {
        var messwert2: Messwert? = null
    }
    data object Herzfrequenz : Gesundheitsparameter()
    data object Blutzucker : Gesundheitsparameter()
    data object Koerpertemperatur : Gesundheitsparameter()
    data object Sauerstoffsaettigung : Gesundheitsparameter()
    data object Atemfrequenz : Gesundheitsparameter()
    data object BodyMassIndex : Gesundheitsparameter()
    data object Cholesterinspiegel : Gesundheitsparameter()
}

// Alternative zur SealedClass-Variante
enum class GesundheitsparameterE {
    Blutdruck,
    Herzfrequenz,
    Blutzucker,
    Körpertemperatur,
    Sauerstoffsättigung,
    Atemfrequenz,
    BodyMassIndex,
    Cholesterinspiegel
}