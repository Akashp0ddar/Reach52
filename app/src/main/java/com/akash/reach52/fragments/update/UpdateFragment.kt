package com.akash.reach52.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.akash.reach52.R
import com.akash.reach52.databinding.FragmentUpdateBinding
import com.akash.reach52.model.PatientList
import com.akash.reach52.viewmodel.PatientViewModel


class UpdateFragment : Fragment(R.layout.fragment_update), MenuProvider {
    private val args by navArgs<UpdateFragmentArgs>()
    private var binding: FragmentUpdateBinding? = null
    private val viewModel by viewModels<PatientViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)
        settingData()
        onClick()

        requireActivity().addMenuProvider(this, viewLifecycleOwner)

    }

    private fun onClick() {
        binding?.btnAddPatientDetailsUpdate?.setOnClickListener {
            updateItem()
        }
    }

    private fun settingData() {
        binding?.etPatientNameUpdate?.setText(args.currentPatient.FirstName)
        binding?.etPatientMiddleNameUpdate?.setText(args.currentPatient.MiddleName)
        binding?.etPatientLastNameUpdate?.setText(args.currentPatient.LastName)
        binding?.etPatientSexUpdate?.setText(args.currentPatient.Sex)
        binding?.etPatientBirthdateUpdate?.setText(args.currentPatient.BirthDate)
        binding?.etDoctorIDUpdate?.setText(args.currentPatient.DoctorID)
        binding?.etDoctorFirstNameUpdate?.setText(args.currentPatient.DoctorFirstName)
        binding?.etDoctorMiddleNameUpdate?.setText(args.currentPatient.DoctorMiddleName)
        binding?.etDoctorLastNameUpdate?.setText(args.currentPatient.DoctorLastName)
        binding?.etDoctorDateUpdate?.setText(args.currentPatient.DoctorDate)
        binding?.ivPatientImageUpdate?.setImageBitmap(args.currentPatient.patientImage)
    }


    private fun updateItem() {
        val patientFirstName = binding?.etPatientNameUpdate?.text.toString()
        val patientMiddleName = binding?.etPatientMiddleNameUpdate?.text.toString()
        val patientLastName = binding?.etPatientLastNameUpdate?.text.toString()
        val sex = binding?.etPatientSexUpdate?.text.toString()
        val birthdate = binding?.etPatientBirthdateUpdate?.text.toString()

        // Inserting Doctor details
        val doctorID = binding?.etDoctorIDUpdate?.text.toString()
        val doctorFirstName = binding?.etDoctorFirstNameUpdate?.text.toString()
        val doctorMiddleName = binding?.etDoctorMiddleNameUpdate?.text.toString()
        val doctorLastName = binding?.etDoctorLastNameUpdate?.text.toString()
        val doctorDate = binding?.etDoctorDateUpdate?.text.toString()
        if (inputCheck(
                patientFirstName,
                patientMiddleName,
                patientLastName,
                sex,
                birthdate,
                doctorID,
                doctorFirstName,
                doctorMiddleName,
                doctorLastName,
                doctorDate
            )
        ) {
            val updatePatient = PatientList(
                id = args.currentPatient.id,
                FirstName = patientFirstName,
                MiddleName = patientMiddleName,
                LastName = patientLastName,
                Sex = sex,
                BirthDate = birthdate,
                DoctorFirstName = doctorFirstName,
                DoctorMiddleName = doctorMiddleName,
                DoctorLastName = doctorLastName,
                DoctorID = doctorID,
                DoctorDate = doctorDate,
                patientImage = args.currentPatient.patientImage
            )

            viewModel.updatePatient(updatePatient)

            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all the fields", Toast.LENGTH_SHORT)
                .show()

        }
    }

    private fun inputCheck(
        patientfirstName: String,
        patientmiddleName: String,
        patientlastName: String,
        patientsex: String,
        patientbirthdate: String,
        DoctorID: String,
        DoctorFirstName: String,
        DoctorMiddleName: String,
        DoctorLastName: String,
        DoctorDate: String
    ): Boolean {
        return !(TextUtils.isEmpty(patientfirstName) &&
                TextUtils.isEmpty(patientmiddleName) &&
                TextUtils.isEmpty(patientlastName) &&
                TextUtils.isEmpty(patientsex) &&
                TextUtils.isEmpty(patientbirthdate) &&
                TextUtils.isEmpty(DoctorID) &&
                TextUtils.isEmpty(DoctorFirstName) &&
                TextUtils.isEmpty(DoctorMiddleName) &&
                TextUtils.isEmpty(DoctorLastName) &&
                TextUtils.isEmpty(DoctorDate))
    }


    private fun deletePatient() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deletePatient(args.currentPatient)
            Toast.makeText(
                requireContext(),
                "Deleted Successfully removed ${args.currentPatient.FirstName}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete${args.currentPatient.FirstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentPatient.FirstName}?")
        builder.create().show()
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.menu_delete) {
            deletePatient()
        }
        return super.onContextItemSelected(menuItem)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}
