package aichner.benjamin.composedemo

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    var imageVectors = mutableStateListOf<IdImageVector>()

    fun addImage(imageVector: ImageVector) {
        Log.d("ReportVM", "addImage()")
        val id = System.currentTimeMillis()
        imageVectors.add(IdImageVector(id, imageVector))
    }

    fun removeImage(idImageVector: IdImageVector) {
        Log.d("ReportVM", "removeImage()")
        imageVectors.remove(idImageVector)
    }
}

data class IdImageVector(
    val id: Long,
    val imageVector: ImageVector
)