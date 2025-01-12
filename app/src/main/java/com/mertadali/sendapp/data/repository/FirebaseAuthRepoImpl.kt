package com.mertadali.sendapp.data.repository

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

class FirebaseRepoImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAuthRepository {

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
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: "Google sign in failed!"))
        }

    }


    override suspend fun saveToFirebase() {
        // TODO: Implement save to Firebase
    }

    override suspend fun deleteFromFirebase() {
        // TODO: Implement delete from Firebase
    }
}