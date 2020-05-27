package com.example.sharedatabetweenapp1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_save_random_num.setOnClickListener {
            val randomNumber = Random().nextInt((100 - 0) + 1) + 0
            saveRandomNumber(randomNumber.toString())
        }

        btn_load_random_num.setOnClickListener {
            loadRandomNumber()
        }
    }

    private fun saveRandomNumber(number: String) {
        var fileOutputStream: FileOutputStream

        try {
            fileOutputStream = openFileOutput("ankitfile.txt", Context.MODE_PRIVATE)
            fileOutputStream.write(number.toByteArray())
            fileOutputStream.close()

            txt_save_msg.text = "$number text written into ${filesDir.absolutePath}"
        } catch (e: Exception) {
            txt_save_msg.text = "Error in saving data into ${filesDir.absolutePath}"
        }
    }

    private fun loadRandomNumber() {
        var fileInputStream: FileInputStream
        val outputData = StringBuffer()

        try {
            fileInputStream = openFileInput("ankitfile.txt")

            var read: Int = 0

            while ({ read = fileInputStream.read(); read }() != -1) {
                outputData.append(read.toChar())
            }

            txt_load_msg.text = "${outputData.toString()} loaded from ${filesDir.absolutePath}"
        } catch (e: Exception) {
            txt_load_msg.text = "Error in loading data into ${filesDir.absolutePath}"
        }
    }

}
