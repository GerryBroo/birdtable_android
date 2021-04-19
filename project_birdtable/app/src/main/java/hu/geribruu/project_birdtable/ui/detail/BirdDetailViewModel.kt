package hu.geribruu.project_birdtable.ui.detail

import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class BirdDetailViewModel @Inject constructor(
    private val repositoryImpl: BirdRepositoryImpl,
    private val savedStateHandle: SavedStateHandle
): ViewModel(), LifecycleObserver {

    companion object {
        const val BIRD_ID = "BIRD_ID"
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is detail Fragment"
    }
    val text : LiveData<String> = _text


    private val showTextLiveData = savedStateHandle.getLiveData<String>(BIRD_ID)
    val showTextDataNotifier: LiveData<String>
        get() = showTextLiveData

}