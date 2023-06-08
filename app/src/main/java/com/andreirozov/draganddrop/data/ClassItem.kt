package com.andreirozov.draganddrop.data

import androidx.compose.runtime.snapshots.SnapshotStateList

data class ClassItem(
    val id: Int,
    val name: String,
    val grade: Int,
    val students: SnapshotStateList<StudentItem>
)