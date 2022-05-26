package com.example.utilitiespkg.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.utilitiespkg.databinding.ActivityMainBinding
import com.example.utilitiespkg.utils.*
import java.io.File

class MainActivity : AppCompatActivity(), OnPositive {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            showAlert("this is utils dialog",
            "this is message ","Yes","Not",null,this)
        }

        val encoderSample :String = "this is a message"

        binding.button2.setOnClickListener {
            d("myEncode","${getMimeType(File("text.txt"))}")
        }

    }

    override fun onYes() {
        d("myDialog","Dialog Yes Click")
    }

    override fun onNo() {
        d("myDialog","Dialog No Click")
    }
}