package com.electrolobzik.starwarsplanetsviewer.presentation.planet_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.electrolobzik.starwarsplanetsviewer.BaseComposeTest
import com.electrolobzik.starwarsplanetsviewer.data.source.remote.ApiService
import com.electrolobzik.starwarsplanetsviewer.data.source.remote.model.RemotePlanetListPage
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import java.io.IOException
import javax.inject.Inject

@HiltAndroidTest
class PlanetListErrorTest : BaseComposeTest() {

    @Inject
    lateinit var apiService: ApiService

    @Test
    fun whenNoConnection_thenShowsNoConnectionError() {
        // Override API service to throw IOException
        (apiService as TestApiService).shouldThrowNetworkError = true

        // Wait for the error to be displayed
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onNodeWithText("No connection")
                .isDisplayed()
        }

        // Verify error message is displayed
        composeTestRule
            .onNodeWithText("No connection")
            .assertIsDisplayed()
    }
}

class TestApiService : ApiService {
    var shouldThrowNetworkError = false

    override suspend fun getPlanetsPage(pageNumber: Int?): RemotePlanetListPage {
        if (shouldThrowNetworkError) {
            throw IOException("No network")
        }
        throw IllegalStateException("Not implemented")
    }
} 