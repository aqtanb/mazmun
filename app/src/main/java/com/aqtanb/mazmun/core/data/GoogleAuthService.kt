package com.aqtanb.mazmun.core.data

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.aqtanb.mazmun.R
import com.aqtanb.mazmun.core.domain.error.AppError
import com.aqtanb.mazmun.core.domain.model.AuthResult
import com.aqtanb.mazmun.core.domain.model.UserData
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await

class GoogleAuthService(
    private val context: Context
) {
    private val auth = Firebase.auth
    private val credentialManager = CredentialManager.Companion.create(context)
    private val serverClientId = context.getString(R.string.web_client_id)

    suspend fun signIn(): AuthResult {
        return try {
            val option = GetGoogleIdOption.Builder()
                .setServerClientId(serverClientId)
                .setFilterByAuthorizedAccounts(false)
                .build()
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(option)
                .build()
            val response = credentialManager.getCredential(context, request)
            val cred = response.credential
            if (cred is CustomCredential && cred.type == GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdToken = GoogleIdTokenCredential.Companion.createFrom(cred.data).idToken

                val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
                val authResult = auth.signInWithCredential(firebaseCredential).await()
                val firebaseUser = authResult.user

                firebaseUser?.let { user ->
                    AuthResult.Success(createUserData(user))
                } ?: AuthResult.Failure(AppError.AuthError.UnknownError("Null user after authentication"))
            } else {
                AuthResult.Failure(AppError.AuthError.InvalidCredentials)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            AuthResult.Failure(AppError.AuthError.UnknownError(e.message ?: "Unknown error"))
        }
    }

    suspend fun signOut() {
        try {
            val clearRequest = ClearCredentialStateRequest()
            credentialManager.clearCredentialState(clearRequest)
            auth.signOut()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.let { createUserData(it) }

    private fun createUserData(user: FirebaseUser): UserData {
        return UserData(
            userId = user.uid,
            username = user.displayName,
            profilePictureUrl = user.photoUrl?.toString(),
        )
    }
}
