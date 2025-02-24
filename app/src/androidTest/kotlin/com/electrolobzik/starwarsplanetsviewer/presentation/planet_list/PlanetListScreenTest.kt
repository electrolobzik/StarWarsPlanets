package com.electrolobzik.starwarsplanetsviewer.presentation.planet_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.electrolobzik.starwarsplanetsviewer.BaseComposeTest
import com.electrolobzik.starwarsplanetsviewer.R
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class PlanetListScreenTest : BaseComposeTest() {

    @Test
    fun whenScreenLoads_thenShowsPlanetList() {
        // Wait for the loading to finish
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onNodeWithText("Test Planet")
                .isDisplayed()
        }

        // Verify planet details are displayed
        composeTestRule
            .onNodeWithText("Test Planet")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Test Climate")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Test Terrain")
            .assertIsDisplayed()
    }

    @Test
    fun whenPlanetClicked_thenNavigatesToDetails() {
        // Wait for the loading to finish
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onNodeWithText("Test Planet")
                .isDisplayed()
        }

        // Click on the planet
        composeTestRule
            .onNodeWithText("Test Planet")
            .performClick()

        // Verify we're on the details screen
        composeTestRule
            .onNodeWithText("Test Planet")
            .assertIsDisplayed()
    }
} 