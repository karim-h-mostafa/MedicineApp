package com.karim.medicine.ui.greetingscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karim.core.util.common.ErrorManager
import com.karim.core.util.network.doIfError
import com.karim.core.util.network.doIfSuccess
import com.karim.domain.usecase.GetLocalMedicinesUseCase
import com.karim.domain.usecase.GetRemoteMedicinesUseCase
import com.karim.domain.usecase.GreetUserByTimeUseCase
import com.karim.domain.usecase.StoreToRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GreetingViewModel @Inject constructor(
    private val getRemoteMedicinesUseCase: GetRemoteMedicinesUseCase,
    private val greetUserByTimeUseCase: GreetUserByTimeUseCase,
    private val storeToRoomUSeCase: StoreToRoomUseCase,
    private val getLocalMedicinesUseCase: GetLocalMedicinesUseCase
) : ViewModel() {
    val greeting get() = greetUserByTimeUseCase()
    private val _medicines = flow {
        getLocalMedicinesUseCase().collect { result ->
            result.doIfSuccess {
                emit(it)
                Log.d("GreetingViewModel", "getMedicines from local: $it")
            }
            result.doIfError {
                getRemoteMedicinesUseCase().collect { result ->
                    result.doIfSuccess {
                        storeToRoomUSeCase(it)
                        emit(it)
                        Log.d("GreetingViewModel", "getMedicines from remote: $it")
                    }
                    result.doIfError {
                        ErrorManager.setError(it.message)
                    }
                }
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)
    val medicines get() = _medicines

}