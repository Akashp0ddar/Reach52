package com.akash.reach52.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akash.reach52.R
import com.akash.reach52.databinding.FragmentSplashBinding


class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var binding: FragmentSplashBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}