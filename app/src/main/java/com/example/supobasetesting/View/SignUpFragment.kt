package com.example.supobasetesting.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
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

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var headerTextView: TextView
    private lateinit var nextButton: MaterialButton
    private lateinit var backButton: ImageView

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
        view.apply {
            backButton = findViewById(R.id.backButton)
            nextButton = findViewById(R.id.nextButton)
            emailEditText = findViewById(R.id.emailEditText)
            passwordEditText = findViewById(R.id.passwordEditText)
            headerTextView = findViewById(R.id.headerTextView)
        }
        nextButton.setOnClickListener(onNextClickListener)
        backButton.setOnClickListener(onBackClickListener)
        if(!isSignUp){
            headerTextView.setText("Log in")
        }
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

        @JvmStatic
        fun newInstance(isSignUpF:Boolean) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    isSignUp = isSignUpF
                }
            }
    }
}