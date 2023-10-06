package com.example.weatherapp

import com.example.weatherapp.api.OpenWeatherApiConstants
import com.example.weatherapp.api.OpenWeatherRepoImpl
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.math.BigDecimal

class OpenWeatherRepoImplTest {
    @Test
    fun `test open weather base URL correct`() {
        // GIVEN
        val sut = OpenWeatherRepoImpl()

        // THEN
        assert(sut.retrofit.baseUrl().url().toString() == OpenWeatherApiConstants.BASE_URL)
    }

    @Test
    fun `test getCurrentWeatherData API success`() {
        // GIVEN
        val sut = OpenWeatherRepoImpl()

        runTest {
            // WHEN
            val response = sut.getCurrentWeatherData(
                latitude = BigDecimal("37.7790262"),
                longitude = BigDecimal("-122.419906")
            )

            // THEN
            assert(response.isSuccess)
        }
    }

    @Test
    fun `test getCurrentWeatherData API failure invalid coordinates`() {
        // GIVEN
        val sut = OpenWeatherRepoImpl()

        runTest {
            // WHEN
            val response = sut.getCurrentWeatherData(
                latitude = BigDecimal("999999999"),
                longitude = BigDecimal("99999999")
            )

            // THEN
            assert(response.isFailure)
        }
    }

    @Test
    fun `test getCoordinates API success`() {
        // GIVEN
        val sut = OpenWeatherRepoImpl()

        runTest {
            // WHEN
            val response = sut.getCoordinates(
                cityName = "San Francisco"
            )

            // THEN
            assert(response.isSuccess)
        }
    }

    @Test
    fun `test getCoordinates API failure invalid city`() {
        // GIVEN
        val sut = OpenWeatherRepoImpl()

        runTest {
            // WHEN
            val response = sut.getCoordinates(
                cityName = "33333431"
            )

            // THEN
            assert(response.isSuccess)
        }
    }
}