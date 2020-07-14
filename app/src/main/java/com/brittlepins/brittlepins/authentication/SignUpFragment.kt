package com.brittlepins.brittlepins.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.brittlepins.brittlepins.R

class SignUpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.sign_up_to_log_in_button).setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLogInFragment()
            view.findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.sign_up_button).setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToMainNavActivity()
            view.findNavController().navigate(action)
        }
    }
}
