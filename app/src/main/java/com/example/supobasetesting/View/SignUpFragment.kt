package com.example.supobasetesting.View

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.ThemeUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.supobasetesting.Common.Validator
import com.example.supobasetesting.R
import com.example.supobasetesting.ViewModel.AuthViewModel
import com.example.supobasetesting.ViewModel.SupabaseViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch
import java.time.Duration


class SignUpFragment : Fragment() {

    private val authViewModel:AuthViewModel by viewModels()

    private var isSignUp:Boolean = true

    private lateinit var sharedPreferences:SharedPreferences

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var headerTextView: TextView
    private lateinit var nextButton: MaterialButton
    private lateinit var backButton: ImageView
    private lateinit var checkBox: CheckBox

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
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences(PREF_KEY,Context.MODE_PRIVATE)
        view.apply {
            backButton = findViewById(R.id.backButton)
            nextButton = findViewById(R.id.nextButton)
            emailEditText = findViewById(R.id.emailEditText)
            passwordEditText = findViewById(R.id.passwordEditText)
            headerTextView = findViewById(R.id.headerTextView)
            checkBox = findViewById(R.id.rememberCheckBox)
        }
        nextButton.setOnClickListener(onNextClickListener)
        backButton.setOnClickListener(onBackClickListener)
        if(!isSignUp){
            headerTextView.setText("Log in")
            getPreferences()
        }
    }

    private fun getPreferences(){
        emailEditText.setText(sharedPreferences.getString(EMAIL_KEY,""))
        passwordEditText.setText(sharedPreferences.getString(PASS_KEY,""))
        checkBox.isChecked = sharedPreferences.getBoolean(STATE_KEY,false)
    }

    private fun checkPreferences(){
            sharedPreferences.edit().apply {
                if(checkBox.isChecked) {
                    putString(EMAIL_KEY, emailEditText.text.toString())
                    putString(PASS_KEY, passwordEditText.text.toString())
                    putBoolean(STATE_KEY, checkBox.isChecked)
                }else{
                    putString(EMAIL_KEY, "")
                    putString(PASS_KEY, "")
                    putBoolean(STATE_KEY, checkBox.isChecked)
                }
            }.apply()
    }

    private val onNextClickListener = OnClickListener(){
        if(!Validator.validateEmail(emailEditText.text.toString())){
            Toast.makeText(requireContext(),"Почта введена не верно!",Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }
        if(passwordEditText.text.toString().isEmpty()){
            Toast.makeText(requireContext(),"Введите пароль!",Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }
        checkPreferences()
        if(isSignUp){
            lifecycleScope.launch {
                authViewModel.createUser(emailEditText.text.toString(),passwordEditText.text.toString()){
                    if(it){
                        (activity as MainActivity).showFragment(OTPFragment.newInstance(isSignUp))
                    }else{
                        return@createUser
                    }
                }
            }
        }else{
            lifecycleScope.launch {
                authViewModel.loginUser(emailEditText.text.toString(),passwordEditText.text.toString()){
                    if(it){
                        (activity as MainActivity).showFragment(MainFragment.newInstance())
                    }else{
                        return@loginUser
                    }
                }
            }
        }
    }

    private val onBackClickListener = OnClickListener(){
        (activity as MainActivity).showFragment(OnboardingFragment.newInstance())
    }

    companion object {

        public const val PREF_KEY = "PREF_KEY"
        public const val PASS_KEY = "PASS_KEY"
        public const val EMAIL_KEY = "EMAIL_KEY"
        public const val STATE_KEY = "STATE_KEY"

        @JvmStatic
        fun newInstance(isSignUpF:Boolean) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    isSignUp = isSignUpF
                }
            }
    }
}