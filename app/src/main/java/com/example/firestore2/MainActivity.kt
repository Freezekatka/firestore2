package com.example.firestore2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import java.lang.reflect.Array
class MainActivity : AppCompatActivity() {
    private val charset = Charsets.UTF_8


    val db = Firebase.firestore
    var Tasks = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button = findViewById<Button>(R.id.button)
        var text = findViewById<TextView>(R.id.text)

        /*   loadSoloTask().forEach(){
              val state: MutableMap<String, Any> = HashMap()
              state["first"] = it
              db.collection("state").add(state)
          }*/


    db.collection("state").get()
    .addOnSuccessListener { result ->
        for (document in result){
            Tasks.add(document.get("first").toString())
        }
    }


        button.setOnClickListener {text.text = Tasks.removeAt(0)}


    }
    private fun loadSoloTask() :List<String>{
        var file = resources.assets.open("statements.txt")
        return file.reader(charset).use{
            it.readLines()
        }
    }

}

