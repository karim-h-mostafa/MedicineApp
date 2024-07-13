package com.karim.medicine.di.module

import android.util.Log.d
import com.karim.core.util.common.Constants
import com.karim.core.util.network.NetworkResponseAdapterFactory
import com.karim.data.repository.remote.MedicineAPI
import com.karim.medicine.BuildConfig


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun retrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL).client(client)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }



    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: okhttp3.Interceptor,
    ): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(headerInterceptor)
        if (BuildConfig.DEBUG) builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> d("OkHttp", message) }.apply {
            if (BuildConfig.DEBUG) setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        }
    }

    @Provides
    fun provideHeaderInterceptor(): okhttp3.Interceptor {
        return okhttp3.Interceptor { chain: okhttp3.Interceptor.Chain ->
            val timeoutSeconds = 10000
            val request: okhttp3.Request = chain.request()
            val requestBuilder: okhttp3.Request.Builder = request.newBuilder()
            requestBuilder.addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Timeout", timeoutSeconds.toString())
            chain.proceed(requestBuilder.build())
        }
    }


}