package com.example.weatherapp

import com.example.weatherapp.model.data.dto.CoordinatesResponse
import com.example.weatherapp.model.repo.OpenWeatherRepo
import com.example.weatherapp.viewmodel.SearchResultViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Test
import java.math.BigDecimal

class SearchResultViewModelTest {
    private lateinit var openWeatherRepoMock: OpenWeatherRepo

    @Test
    fun `test get coordinates api called`() {
        // GIVEN
        val sut = createSearchResultViewModel(willCallSucceed = true)

        // WHEN
        sut.searchForCity("San Francisco")

        // THEN
        coVerify (exactly = 1) { openWeatherRepoMock.getCoordinates("San Francisco") }
    }

    private fun createSearchResultViewModel(
        willCallSucceed: Boolean
    ): SearchResultViewModel {
        val coordinatesResponse = mockk<List<CoordinatesResponse>>(relaxed = true) {
            coEvery { first() } returns CoordinatesResponse(
                name = "Test",
                localNames = " ",
                latitude = BigDecimal.ZERO,
                longitude = BigDecimal.ZERO,
                state = "California",
                country = "US"
            )
        }
        openWeatherRepoMock = mockk<OpenWeatherRepo>(relaxed = true) {
            if (willCallSucceed) {
                coEvery {
                    getCoordinates(any())
                } returns Result.success(coordinatesResponse)
            } else {
                coEvery {
                    getCoordinates(any())
                } returns Result.failure(mockk<Throwable>())
            }
        }

        return SearchResultViewModel(openWeatherRepoMock)
    }
}