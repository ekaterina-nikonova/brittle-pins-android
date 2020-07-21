package com.brittlepins.brittlepins.authentication.login

import android.os.Bundle
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
import com.brittlepins.brittlepins.network.AuthServices

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
            logInWith(it, LogIn(logInViewModel.email.value ?: "", ""))
        }
    }

    fun logInWith(view: View, logIn: LogIn) {
        AuthServices.logIn(logIn)

        val action =
            LogInFragmentDirections.actionLogInFragmentToMainNavActivity()
        view.findNavController().navigate(action)
    }
}
