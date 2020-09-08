package kozakiewicz.szymon.julianonandroid.room

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun toStatus(value: Int) = enumValues<Status>()[value]

    @TypeConverter
    fun fromStatus(value: Status) = value.ordinal

}