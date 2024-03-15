package com.example.supobasetesting.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import com.example.supobasetesting.R
import com.google.android.material.button.MaterialButton


class OnboardingFragment : Fragment() {

    private lateinit var signUpButton: MaterialButton
    private lateinit var logInButton: MaterialButton
    private lateinit var skipButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            signUpButton = findViewById(R.id.signUpButton)
            logInButton = findViewById(R.id.logInButton)
            skipButton = findViewById(R.id.skipTextView)
        }
        signUpButton.setOnClickListener(onSignUpClickListener)
        logInButton.setOnClickListener(onLogInClickListener)
    }

    private val onSignUpClickListener = OnClickListener(){
        (activity as MainActivity).showFragment(SignUpFragment.newInstance(true))
    }

    private val onLogInClickListener = OnClickListener(){
        (activity as MainActivity).showFragment(SignUpFragment.newInstance(false))
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            OnboardingFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}