package com.andi.recipeapp

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andi.recipeapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rezeptListe: ListView
    private lateinit var addButton: FloatingActionButton
    private lateinit var rezepte: ArrayList<String>
    private lateinit var itemAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        rezeptListe = findViewById(R.id.rezeptListe)
        addButton = findViewById(R.id.addButton)

        rezepte = ArrayList()

        rezepte.add("Spaghetti")
        rezepte.add("Ofenkartoffel")
        rezepte.add("Fisch")
        rezepte.add("test")

        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, rezepte)
        rezeptListe.adapter = itemAdapter

        rezeptListe.setOnItemLongClickListener(AdapterView.OnItemLongClickListener{arg0, aeg1, pos, id ->
            rezepte.removeAt(pos)
            itemAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext, "gelöscht", Toast.LENGTH_SHORT).show()
            true
        })




        addButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Hinzufügen")

            val input = EditText(this)
            input.hint = "Text eingeben"
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("OK"){dialog, which ->
                rezepte.add(input.text.toString())
            }

            builder.setNegativeButton("Abbrechen"){dialog, which ->
                Toast.makeText(applicationContext, "Abgebrochen", Toast.LENGTH_SHORT).show()

            }

            builder.show()
        }




    }
}