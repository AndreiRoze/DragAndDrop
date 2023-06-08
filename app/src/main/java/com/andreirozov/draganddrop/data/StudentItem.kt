package com.andreirozov.draganddrop.data

data class StudentItem(
    val id: Int,
    val name: String,
    val surname: String,
    val age: Int,
    var inClass: Boolean
)