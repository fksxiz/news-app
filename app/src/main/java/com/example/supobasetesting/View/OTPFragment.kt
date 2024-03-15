package com.example.supobasetesting.View

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chaos.view.PinView
import com.example.supobasetesting.Common.Email
import com.example.supobasetesting.Common.Token
import com.example.supobasetesting.R
import com.example.supobasetesting.ViewModel.AuthViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch


class OTPFragment : Fragment() {

    private val authViewModel:AuthViewModel by viewModels()

    private var isSignUp:Boolean = true

    private lateinit var pinView:PinView
    private lateinit var timeTextView:TextView
    private lateinit var headerTextView: TextView
    private lateinit var submitButton: MaterialButton
    private lateinit var backButton: ImageView
    private lateinit var resendButton: TextView
    private var canResend:Boolean = false

    private var time:Int=60

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
        return inflater.inflate(R.layout.fragment_o_t_p, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            pinView = findViewById(R.id.otpPinView)
            timeTextView = findViewById(R.id.timerTextView)
            headerTextView = findViewById(R.id.headerTextView)
            submitButton = findViewById(R.id.submitButton)
            backButton = findViewById(R.id.backButton)
            resendButton = findViewById(R.id.resendTextView)
        }
        startTimer()
        if(!isSignUp){
            headerTextView.setText("Log in")
        }
        submitButton.setOnClickListener(onSubmitClickListener)
        backButton.setOnClickListener(onBackClickListener)
        resendButton.setOnClickListener(onResendClickListener)
    }

    private val onSubmitClickListener = OnClickListener{
        /*if(pinView.text.toString() == Token.token){

        }*/
        if(Email.email==null) return@OnClickListener
        lifecycleScope.launch {
            authViewModel.loginOTP(Email.email!!,pinView.text.toString()){
                if(it){
                    (activity as MainActivity).showFragment(MainFragment.newInstance())
                }else{
                    Toast.makeText(requireContext(),"Invalid code",Toast.LENGTH_SHORT)
                }
            }
        }
    }

    private val onBackClickListener = OnClickListener{
        (activity as MainActivity).showFragment(SignUpFragment.newInstance(isSignUp))
    }

    @SuppressLint("SuspiciousIndentation")
    private val onResendClickListener = OnClickListener{
        if(Email.email==null) return@OnClickListener
        lifecycleScope.launch {
            authViewModel.resendOTP(Email.email!!){
                if(it){
                    if(canResend)
                    startTimer()
                }else{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT)
                }
            }
        }
    }

    fun startTimer(){
            canResend=false
            time = 60
            val timer = object : CountDownTimer(60000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    time--
                    timeTextView.setText(time.toString())
                }

                override fun onFinish() {
                    canResend = true
                }
            }.start()
    }

    companion object {

        @JvmStatic
        fun newInstance(isSignUpF:Boolean) =
            OTPFragment().apply {
                arguments = Bundle().apply {
                    isSignUp = isSignUpF
                }
            }
    }
}