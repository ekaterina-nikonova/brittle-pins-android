package com.brittlepins.brittlepins.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.brittlepins.brittlepins.R

class LogInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.log_in_to_sign_up_button).setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
            it.findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.log_in_button).setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToMainNavActivity()
            it.findNavController().navigate(action)
        }
    }
}
