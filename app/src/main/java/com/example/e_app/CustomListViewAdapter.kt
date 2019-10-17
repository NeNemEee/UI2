package com.example.e_app

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.widget.TextView



class CustomListViewAdapter(val context : Context, val wordarray : Array<WordList>)  : BaseAdapter() {
    override fun getCount(): Int {
        return wordarray.size
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getItem(p0: Int): Any {
        return wordarray[p0]
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = p1 ?: LayoutInflater.from(context).inflate(R.layout.list_word, p2, false) as View
        p1?.findViewById<TextView>(R.id.word)?.text = wordarray[p0].word
        p1?.findViewById<TextView>(R.id.word_detail)?.text = wordarray[p0].wordDetail

        return view
    }
}
