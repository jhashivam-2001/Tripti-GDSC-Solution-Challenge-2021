package com.example.solutionchallenge

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInApi
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.json.JSONObject


const val  key="key"
const val RC_SIGN_IN=120
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val about: Button = findViewById(R.id.ABOUT)
        val contact: Button = findViewById(R.id.CONTACTUS)
        val donatem: Button = findViewById(R.id.DONATEMONEY)
        val donatef: Button = findViewById(R.id.DONATEFOOD)
        val log: Button = findViewById(R.id.LOG)

        val mAuth: FirebaseAuth
        mAuth = FirebaseAuth.getInstance()


        var user = mAuth.currentUser

        if (user != null) {
            log.text = "LOGOUT"
        } else {
            log.text = "LOGIN"
        }



        about.setOnClickListener {
            val intent = Intent(this, ABOUT::class.java)
            startActivity(intent)
        }
        contact.setOnClickListener {
            val intent = Intent(this, CONTACTUS::class.java)
            startActivity(intent)
        }



        log.setOnClickListener {

            if (mAuth.currentUser != null) {
                Firebase.auth.signOut()
               log.text="LOGIN"
                Toast.makeText(this,"Signed out",Toast.LENGTH_SHORT).show()

            } else {

                signIn()

            }
        }
        donatem.setOnClickListener {
            if (mAuth.currentUser != null) {
                Toast.makeText(this,"Google pay is not enabled in beta version",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login First", Toast.LENGTH_SHORT).show()
            }

        }





        donatef.setOnClickListener {

            Handler().postDelayed(Runnable { if (mAuth.currentUser != null) {
                val intent = Intent(this, REGISTER::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login First", Toast.LENGTH_SHORT).show()
            } }, 1000)


        }


    }


    fun signIn() {
        val googleSignInClient: GoogleSignInClient
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val Exception=task.exception
            if(task.isSuccessful){
                val log:Button=findViewById(R.id.LOG)
                log.text="LOGOUT"
                 Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show()
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("SignInActivity", "Google sign in failed", e)
            }}
            else
            {
                Log.w("SignInActivity", Exception.toString())
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val  mAuth: FirebaseAuth
        mAuth = FirebaseAuth.getInstance()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignInActivity", "signInWithCredential:success")
                    

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("SignInActivity", "signInWithCredential:failure", task.exception)

                }
            }
    }


}
