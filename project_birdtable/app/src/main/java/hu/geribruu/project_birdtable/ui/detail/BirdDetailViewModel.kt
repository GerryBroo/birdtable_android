package hu.geribruu.project_birdtable.ui.detail

import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.geribruu.project_birdtable.database.model.Bird
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class BirdDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BirdRepositoryImpl
    ): ViewModel(), LifecycleObserver {

    companion object {
        const val BIRD_ID = "BIRD_ID"
    }

    val birdLiveData: LiveData<Bird> = loadBird()
        get() = field

    private fun loadBird() : LiveData<Bird> {
        val birdId = savedStateHandle.get<Long>(BIRD_ID)
        return repository.getBird(birdId!!.toLong()).asLiveData()
    }
}