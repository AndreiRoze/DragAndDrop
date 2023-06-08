package com.andreirozov.draganddrop.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.andreirozov.draganddrop.data.ClassItem
import com.andreirozov.draganddrop.data.StudentItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContentViewModel : ViewModel() {
    private val _students = MutableStateFlow<SnapshotStateList<StudentItem>>(mutableStateListOf())
    val students: StateFlow<SnapshotStateList<StudentItem>> = _students.asStateFlow()

    private val _classes = MutableStateFlow<MutableList<ClassItem>>(mutableListOf())
    val classes: StateFlow<MutableList<ClassItem>> = _classes.asStateFlow()

    init {
        fillData()
    }

    private fun fillData() {
        _students.value.add(
            StudentItem(
                id = 0,
                name = "Alex",
                surname = "Smith",
                age = 15,
                inClass = false
            )
        )
        _students.value.add(
            StudentItem(
                id = 1,
                name = "Andrei",
                surname = "Roze",
                age = 13,
                inClass = false
            )
        )
        _students.value.add(
            StudentItem(
                id = 2,
                name = "Nick",
                surname = "Bennet",
                age = 14,
                inClass = false
            )
        )
        _students.value.add(
            StudentItem(
                id = 3,
                name = "John",
                surname = "Miller",
                age = 11,
                inClass = false
            )
        )
        _students.value.add(
            StudentItem(
                id = 4,
                name = "Serge",
                surname = "Graves",
                age = 12,
                inClass = false
            )
        )
        _students.value.add(
            StudentItem(
                id = 5,
                name = "Mika",
                surname = "Gallant",
                age = 15,
                inClass = false
            )
        )
        _students.value.add(
            StudentItem(
                id = 6,
                name = "Henrik",
                surname = "Gilbert",
                age = 13,
                inClass = false
            )
        )
        _students.value.add(
            StudentItem(
                id = 7,
                name = "Sidney",
                surname = "White",
                age = 14,
                inClass = false
            )
        )
        _students.value.add(
            StudentItem(
                id = 8,
                name = "Jack",
                surname = "Fox",
                age = 11,
                inClass = false
            )
        )
        _students.value.add(
            StudentItem(
                id = 9,
                name = "Matthew",
                surname = "Kane",
                age = 12,
                inClass = false
            )
        )

        _classes.value.add(
            ClassItem(
                id = 0,
                name = "Math",
                grade = 5,
                students = SnapshotStateList()
            )
        )
        _classes.value.add(
            ClassItem(
                id = 1,
                name = "Chemistry",
                grade = 6,
                students = SnapshotStateList()
            )
        )
        _classes.value.add(
            ClassItem(
                id = 2,
                name = "Biology",
                grade = 6,
                students = SnapshotStateList()
            )
        )
        _classes.value.add(
            ClassItem(
                id = 3,
                name = "Music",
                grade = 5,
                students = SnapshotStateList()
            )
        )
        _classes.value.add(
            ClassItem(
                id = 4,
                name = "Art",
                grade = 5,
                students = SnapshotStateList()
            )
        )
    }

    fun addStudentToClass(student: StudentItem, classId: Int) {
        // Find class -> add student -> delete student from students list, if class not exist do nothing
        _classes.value.firstOrNull { classItem -> classItem.id == classId }?.let { classItem ->
            student.inClass = true
            classItem.students.add(student)
            _students.value.remove(student)
        }
    }

    fun removeStudentFromClass(student: StudentItem, classId: Int) {
        // Find class -> remove student -> add student to students list, if class not exist do nothing
        _classes.value.firstOrNull { classItem -> classItem.id == classId }?.let { classItem ->
            student.inClass = false
            classItem.students.remove(student)
            _students.value.add(student)
        }
    }
}