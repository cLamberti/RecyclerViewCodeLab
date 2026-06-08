package com.example.recyclerviewcodelab

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewcodelab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        loadData()
    }

    private fun setupRecyclerView() {
        courseAdapter = CourseAdapter { course ->
            Toast.makeText(
                this,
                "Seleccionaste: ${course.title}",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.recyclerViewCourses.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = courseAdapter
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                this@MainActivity,
                LinearLayoutManager.VERTICAL
            )
            addItemDecoration(divider)
        }
    }

    private fun loadData() {
        val courses = listOf(
            Course(1, "Android Fundamentals", "Mobile Development", "Beginner"),
            Course(2, "Kotlin for Android", "Programming", "Intermediate"),
            Course(3, "Jetpack Navigation", "Architecture", "Intermediate"),
            Course(4, "RecyclerView Mastery", "UI Components", "Advanced"),
            Course(5, "Room Database", "Persistence", "Intermediate"),
            Course(6, "Dependency Injection", "Architecture", "Advanced"),
            Course(7, "Unit Testing", "Quality", "Intermediate"),
            Course(8, "Clean Architecture", "Best Practices", "Advanced")
        )
        courseAdapter.submitList(courses)
    }
}
