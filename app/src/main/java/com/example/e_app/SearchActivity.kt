package com.example.e_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_search.*
import android.content.Intent

import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import java.io.InputStreamReader
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import java.net.URLEncoder
import java.io.DataOutputStream
import com.google.gson.*

class SearchActivity : AppCompatActivity() {
    var sourceText: String? = null
    private var statusProgress: ProgressBar?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //하단메뉴
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

        //검색창
        statusProgress = this.statusprogress

        btn_search.setOnClickListener {
            sourceText = txt_search.text.toString()
            //AsyncTask를 실행한다.
            AsyncTaskNMT().execute(sourceText)



            //Toast.makeText(this, "click: $sourceText", Toast.LENGTH_LONG).show()
        }

        btn_add_word.setOnClickListener {
            if(txt_word.text.length !=  0){
                val wordadd = WordList(txt_word_detail.text.toString(), txt_mean_detail.text.toString())
                val intent = Intent(this, WordActivity::class.java)
                intent.putExtra("word", wordadd.word)
                intent.putExtra("mean", wordadd.wordDetail)
                startActivity(intent)
            }
        }


    }

    inner class AsyncTaskNMT : AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            statusProgress?.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: String): String {

            val clientId = "sUoNOVQRX2ytJDihmSkH"//애플리케이션 클라이언트 아이디값";
            val clientSecret = "MoraHUpncQ"//애플리케이션 클라이언트 시크릿값";
            try {
                val text = URLEncoder.encode(params[0], "UTF-8") //넘어온게 배열이었어?
                val apiURL = "https://openapi.naver.com/v1/papago/n2mt"
                val url = URL(apiURL)
                val con = url.openConnection() as HttpURLConnection
                con.setRequestMethod("POST")
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                // post request
                val postParams = "source=en&target=ko&text=" + text
                con.doOutput = true
                println(con.outputStream)
                val wr = DataOutputStream(con.outputStream)
                wr.writeBytes(postParams)
                wr.flush()
                wr.close()
                val responseCode = con.responseCode
                val br: BufferedReader
                if (responseCode === 200) { // 정상 호출
                    br = BufferedReader(InputStreamReader(con.inputStream))
                } else {  // 에러 발생
                    br = BufferedReader(InputStreamReader(con.errorStream))
                }
                var inputLine: String? = null
                val response = StringBuffer()
                while ({inputLine = br.readLine(); inputLine}() != null) {
                    response.append(inputLine)
                }
                br.close()
                //println(response.toString())

                var gson = Gson() //오브젝트 생성
                //json file -> Gson object
                val parser = JsonParser()
                val rootObj = parser.parse(response.toString())
                    //원하는 데이터까지 찾아 들어간다.
                    .getAsJsonObject().get("message")
                    .getAsJsonObject().get("result")
                var post = gson.fromJson(rootObj, Message::class.java)
                var stringBuilder = StringBuilder("결과\n---------------------")
                stringBuilder?.append("\n소스언어: " + post.srcLangType)
                stringBuilder?.append("\n타겟언어: " + post.tarLangType)
                stringBuilder?.append("\n번역내용: " + post.translatedText)



                return post.translatedText.toString()


            } catch (e: Exception) {
                println(e)
            }

            return ""
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            statusProgress?.visibility = View.GONE

            //번역된 결과를 resultView에 출력하자.
            txt_word_detail.setText(txt_search.text)
            txt_mean_detail.setText(result)



        }
    }


}

data class Message(
    var srcLangType: String? = null,
    var tarLangType: String? = null,
    var translatedText: String? = null
)
