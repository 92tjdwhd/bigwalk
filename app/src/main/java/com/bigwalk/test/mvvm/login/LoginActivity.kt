package com.bigwalk.test.mvvm.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bigwalk.test.R
import com.bigwalk.test.databinding.ActivityLoginBinding
import com.bigwalk.test.mvvm.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LoginActivity"
        private const val RC_SIGN_IN = 9001
    }

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@LoginActivity
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = Firebase.auth
        initView()
        initEvent()
    }

    fun initView() {

    }

    fun initEvent() {
        with(viewModel) {
            requestLogin.observe(this@LoginActivity, Observer {
                it.getContentIfNotHandled().let {
                    val signInIntent = googleSignInClient.signInIntent
                    startActivityForResult(signInIntent, RC_SIGN_IN)
                }
            })

            moveToMainEvent.observe(this@LoginActivity, Observer {
                it.getContentIfNotHandled().let {
                    MainActivity.startActivity(this@LoginActivity)
                    finish()
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                MainActivity.startActivity(this@LoginActivity)
                finish()

            } catch (e: ApiException) {
                Toast.makeText(applicationContext,"Google sign in failed",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Firebase.auth.signOut()
        googleSignInClient.signOut()
    }
}