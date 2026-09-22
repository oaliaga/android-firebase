package com.oso.myfirebase.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oso.myfirebase.auth.data.AuthRepository
import com.oso.myfirebase.auth.presentation.login.LoginViewModel.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {

    private val _ui = MutableStateFlow(RegisterUiState())

    val ui: StateFlow<RegisterUiState> = _ui



    sealed interface RegisterEvent {
        data object Success : RegisterEvent
    }

    private val _event = MutableSharedFlow<RegisterEvent>(replay = 0)

    val event : SharedFlow<RegisterEvent> = _event.asSharedFlow()

    fun register(email: String, password: String) {

        viewModelScope.launch {
            _ui.update { current ->
                current.copy(
                    loading = true,
                    error = null
                )
            }
            val r = repo.register(email.toString(), password)

            if (r.isSuccess){
                _ui.update { current ->
                    current.copy(
                        loading = false,
                        error = null
                    )
                }
                _event.emit(RegisterEvent.Success)
            }else{
                _ui.update { current ->
                    current.copy(
                        loading = false,
                        error = r.exceptionOrNull()?.toReadable()
                    )
                }
            }

        }
    }

    private fun Throwable.toReadable(): String =
        (this.message ?: "Error inesperado. Intenta de nuevo")


}

data class RegisterUiState(
    val loading: Boolean = false,
    val error: String? = null
)