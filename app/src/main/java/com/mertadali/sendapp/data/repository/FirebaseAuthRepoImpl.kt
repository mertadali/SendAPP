package com.mertadali.sendapp.data.repository

import android.content.SharedPreferences
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.mertadali.sendapp.domain.repository.FirebaseAuthRepository
import com.mertadali.sendapp.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepoImpl @Inject constructor(private val auth: FirebaseAuth, private val sharedPreferences: SharedPreferences
) : FirebaseAuthRepository {

    // SharedPreferences için gerekli sabitler

    private val PREF_NAME = "login_preferences"
    private val KEY_CHECKBOX = "checkbox_state"

    // SharedPreferences instance'ı
    private val LOGGED_IN_KEY = "logged_in"

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Flow<Response<AuthResult>> = flow {
        try {
            emit(Response.Loading)
            val result = auth.signInWithEmailAndPassword(email, password).await()
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "An error occurred during sign in"))
        }
    }

    override suspend fun signUpWithEmailAndPassword(email: String, password: String): Flow<Response<AuthResult>> = flow {
        try {
            emit(Response.Loading)
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "An error occurred during sign up"))
        }
    }

    override suspend fun signOut() {
        auth.signOut()
        setLoggedIn(false)
    }

    override suspend fun getSignedInUser(): FirebaseUser? = auth.currentUser


    override suspend fun sendPasswordResetEmail(email: String): Flow<Response<Unit>> = flow {
        try {
            emit(Response.Loading)
            auth.sendPasswordResetEmail(email).await()
            emit(Response.Success(Unit))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "An error occurred while sending reset email"))
        }
    }



    override suspend fun signInWithGoogle(token: String): Flow<Response<AuthResult>> = flow{
        try {
            emit(Response.Loading)
            val credential = GoogleAuthProvider.getCredential(token, null)
            val result = auth.signInWithCredential(credential).await()
            result.user?.let {
                if (!it.isEmailVerified) {
                    it.sendEmailVerification().await()
                }
            }
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Google sign in failed!"))
        }
    }

    override suspend fun sendEmailVerification(): Flow<Response<Unit>> = flow {
        try {
            emit(Response.Loading)
            auth.currentUser?.let {
                if (!it.isEmailVerified) {
                    it.sendEmailVerification().await()
                    emit(Response.Success(Unit))
                } else {
                    emit(Response.Error("Email is already verified"))
                }
            } ?: emit(Response.Error("No user is signed in"))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Failed to send verification email"))
        }
    }

    override suspend fun getLoggedIn(): Boolean {
        return  sharedPreferences.getBoolean(LOGGED_IN_KEY, false)
    }

    override suspend fun setLoggedIn(loggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(LOGGED_IN_KEY, loggedIn).apply()
    }




    override suspend fun saveToFirebase() {
        // TODO: Implement delete from Firebase
    }

    override suspend fun deleteFromFirebase() {
        // TODO: Implement delete from Firebase

    }
}