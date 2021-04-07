package hu.geribruu.project_birdtable.ui.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.geribruu.project_birdtable.database.model.BirdDatabaseModel
import hu.geribruu.project_birdtable.database.BirdRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: BirdRepository) : ViewModel() {

    val birds : LiveData<List<BirdDatabaseModel>> = repository.birds.asLiveData()
}

class GalleryViewModelFactory(private val repository: BirdRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GalleryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}