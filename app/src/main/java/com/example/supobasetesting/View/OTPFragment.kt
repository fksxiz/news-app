package com.example.supobasetesting.View

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.chaos.view.PinView
import com.example.supobasetesting.R


class OTPFragment : Fragment() {

    private lateinit var pinView:PinView
    private lateinit var timeTextView:TextView

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
        }
        startTimer()
    }

    fun startTimer(){
        time=60
        val timer = object :CountDownTimer(60000,1000){
            override fun onTick(millisUntilFinished: Long) {
                time--
                timeTextView.setText(time.toString())
            }

            override fun onFinish() {
            }
        }.start()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            OTPFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}