package hu.geribruu.project_birdtable.ui.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.geribruu.project_birdtable.database.model.Bird
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import hu.geribruu.project_birdtable.repository.IBirdRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: BirdRepositoryImpl) : ViewModel() {

   val birds : LiveData<List<Bird>> = repository.birds.asLiveData()
}

class GalleryViewModelFactory(private val repositoryImpl: BirdRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GalleryViewModel(repositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}