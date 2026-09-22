package com.oso.myfirebase.auth.data

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    val authState : Flow<FirebaseUser?>

    suspend fun signIn(email:String, password: String) : Result<Unit>

    suspend fun register(email:String, password: String) : Result<Unit>

    fun signOut()

    fun currentUser(): FirebaseUser?

}