package com.wilderapps.costars.local.typeConverters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson

@ProvidedTypeConverter
class GenreIdTypeConverter {

//    @TypeConverter
//    fun listToString(genreIds: MutableList<Int>): String{
//        var genreIdsString = ""
//
//        for(i in genreIds){
//            genreIdsString += if(i == genreIds.last()){
//                "$i"
//            } else {
//                "$i,"
//            }
//        }
//
//        return genreIdsString
//    }
//
//    @TypeConverter
//    fun stringToList(genreIds: String): List<Int>{
//        val genreIdsMutableList = mutableListOf<Int>()
//
//        genreIds.split(",").let{ genreIdsArray ->
//            for(i in genreIdsArray){
//                genreIdsMutableList.add(i.toInt())
//            }
//        }
//
//        return genreIdsMutableList
//    }

    @TypeConverter
    fun mutableListOfIntToJson(list: MutableList<Int>): String = Gson().toJson(list)

    @TypeConverter
    fun jsonToMutableListOfInt(json: String): MutableList<Int> = Gson().fromJson(json, Array<Int>::class.java).toMutableList()
}