package com.akash.reach52.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
@Entity(tableName = "patient_list")
data class PatientList(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var FirstName: String? = null,
    var MiddleName: String? = null,
    var LastName: String? = null,
    var Sex: String? = null,
    var BirthDate: String? = null,
    var patientImage: Bitmap? = null,

    //Doctor details

    var DoctorID: String? = null,
    var DoctorFirstName: String? = null,
    var DoctorMiddleName: String? = null,
    var DoctorLastName: String? = null,
    var DoctorDate: String? = null
) : Parcelable
