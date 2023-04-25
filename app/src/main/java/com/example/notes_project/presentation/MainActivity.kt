package com.example.notes_project.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.*
import com.example.notes_project.*
import com.example.notes_project.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding

    lateinit var appComponent: AppComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        context = applicationContext
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //lifecycle scope запустит эту нашу корутину, которая у нас есть, сразу же, как только стартанёт этот lifecycleScope
        appComponent = DaggerAppComponent.builder().mainActivity(this).app(application).context(applicationContext).build()
        appComponent.inject(this)
        //запустит корутину, когда активность будет в состоянии started

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.msg1.collect{
                    for(i in it){
                        Toast.makeText(applicationContext, "${i}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.button.setOnClickListener {
            viewModel.insertNote(note = Note(0, "header", binding.editTextTextPersonName.text.toString()))
        }

    }
}