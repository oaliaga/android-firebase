package com.oso.myfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.oso.myfirebase.auth.AuthViewModel
import com.oso.myfirebase.navigation.AppNavHost
import com.oso.myfirebase.ui.theme.MyFirebaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFirebaseTheme {
                val authVM: AuthViewModel= hiltViewModel()
                val user by authVM.user.collectAsState()
                AppNavHost(
                    //user!=null -> el usuario esta autenticado -> se abre pantalla "Home"
                    //user==null -> el usuario no esta autenticado -> se abre pantalla "Login"
                    startOnHome = user != null
                )
            }
        }
    }
}