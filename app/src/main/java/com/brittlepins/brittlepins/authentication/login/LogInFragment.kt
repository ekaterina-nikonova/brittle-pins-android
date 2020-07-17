package com.brittlepins.brittlepins.authentication.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.brittlepins.brittlepins.R
import com.brittlepins.brittlepins.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private lateinit var logInViewModel : LogInViewModel
    private lateinit var logInViewModelFactory : LogInViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logInViewModelFactory = LogInViewModelFactory()
        logInViewModel = ViewModelProvider(this, logInViewModelFactory)
            .get(LogInViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentLogInBinding>(
            inflater, R.layout.fragment_log_in, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = logInViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.log_in_to_sign_up_button).setOnClickListener {
            val action =
                LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
            it.findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.log_in_button).setOnClickListener {
            Log.d("LOG_IN_FRAGMENT", logInViewModel.email.value ?: "No email")
            val action =
                LogInFragmentDirections.actionLogInFragmentToMainNavActivity()
            it.findNavController().navigate(action)
        }
    }
}
