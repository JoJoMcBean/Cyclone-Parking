package com.example.helloworld

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sayHello(view: View) {
        // val myToast = Toast.makeText(this, message, duration);
        val helloMessage = Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT)
        helloMessage.show()
    }

    fun logSpace(view: View) {
        // Get the text view
        val showCountTextView = findViewById<TextView>(R.id.parkingSpaces)

        // Get the value of the text view.
        val countString = showCountTextView.text.toString()

        // Convert value to a number and increment it
        var count: Int = Integer.parseInt(countString)
        count++

        // Display the new value in the text view.
        showCountTextView.text = count.toString();
    }

    fun gotoDash(view: View) {
        val secondIntent = Intent(this, Main2Activity::class.java)

        // Start the new activity.
        startActivity(secondIntent)
    }

    fun gotoMaps(view: View) {
        val thirdIntent = Intent(this, MapsActivity::class.java)

        // Start the new activity.
        startActivity(thirdIntent)
    }
}
