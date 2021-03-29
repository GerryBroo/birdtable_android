package hu.geribruu.project_birdtable.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hu.geribruu.project_birdtable.database.model.BirdDatabaseModel
import hu.geribruu.project_birdtable.database.BirdRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CameraViewModel(private val repository: BirdRepository) : ViewModel() {

    fun insert(bird : BirdDatabaseModel) = viewModelScope.launch {
        repository.insert(bird)
    }
}

class CameraViewModelFactory(private val repository: BirdRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CameraViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CameraViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}