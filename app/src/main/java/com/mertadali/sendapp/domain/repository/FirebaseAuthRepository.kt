package com.mertadali.sendapp.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.mertadali.sendapp.util.Response
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    // Auth
    suspend fun signInWithEmailAndPassword(email: String, password: String): Flow<Response<AuthResult>>
    suspend fun signUpWithEmailAndPassword(email: String, password: String): Flow<Response<AuthResult>>
    suspend fun signOut()
    suspend fun getSignedInUser(): FirebaseUser?
    suspend fun sendPasswordResetEmail(email: String): Flow<Response<Unit>>
    suspend fun googleSignIn()
    
    // Firebase Storage
    suspend fun saveToFirebase()
    suspend fun deleteFromFirebase()
}