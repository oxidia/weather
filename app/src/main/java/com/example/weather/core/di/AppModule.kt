package com.example.weather.core.di

import android.app.Application
import androidx.room.Room
import com.example.weather.core.data.repository.CityRepositoryImpl
import com.example.weather.core.data_source.Database
import com.example.weather.core.domain.repository.CityRepository
import com.example.weather.feature_manage_cities.data.remote.google.GoogleApi
import com.example.weather.feature_weather.data.remote.WeatherApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): Database {
        val db = Room.databaseBuilder(
            context = application,
            klass = Database::class.java,
            name = Database.DATABASE_NAME
        ).build()

        return  db
    }

    @Provides
    @Singleton
    fun provideCityRepository(database: Database): CityRepository {
        return CityRepositoryImpl(
            cityDao = database.cityDao
        )
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGoogleApi(): GoogleApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl: HttpUrl = original.url()

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(
                        "key", "<google api key>"
                    )
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()

                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(application: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }
}
