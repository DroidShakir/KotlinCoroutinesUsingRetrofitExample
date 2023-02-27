package com.protocol.kotlincoroutineswithretrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.protocol.kotlincoroutineswithretrofit.adapter.MainAdapter
import com.protocol.kotlincoroutineswithretrofit.api.RetrofitService
import com.protocol.kotlincoroutineswithretrofit.databinding.ActivityMainBinding
import com.protocol.kotlincoroutineswithretrofit.repository.MainRepository
import com.protocol.kotlincoroutineswithretrofit.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val adapter = MainAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(mainRepository))[MainViewModel::class.java]

        viewModel.movieList.observe(this) {
            adapter.setMovieList(it)
        }

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })
        viewModel.getAllMovies()

        //Second commit
    }
}