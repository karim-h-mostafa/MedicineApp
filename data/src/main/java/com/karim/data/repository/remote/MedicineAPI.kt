package com.karim.data.repository.remote

import com.karim.core.util.network.NetworkResponse
import com.karim.data.model.ErrorModel
import com.karim.data.model.MedicinesModel
import retrofit2.http.GET

interface MedicineAPI {
    @GET("41f6e308-d148-4fc3-9123-798211ac5643")
    suspend fun getMedicine():NetworkResponse<MedicinesModel, ErrorModel>
}
