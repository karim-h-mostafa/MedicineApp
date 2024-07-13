package com.karim.medicine.ui.medicinedetailsscreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.karim.domain.entity.Medicines
import com.karim.medicine.ui.mainscreen.SharedViewModel

@Composable
fun MedicineDetailsScreen(modifier: Modifier = Modifier, sharedViewModel: SharedViewModel) {
    val medicine by sharedViewModel.selectedMedicine.collectAsStateWithLifecycle()
    Log.d("MedicineDetailsScreen", "MedicineDetailsScreen: $medicine")
    medicine?.let { med ->
        Log.d("MedicineDetailsScreen", "MedicineDetailsScreen: $med")
        Column(
            modifier.fillMaxWidth().padding(36.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Medicine Details", modifier = Modifier.align(Alignment.CenterHorizontally))
            Card(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(text = "name : ${med.name.ifEmpty { "missed" }}")
                    Text(text = "dose : ${med.dose.ifEmpty { "missed" }}")
                    Text(text = "strength : ${med.strength.ifEmpty { "missed" }}")
                }
            }

        }
    }

}
