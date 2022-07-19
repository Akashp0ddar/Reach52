package com.akash.reach52.fragments.add

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.akash.reach52.R
import com.akash.reach52.databinding.FragmentAddBinding
import com.akash.reach52.model.PatientList
import com.akash.reach52.viewmodel.PatientViewModel

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var mUserViewModel: PatientViewModel
    private lateinit var imageBitmap: Bitmap

    /**
     * Using contracts to pickup image from Gallery
     * */
    private val galleryImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            imageBitmap = getImageUri(requireContext(), it)
            binding.ivPatientImage.setImageURI(it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)
        mUserViewModel = ViewModelProvider(requireActivity()).get(PatientViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        binding.btnAddPatientDetails.setOnClickListener {
            if(::imageBitmap.isInitialized) {
                insertDataToDataBase()
            }else{
                Toast.makeText(requireContext(), "Please Fill out all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivPatientImage.setOnClickListener {
            /**
             * Showing image in imageView
             */
            galleryImage.launch("image/*")
        }
    }

    private fun insertDataToDataBase() {
        val patientFirstName = binding.etPatientName.text.toString()
        val patientMiddleName = binding.etPatientMiddleName.text.toString()
        val patientLastName = binding.etPatientLastName.text.toString()
        val sex = binding.etPatientSex.text.toString()
        val birthdate = binding.etPatientBirthdate.text.toString()

        // Inserting Doctor details
        val doctorID = binding.etDoctorID.text.toString()
        val doctorFirstName = binding.etDoctorFirstName.text.toString()
        val doctorMiddleName = binding.etDoctorMiddleName.text.toString()
        val doctorLastName = binding.etDoctorLastName.text.toString()



        if (inputCheck(
                patientFirstName,
                patientMiddleName,
                patientLastName,
                sex,
                birthdate,
                doctorID,
                doctorFirstName,
                doctorMiddleName,
                doctorLastName
            )
        ) {

                val patient = PatientList(
                    FirstName = patientFirstName,
                    MiddleName = patientMiddleName,
                    LastName = patientLastName,
                    Sex = sex,
                    patientImage = imageBitmap,
                    BirthDate = birthdate,
                    DoctorFirstName = doctorFirstName,
                    DoctorMiddleName = doctorMiddleName,
                    DoctorLastName = doctorLastName,
                    DoctorID = doctorID


                )
                //Add data to Database
                mUserViewModel.addPatient(patient)
            //Create User Object
            Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * this below function is used to check that,
     * values are not empty
     */
    private fun inputCheck(
        patientfirstName: String,
        patientmiddleName: String,
        patientlastName: String,
        patientsex: String,
        patientbirthdate: String,
        DoctorID: String,
        DoctorFirstName: String,
        DoctorMiddleName: String,
        DoctorLastName: String
    ): Boolean {
        return !(TextUtils.isEmpty(patientfirstName) &&
                TextUtils.isEmpty(patientmiddleName) &&
                TextUtils.isEmpty(patientlastName) &&
                TextUtils.isEmpty(patientsex) &&
                TextUtils.isEmpty(patientbirthdate) &&
                TextUtils.isEmpty(DoctorID) &&
                TextUtils.isEmpty(DoctorFirstName) &&
                TextUtils.isEmpty(DoctorMiddleName) &&
                TextUtils.isEmpty(DoctorLastName))
    }

    private fun getImageUri(context: Context, Image: Uri): Bitmap {
        val source: ImageDecoder.Source = ImageDecoder.createSource(context.contentResolver, Image)
        return ImageDecoder.decodeBitmap(source)
    }

}