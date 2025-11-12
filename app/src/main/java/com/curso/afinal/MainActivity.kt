package com.curso.afinal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val textArray = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textoEditText = findViewById<TextInputEditText>(R.id.Texto)
        val numeroEditText = findViewById<TextInputEditText>(R.id.Numero)
        val anadirButton = findViewById<Button>(R.id.Anadir)
        val resultadoEditText = findViewById<EditText>(R.id.Resultado)
        val eliminarButton = findViewById<Button>(R.id.Eliminar)
        val mostrarButton = findViewById<Button>(R.id.Mostrar)

        anadirButton.setOnClickListener {
            val numero = numeroEditText.text.toString().toIntOrNull()
            if (numero != null && numero > 5) {
                resultadoEditText.setText("El número debe ser inferior o igual a 5")
                return@setOnClickListener
            }

            val texto = textoEditText.text.toString()
            if (texto.isEmpty()) {
                resultadoEditText.setText("No se puede agregar un valor vacio")
                return@setOnClickListener
            }

            val timesToAdd = numero ?: 1

            if (timesToAdd <= 0) {
                resultadoEditText.setText("El número debe ser un valor positivo.")
                return@setOnClickListener
            }

            for (i in 1..timesToAdd) {
                textArray.add(texto)
            }
            textoEditText.text?.clear()

            if (timesToAdd > 1) {
                resultadoEditText.setText("Textos añadidos al array")
            } else {
                resultadoEditText.setText("Texto añadido al array")
            }
        }

        eliminarButton.setOnClickListener {
            val numero = numeroEditText.text.toString().toIntOrNull()
            if (numero != null && numero > 5) {
                resultadoEditText.setText("El número debe ser inferior o igual a 5")
                return@setOnClickListener
            }

            val textoAEliminar = textoEditText.text.toString()
            if (textoAEliminar.isEmpty()) {
                resultadoEditText.setText("No se puede eliminar un valor vacio")
                return@setOnClickListener
            }

            val timesToRemove = numero ?: 1
            if (timesToRemove <= 0) {
                resultadoEditText.setText("El número debe ser un valor positivo.")
                return@setOnClickListener
            }

            var removedCount = 0
            for (i in 1..timesToRemove) {
                if (textArray.remove(textoAEliminar)) {
                    removedCount++
                }
            }

            if (removedCount > 0) {
                textoEditText.text?.clear()
                if (removedCount > 1) {
                    resultadoEditText.setText("Textos eliminados del array")
                } else {
                    resultadoEditText.setText("Texto eliminado del array")
                }
            } else {
                resultadoEditText.setText("El texto no se encuentra en el array")
            }
        }

        mostrarButton.setOnClickListener {
            if (textArray.isEmpty()) {
                resultadoEditText.setText("El array está vacío")
            } else {
                resultadoEditText.setText(textArray.joinToString(separator = "\n"))
            }
        }
    }
}