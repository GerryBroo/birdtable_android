package hu.geribruu.project_birdtable.ui.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.geribruu.project_birdtable.database.model.Bird
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val repository: BirdRepositoryImpl) : ViewModel() {

    fun insert(bird : Bird) = viewModelScope.launch {
        repository.insert(bird)
    }
}