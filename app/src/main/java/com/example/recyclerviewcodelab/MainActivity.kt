package com.example.recyclerviewcodelab

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewcodelab.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicializamos ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        loadInitialData()
    }

    private fun setupRecyclerView() {
        // Inicializamos el Adapter pasando una función lambda para el clic
        courseAdapter = CourseAdapter { selectedCourse ->
            Toast.makeText(
                this,
                "Seleccionado: ${selectedCourse.title}",
                Toast.LENGTH_SHORT
            ).show()
        }
        // Configuramos el RecyclerView
        binding.recyclerViewCourses.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = courseAdapter
            setHasFixedSize(true) // Optimización sugerida por la documentación
        }
    }

    private fun loadInitialData() {
        val courses = listOf(
            Course(1, "Kotlin Desde Cero", "Programación", "Básico"),
            Course(2, "Arquitectura MVVM", "Arquitectura", "Intermedio"),
            Course(3, "Master en RecyclerView", "UI/UX", "Avanzado"),
            Course(4, "Inyección de Dependencias", "Arquitectura", "Avanzado"),
            Course(5, "Corrutinas en Android", "Concurrencia", "Intermedio")
        )
        // Enviamos la lista al adaptador
        courseAdapter.submitList(courses)
    }
}
