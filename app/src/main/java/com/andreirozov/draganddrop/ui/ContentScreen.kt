package com.andreirozov.draganddrop.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andreirozov.draganddrop.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentScreen(
    contentViewModel: ContentViewModel = viewModel()
) {
    val students by contentViewModel.students.collectAsStateWithLifecycle()
    val classes by contentViewModel.classes.collectAsStateWithLifecycle()

    Scaffold {
        LongPressDraggable(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                if (students.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.7f),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(
                            items = students,
                            key = { student -> student.id }
                        ) { student ->
                            StudentCard(
                                modifier = Modifier.animateItemPlacement(),
                                student = student
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(0.7f),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(R.string.no_available_students),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.3f),
                    tonalElevation = 4.dp,
                    shadowElevation = 4.dp
                ) {
                    LazyRow(
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(items = classes) { classItem ->
                            ClassCard(
                                classItem = classItem,
                                addStudent = { student, classId ->
                                    contentViewModel.addStudentToClass(
                                        student = student,
                                        classId = classId
                                    )
                                },
                                removeStudent = { student, classId ->
                                    contentViewModel.removeStudentFromClass(
                                        student = student,
                                        classId = classId
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

