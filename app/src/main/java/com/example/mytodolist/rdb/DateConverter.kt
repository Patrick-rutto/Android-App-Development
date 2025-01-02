package com.example.mytodolist.rdb

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter                    // Annotation instructing Room how to convert a custom type Like Date
    fun fromDate(date: Date):Long{    // Converts a Date object to a Long
        return date.time
    }

    @TypeConverter
    fun toDate(time:Long): Date{      // Converts a Long (timestamp) back into a Date object.
        return Date(time)
    }


}