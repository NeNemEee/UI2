package com.example.e_app


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class StudyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)

        btn_speech.setOnClickListener{
            val intent=Intent(this,StudyActivity::class.java)
            startActivity(intent)
        }

        btn_word_note.setOnClickListener(){
            val intent=Intent(this,WordActivity::class.java)
            startActivity(intent)
        }

        btn_word_search.setOnClickListener(){
            val intent=Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }

        btn_quiz.setOnClickListener(){
            val intent=Intent(this,StudyActivity::class.java)
            startActivity(intent)
        }
    }
}