package com.imagery.earth.viewmodels

import androidx.lifecycle.*
import com.imagery.earth.data.Image
import com.imagery.earth.data.ImageRepository
import kotlinx.coroutines.launch

class ImageListViewModel(
    private val imageRepository: ImageRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        getImages()
    }

    val imageList: MutableLiveData<List<Image>> = MutableLiveData<List<Image>>()
    private val _loader = MutableLiveData<Boolean>(true)
    val loader: LiveData<Boolean> = _loader

    private fun getImages() = viewModelScope.launch {
        val response = imageRepository.getImages()
        imageList.value = response
        _loader.value = false
    }

}