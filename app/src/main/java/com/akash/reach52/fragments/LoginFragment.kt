package com.akash.reach52.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akash.reach52.R
import com.akash.reach52.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {
    private var binding: FragmentLoginBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        onClick()
    }

    private fun onClick() {
        binding?.btnLogin?.setOnClickListener {
            if (binding?.etEmail?.text?.length!! > 0 && binding?.etPassword?.text?.length!! > 0) {

                findNavController().navigate(R.id.action_loginFragment_to_listFragment)

            } else Toast.makeText(requireContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
