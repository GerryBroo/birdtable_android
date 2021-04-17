package hu.geribruu.project_birdtable.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.geribruu.project_birdtable.database.model.Bird
import hu.geribruu.project_birdtable.repository.BirdRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(repository: BirdRepositoryImpl) : ViewModel() {

   val birds : LiveData<List<Bird>> = repository.birds.asLiveData()
}