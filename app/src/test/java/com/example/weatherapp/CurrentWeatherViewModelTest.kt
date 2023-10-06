package com.example.weatherapp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.weatherapp.model.data.dto.WeatherDataResponse
import com.example.weatherapp.model.repo.OpenWeatherRepo
import com.example.weatherapp.viewmodel.CurrentWeatherViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import java.math.BigDecimal

class CurrentWeatherViewModelTest {
    private lateinit var openWeatherRepoMock: OpenWeatherRepo

    private lateinit var dataStoreMock: DataStore<Preferences>

    @Test
    fun `test get current weather api called`() {
        // GIVEN
        val sut = createCurrentWeatherViewModel(willCallSucceed = true)
        val latitude = BigDecimal.ZERO
        val longitude = BigDecimal.ZERO

        // WHEN
        sut.getCurrentWeather(latitude, longitude)

        // THEN
        coVerify (exactly = 1) { openWeatherRepoMock.getCurrentWeatherData(latitude, longitude) }
    }

    private fun createCurrentWeatherViewModel(
        willCallSucceed: Boolean
    ): CurrentWeatherViewModel {
        val weatherDataResponseMock = mockk<WeatherDataResponse>(relaxed = true) {
            every { cityName } returns "City"
            every { mainData.temp } returns BigDecimal.ZERO
            every { mainData.feelsLike } returns BigDecimal.ZERO
        }
        openWeatherRepoMock = mockk<OpenWeatherRepo>(relaxed = true) {
            if (willCallSucceed) {
                coEvery {
                    getCurrentWeatherData(any(), any())
                } returns Result.success(weatherDataResponseMock)
            } else {
                coEvery {
                    getCurrentWeatherData(any(), any())
                } returns Result.failure(mockk<Throwable>())
            }
        }
        dataStoreMock = mockk<DataStore<Preferences>>()

        return CurrentWeatherViewModel(openWeatherRepoMock, dataStoreMock)
    }
}