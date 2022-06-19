package com.example.usergit.ui.detailingUser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.usergit.databinding.ActivityDetailingUserBinding

class DetailingUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailingUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailingUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}