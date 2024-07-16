package com.example.assignment1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment1.R
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var destinationSpinner: Spinner
    private lateinit var selectButton: Button
    private lateinit var descriptionEditText: EditText
    private lateinit var saveButton: Button
    private var selectedDestination: String? = null
    private lateinit var descriptions: Array<String>
    private lateinit var textView2: TextView
    private lateinit var editButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        destinationSpinner = findViewById(R.id.destinationSpinner)
        selectButton = findViewById(R.id.selectButton)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        saveButton = findViewById(R.id.saveButton)
        textView2 = findViewById(R.id.TextView2)
        editButton = findViewById(R.id.editButton)

        val destinations = arrayOf("Mathematics", "French", "Physics", "Biology", "Chemistry")
        descriptions = arrayOf(
            "Topics for Mid term: " +
                    " \n1. Vectors " +
                    " \n2. Derivation " +
                    " \n3. Complex numbers ",
            "Second pop quiz next lecture",
            "Possible project ideas: " +
                    "\n1.Solar System" +
                    "\n2. Conservation of Momentum",
            "19th July Lab session is postponed to next week",
            "After class, discuss Assignment 2 with professor"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, destinations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        destinationSpinner.adapter = adapter

        destinationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedDestination = parent.getItemAtPosition(position) as String
                descriptionEditText.visibility = View.GONE
                descriptionEditText.setEnabled(false)
                textView2.visibility = View.GONE
                saveButton.visibility = View.GONE
                editButton.visibility = View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedDestination = null
            }
        }

        selectButton.setOnClickListener {
            if (selectedDestination != null) {
                val position = destinationSpinner.selectedItemPosition
                descriptionEditText.setText(descriptions[position])
                textView2.visibility = View.VISIBLE
                descriptionEditText.visibility = View.VISIBLE
                saveButton.visibility = View.VISIBLE
                editButton.visibility = View.VISIBLE

                Toast.makeText(
                    this,
                    "Selected Subject: $selectedDestination",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Please select a subject", Toast.LENGTH_SHORT).show()
            }
        }

        saveButton.setOnClickListener {
            val position = destinationSpinner.selectedItemPosition
            descriptions[position] = descriptionEditText.text.toString()
            descriptionEditText.setEnabled(false)
            Toast.makeText(this, "Notes saved", Toast.LENGTH_SHORT).show()


        }
        editButton.setOnClickListener {
            descriptionEditText.setEnabled(true)
            descriptionEditText.requestFocus()
        }
    }
}