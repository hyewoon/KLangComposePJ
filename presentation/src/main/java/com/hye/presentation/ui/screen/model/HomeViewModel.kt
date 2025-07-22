package com.hye.presentation.ui.screen.model

import androidx.lifecycle.ViewModel
import com.hye.domain.usecase.LoadStudyWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadStudyWordUseCase: LoadStudyWordUseCase
) : ViewModel() {




}