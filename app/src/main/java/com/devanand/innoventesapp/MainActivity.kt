package com.devanand.innoventesapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
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

    private lateinit var binding : ActivityMainBinding

    private val viewModel : UserInputViewModel by viewModels{
        UserInputViewModelFactory(UserRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textWatcherFun()


        binding.btnNext.setOnClickListener {
            Toast.makeText(this,"Details submitted successfully",Toast.LENGTH_SHORT).show()
            finish()
        }

        viewModel.validationResult.observe(this, Observer {
           // Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })

        viewModel.isNextButtonVisible.observe(this, Observer {isVisible ->
            binding.btnNext.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE

        })

        binding.tvDonotHavePan.setOnClickListener {
            finish()
        }

    }

    private fun textWatcherFun() {

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateInputs()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        binding.etPANCard.addTextChangedListener(textWatcher)
        binding.etDay.addTextChangedListener(textWatcher)
        binding.etMonth.addTextChangedListener(textWatcher)
        binding.etYear.addTextChangedListener(textWatcher)



        binding.etDay.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 2) {
                    binding.etMonth.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No implementation needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No implementation needed
            }
        })

        binding.etMonth.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 2) {
                    binding.etYear.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No implementation needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No implementation needed
            }
        })
    }

    private fun validateInputs() {
        val pan = binding.etPANCard.text.toString().trim()
        val day = binding.etDay.text.toString().trim()
        val month = binding.etMonth.text.toString().trim()
        val year = binding.etYear.text.toString().trim()

        viewModel.validateUserInput(pan,day,month,year)
    }
}