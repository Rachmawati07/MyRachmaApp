package com.rachma.myrachmaapp.data.db

import androidx.room.TypeConverter
import com.rachma.myrachmaapp.data.models.Source

// Untuk mendeklarasikan class yang bernama Converter
class Converter {

    // Untuk mendeklarasikan method yang bernama TypeConverter
    @TypeConverter
    // Untuk mendeklarasikan fungsi yang bernama fromSourceToString yang berisi source yang mempunyai tipe data string
    fun fromSourceToString(source : Source):String{
        // Untuk mengembalikan source
        return source.name
    }

    // Untuk mendeklarasikan method yang bernama TypeConverter
    @TypeConverter
    // Untuk mendeklarasikan fungsi yang bernama fromStringToSource yang berisi source yang mempunyai tipe data string
    fun fromStringToSource(name:String): Source {
        // Untuk mengembalikan source dalam bentuk name
        return Source(name, name)
    }
}