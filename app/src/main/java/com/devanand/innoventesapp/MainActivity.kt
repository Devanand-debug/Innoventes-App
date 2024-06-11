package com.devanand.innoventesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.devanand.innoventesapp.databinding.ActivityMainBinding
import com.devanand.innoventesapp.factory.UserInputViewModelFactory
import com.devanand.innoventesapp.repository.UserRepository
import com.devanand.innoventesapp.viewmodel.UserInputViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private val viewModel : UserInputViewModel by viewModels{
        UserInputViewModelFactory(UserRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {

            val pan = binding.etPANCard.text.toString().trim()
            val day = binding.etDay.text.toString().trim()
            val month = binding.etMonth.text.toString().trim()
            val year = binding.etYear.text.toString().trim()

            viewModel.validateUserInput(pan,day,month,year)
        }

        viewModel.validationResult.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })

    }
}