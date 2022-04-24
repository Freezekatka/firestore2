package com.example.firestore2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val charset = Charsets.UTF_8//Charset.forName("windows-1251")

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var text = findViewById<TextView>(R.id.text)

           loadSoloTask().forEach(){
               val state: MutableMap<String, Any> = HashMap()
               state["first"] = it
               db.collection("state").add(state)
           }



        db.collection("state").get()
            .addOnSuccessListener { result ->
                for (document in result){
                    text.text = text.text.toString() + "${document.getString("first")} \n"
                }
            }

        }
    private fun loadSoloTask() :List<String>{
        var file = resources.assets.open("statements.txt")
        return file.reader(charset).use{
            it.readLines()
        }
    }

}