package com.example.supobasetesting.View

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.supobasetesting.R

class ProfileFragment : Fragment() {

    private lateinit var newsButton:ImageButton
    private lateinit var avatarImageView:ImageView
    private lateinit var emailTextView:TextView

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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            newsButton = findViewById(R.id.newsButton)
            avatarImageView = findViewById(R.id.avatarImageView)
            emailTextView = findViewById(R.id.emailTextView)
        }

        newsButton.setOnClickListener(onNewsClickListener)
        avatarImageView.setOnClickListener(onAvatarClickListener)
    }

    private val onNewsClickListener = OnClickListener{
        (activity as MainActivity).showFragment(MainFragment.newInstance())
    }

    private val onAvatarClickListener = OnClickListener {
        /*val takePicture = Intent(MediaStore.ACTION_PICK_IMAGES)
        startActivity(takePicture)*/
        val url = "https://picsum.photos/300"
        Glide.with(this)
            .load(url)
            .fitCenter()
            .into(avatarImageView)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}