package com.example.e_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView

class WordActivity : AppCompatActivity() {

    val button: Button=findViewById(R.id.list1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        button.setOnClickListener{
            val intent=Intent(this,StudyActivity::class.java)
            startActivityForResult(intent,1)
            //startActivity(intent)
        }
    }
}
