package com.oso.myfirebase.auth.presentation.home

import androidx.lifecycle.ViewModel
import com.oso.myfirebase.auth.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: AuthRepository) : ViewModel() {

    val email: String = repo.currentUser()?.email.orEmpty()

    val uid: String = repo.currentUser()?.uid.orEmpty()


    fun logout(){
        repo.signOut()
    }


}
