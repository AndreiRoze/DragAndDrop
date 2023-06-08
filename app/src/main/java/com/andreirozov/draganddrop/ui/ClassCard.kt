package com.andreirozov.draganddrop.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreirozov.draganddrop.R
import com.andreirozov.draganddrop.data.ClassItem
import com.andreirozov.draganddrop.data.StudentItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClassCard(
    classItem: ClassItem,
    addStudent: (student: StudentItem, classId: Int) -> Unit,
    removeStudent: (student: StudentItem, classId: Int) -> Unit
) {
    DropTarget<StudentItem> { isInBound, student ->
        // Animate scale for smooth UI
        val sizeScale by animateFloatAsState(if (isInBound) 1.08f else 1.0f)

        // Add student to class
        student?.let {
            if (isInBound) addStudent(student, classItem.id)
        }

        Card(
            modifier = Modifier
                .fillMaxHeight()
                .width(160.dp)
                .scale(sizeScale)
                .animateContentSize()
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = classItem.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    text = "${stringResource(R.string.grade).lowercase()}: ${classItem.grade}",
                    fontSize = 18.sp
                )

                if (classItem.students.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        items(
                            items = classItem.students,
                            key = { student2 -> student2.id }
                        ) { student2 ->
                            StudentInClassCard(
                                modifier = Modifier.animateItemPlacement(),
                                student = student2,
                                classId = classItem.id,
                                removeStudent = removeStudent
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(R.string.empty_class),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StudentInClassCard(
    modifier: Modifier = Modifier,
    student: StudentItem,
    classId: Int,
    removeStudent: (student: StudentItem, classId: Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1.0f),
            text = "${student.surname} ${student.name} ",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )

        IconButton(
            onClick = {
                // Remove student from class
                removeStudent(student, classId)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.remove_student_from_class)
            )
        }
    }
}