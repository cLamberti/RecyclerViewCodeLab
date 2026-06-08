package com.example.recyclerviewcodelab
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcodelab.databinding.ItemCourseBinding
class CourseAdapter(
    private val onItemClick: (Course) -> Unit
) : ListAdapter<Course, CourseAdapter.CourseViewHolder>(CourseDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
// Inflamos el XML usando ViewBinding
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }
    inner class CourseViewHolder(
        private val binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) {
// Asignación de datos
            binding.tvCourseTitle.text = course.title
            binding.tvCourseCategory.text = course.category
            binding.tvCourseLevel.text = "Nivel: ${course.level}"
// Configuración del click listener
            binding.root.setOnClickListener {

                onItemClick(course)
            }
        }
    }
}
// DiffUtil para calcular diferencias en segundo plano
class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
// Compara identificadores únicos. ¿Es el mismo objeto conceptual?
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
// Compara el contenido visual. Si el ID es igual, ¿cambió algún texto?
// En Kotlin, los data classes implementan equals() automáticamente.
        return oldItem == newItem
    }
}