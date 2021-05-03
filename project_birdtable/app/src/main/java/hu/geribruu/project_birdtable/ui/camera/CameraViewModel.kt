package hu.geribruu.project_birdtable.ui.camera

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.geribruu.project_birdtable.camera.analyzer.ImageAnalyzer
import hu.geribruu.project_birdtable.database.model.Bird
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val imageAnalyzer: ImageAnalyzer) : ViewModel(), LifecycleObserver {

    private var _birdNameLiveData =  MutableLiveData<String>().apply {
        value = imageAnalyzer.birdName
    }
    var birdNameLiveData: LiveData<String> = _birdNameLiveData
}