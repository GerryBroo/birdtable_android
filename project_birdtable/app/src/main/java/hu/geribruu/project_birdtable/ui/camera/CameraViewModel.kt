package hu.geribruu.project_birdtable.ui.camera

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.geribruu.project_birdtable.camera.BirdInfoScreen
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(): ViewModel(), BirdInfoScreen, LifecycleObserver {

    private var _birdNameLiveData = MutableLiveData<String>()
    var birdNameLiveData: LiveData<String> = _birdNameLiveData

    override fun birdInfo(text: String) {
        _birdNameLiveData.value = text
    }
}