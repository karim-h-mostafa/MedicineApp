package com.karim.medicine.ui.greetingscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.karim.domain.entity.Medicines

@Composable
fun GreetingScreen(
    modifier: Modifier = Modifier,
    userName: String,
    onMedicineClick: (Medicines.Medicine) -> Unit
) {
    val greetingViewModel: GreetingViewModel = hiltViewModel()
    val greeting = greetingViewModel.greeting

    val medicines by greetingViewModel.medicines.collectAsStateWithLifecycle()
    Column(
        modifier = modifier.padding(horizontal = 36.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "$greeting ,\n$userName", fontSize = 22.sp)
        medicines?.let { medicines ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(36.dp)
            ) {
                items(
                    items = medicines.medicines
                ) { item: Medicines.Medicine ->
                    MedicineItem(item = item, onMedicineClick)
                }
            }
        }
    }

}

@Composable
fun MedicineItem(item: Medicines.Medicine, onMedicineClick: (Medicines.Medicine) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(onClick = { onMedicineClick(item) }, modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "name : ${item.name.ifEmpty { "missed" }}")
                Text(text = "dose : ${item.dose.ifEmpty { "missed" }}")
                Text(text = "strength : ${item.strength.ifEmpty { "missed" }}")
            }
        }

    }
}