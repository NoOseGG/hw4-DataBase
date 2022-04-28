package com.example.hw4database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hw4database.databinding.ActivityMainBinding
import com.example.hw4database.navigation.MainFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Repositories.init(applicationContext)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_main_activity, MainFragment())
            .commit()
    }
}