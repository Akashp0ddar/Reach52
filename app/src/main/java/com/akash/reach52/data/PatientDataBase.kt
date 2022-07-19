package com.akash.reach52.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akash.reach52.fragments.add.Converters
import com.akash.reach52.model.PatientList

@Database(entities = [PatientList::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PatientDataBase : RoomDatabase() {

    abstract fun patientListDao(): PatientListDao

    companion object {
        @Volatile
        private var INSTANCE: PatientDataBase? = null

        fun getDataBase(context: Context): PatientDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, PatientDataBase::class.java, "patient_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}