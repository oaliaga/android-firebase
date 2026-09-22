package com.oso.myfirebase.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.oso.myfirebase.auth.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(repository: AuthRepository) : ViewModel() {

    val user: StateFlow<FirebaseUser?> = repository.authState.stateIn(
        viewModelScope, SharingStarted.Eagerly, null
    )

}