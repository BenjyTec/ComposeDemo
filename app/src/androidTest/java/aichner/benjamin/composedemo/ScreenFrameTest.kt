package aichner.benjamin.composedemo

import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ScreenFrameTest {

    @get:Rule
    val testRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun clickRemoveImageButton_removesImage() {
        val imageContDesc = "IMAGE 0"
        val removeImageContDesc = "CLOSE"
        val mainViewModel = MainViewModel()
        mainViewModel.addImage(Icons.Default.Warning)

        testRule.activity.setContent {
            ScreenFrame(
                viewModel = mainViewModel
            )
        }
        var foundNodes = -1
        foundNodes = testRule.onAllNodes(hasContentDescription(imageContDesc)).fetchSemanticsNodes().size
        Log.d("ScreenFrameTest", "A: ViewModel contains ${mainViewModel.imageVectors.size} imageVectors")
        Log.d("ScreenFrameTest", "A: Found $foundNodes nodes with contentDescription $imageContDesc")

        testRule.onNodeWithContentDescription(removeImageContDesc).performClick()

        foundNodes = testRule.onAllNodes(hasContentDescription(imageContDesc)).fetchSemanticsNodes().size
        Log.d("ScreenFrameTest", "B: ViewModel contains ${mainViewModel.imageVectors.size} imageVectors")
        Log.d("ScreenFrameTest", "B: Found $foundNodes nodes with contentDescription $imageContDesc")

        testRule.onNodeWithContentDescription(imageContDesc).assertDoesNotExist()
        testRule.onNodeWithContentDescription(removeImageContDesc).assertDoesNotExist()
    }

}