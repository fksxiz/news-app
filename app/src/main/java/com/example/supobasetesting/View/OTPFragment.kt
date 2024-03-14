package com.example.supobasetesting.View

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.chaos.view.PinView
import com.example.supobasetesting.R


class OTPFragment : Fragment() {

    private lateinit var pinView:PinView

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
        }
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