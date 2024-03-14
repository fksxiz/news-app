package com.example.supobasetesting.View

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.supobasetesting.R


class SplashFragment : Fragment() {


    //private lateinit var timer: Timer

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
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startTimer()
    }

    private fun startTimer(){
        val timer = object : CountDownTimer(1500,500){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                (activity as MainActivity).showFragment(OnboardingFragment.newInstance())
            }
        }.start()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SplashFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}