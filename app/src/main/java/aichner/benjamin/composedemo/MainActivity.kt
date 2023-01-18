package aichner.benjamin.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import aichner.benjamin.composedemo.ui.theme.ComposeDemoTheme
import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenFrame()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenFrame(
    viewModel: MainViewModel = viewModel()
) {

    LazyRow(
        modifier = Modifier
            .fillMaxHeight(0.45f)
            .fillMaxWidth(),
        contentPadding = PaddingValues(6.dp)
    ) {

        itemsIndexed(
            viewModel.imageVectors, 
            key = { _, item: IdImageVector -> item.id}
        ) { index: Int, idImageVector: IdImageVector ->
            Box(modifier = Modifier
                .padding(4.dp)
                .animateItemPlacement()
            ) {
                Icon(
                    modifier = Modifier.size(200.dp, 300.dp),
                    imageVector = idImageVector.imageVector,
                    contentDescription = "IMAGE $index"
                )
                IconButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = { viewModel.removeImage(idImageVector) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "CLOSE"
                    )
                }
            }

        }
        
        item(-1) {
            Text(
                modifier = Modifier.animateItemPlacement(),
                text = "ABCDEFGHI"
            )
        }

    }


}