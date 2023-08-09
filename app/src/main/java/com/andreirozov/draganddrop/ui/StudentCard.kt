package com.andreirozov.draganddrop.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreirozov.draganddrop.R
import com.andreirozov.draganddrop.data.StudentItem

@Composable
fun StudentCard(
    modifier: Modifier = Modifier,
    student: StudentItem
) {
    DragTarget(
        modifier = modifier,
        dataToDrop = student
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1.0f)
                ) {
                    Text(
                        text = student.surname,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Text(
                        text = student.name,
                        fontSize = 18.sp
                    )
                }

                Text(
                    text = "${student.age} ${stringResource(R.string.years).lowercase()}",
                    fontSize = 18.sp
                )
            }
        }
    }
}