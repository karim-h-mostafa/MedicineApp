package com.karim.medicine.ui.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.karim.core.util.common.ErrorManager
import com.karim.domain.entity.Medicines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _selectedMedicine = MutableStateFlow<Medicines.Medicine?>(null)
    val selectedMedicine get() = _selectedMedicine.asStateFlow()
    fun setSelectedMedicine(medicine: Medicines.Medicine) {
        _selectedMedicine.value = medicine
    }

}