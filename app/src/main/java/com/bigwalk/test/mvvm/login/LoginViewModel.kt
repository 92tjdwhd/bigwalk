package com.bigwalk.test.mvvm.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bigwalk.test.R
import com.bigwalk.test.util.Event

class LoginViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    init {
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()
    }

    val moveToMainEvent:MutableLiveData<Event<Unit>> = MutableLiveData()

    val requestLogin:MutableLiveData<Event<Unit>> = MutableLiveData()

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(LoginActivity.TAG, "signInWithCredential:success")
                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(LoginActivity.TAG, "signInWithCredential:failure", task.exception)

                }
            }
    }

    fun loginEvent(){
        requestLogin.value = Event(Unit)
    }

    fun loginSuccessEvent(){
        moveToMainEvent.value = Event(Unit)
    }
}