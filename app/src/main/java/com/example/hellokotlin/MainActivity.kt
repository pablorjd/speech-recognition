package com.example.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts:TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this,this)

    findViewById<Button>(R.id.btnSpeak).setOnClickListener{speak()}
       // Log.i("message-textview", message)

    }

    private fun speak() {
        //var message:String =  findViewById<TextView>(R.id.textView).text.toString()
        val message2: String = findViewById<EditText>(R.id.etTextToRead).text.toString()
        findViewById<TextView>(R.id.textView).text = message2
        if (message2.isEmpty()) {
            Toast.makeText(this, "Debe Introducir un texto",Toast.LENGTH_LONG).show()
            return
        }
        tts!!.speak(message2, TextToSpeech.QUEUE_FLUSH, null, "")
    }



    override fun onInit(status: Int) {
        if ( status == TextToSpeech.SUCCESS ) {
            findViewById<TextView>(R.id.textView).text = getString(R.string.hello_kotlin)
            tts!!.language = Locale("ES")
        }else {
            findViewById<TextView>(R.id.textView).text = getString(R.string.no_disponible)
        }
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}