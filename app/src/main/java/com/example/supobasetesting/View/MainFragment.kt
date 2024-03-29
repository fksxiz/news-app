package com.example.supobasetesting.View

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaos.view.PinView
import com.example.supobasetesting.R
import com.example.supobasetesting.ViewModel.NewsAdapter
import com.example.supobasetesting.ViewModel.OrdersAdapter
import com.example.supobasetesting.ViewModel.SupabaseViewModel
import kotlinx.coroutines.launch
import java.io.File


class MainFragment : Fragment() {

    private val viewModel:SupabaseViewModel by viewModels()

    private lateinit var pinView: PinView
    private lateinit var politicsTextView: TextView
    private lateinit var newsListView: RecyclerView
    private lateinit var ordersRecycleView:RecyclerView
    private lateinit var profileButton:ImageButton

    private var newsAdapter: NewsAdapter = NewsAdapter()
    private var ordersAdapter = OrdersAdapter()

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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            newsListView = findViewById(R.id.newsListView)
            politicsTextView=findViewById(R.id.politicsTextView)
            ordersRecycleView = findViewById(R.id.RecyclerView)
            profileButton = findViewById(R.id.profileButton)
        }
        profileButton.setOnClickListener(onProfileClickListener)
        politicsTextView.setOnClickListener(onPoliticsClickListener)
        val lr = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val lr2 = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        newsListView.layoutManager = lr
        newsListView.adapter = newsAdapter
        getNews()
        viewModel.news.observe(viewLifecycleOwner){
            newsAdapter.news = it
            newsAdapter.notifyDataSetChanged()
        }
        ordersRecycleView.layoutManager = lr2
        ordersRecycleView.adapter = ordersAdapter
        getOrders()
        viewModel.orders.observe(viewLifecycleOwner){
            ordersAdapter.orders =it
            ordersAdapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getNews(){
        lifecycleScope.launch {
            viewModel.getNews {
                newsAdapter.news=it
                newsAdapter.notifyDataSetChanged()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getOrders(){
        lifecycleScope.launch {
            viewModel.getOrders {
                ordersAdapter.orders = it
                ordersAdapter.notifyDataSetChanged()
            }
        }
    }

    fun copyAssetToFile(assetFile:String,file:File){
        requireContext().assets.open(assetFile).use { inputStream->
            file.outputStream().use { outputStream->
                inputStream.copyTo(outputStream)
            }
        }
    }

    private val onPoliticsClickListener = OnClickListener{
        //val url = File("file.pdf")
        val fileUri = Uri.parse("https://github.com/bumptech/glide")
        val intent = Intent(
            Intent.ACTION_VIEW,
            fileUri
        )
        startActivity(intent)

    }

    private val onProfileClickListener = OnClickListener {
        (activity as MainActivity).showFragment(ProfileFragment.newInstance())
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}