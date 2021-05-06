package com.bignerdranch.android.food_recipes_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_getstarted.*

class GetStartedScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getstarted)

        getStartedButton.setOnClickListener{
            var intent = Intent(this@GetStartedScreen, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }

    }
}