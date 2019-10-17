package com.example.e_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.btn_quiz
import kotlinx.android.synthetic.main.activity_main.btn_speech
import kotlinx.android.synthetic.main.activity_main.btn_word_note
import kotlinx.android.synthetic.main.activity_main.btn_word_search
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_word.*

class WordActivity : AppCompatActivity() {

    var wordlist = arrayOf(WordList("sd","sdsd"), WordList("dssd", "sdsd"))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)



        if(intent.hasExtra("word")){
            if(intent.hasExtra("mean")){
                wordlist.plusElement(WordList(intent.getStringExtra("word"), intent.getStringExtra("mean")))


            }
        }


        var adapter = CustomListViewAdapter(this, wordlist)
        listview_word.adapter = adapter










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
/*
       btn_add_word.setOnClickListener(){

        }
*/

    }



}
