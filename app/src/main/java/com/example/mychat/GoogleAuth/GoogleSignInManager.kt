import android.app.Activity
import android.content.Intent

import com.example.googleauth.data.SignInResult
import com.example.googleauth.data.UserData
import com.example.mychat.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


/**
 * //GoogleSignIn
 *     implementation ("com.google.android.gms:play-services-auth:20.5.0")
 */

class GoogleSignInManager(
    private val activity: Activity,
) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Configure Google Sign-In
    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(activity.getString(R.string.requestIdToken))  // Correct way to fetch string resource
        .requestEmail()
        .build()


    // GoogleSignInClient
    private val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(activity, gso)

    // Launch Google Sign-In
    fun signIn(): Intent {
        return googleSignInClient.signInIntent
    }

    // Handle the sign-in result
    fun handleSignInResult(
        data: Intent?,
        onResult: (SignInResult) -> Unit
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account, onResult)
        } catch (e: ApiException) {
            onResult(SignInResult(success = false, errorMessage = e.localizedMessage))
        }
    }

    // Firebase authentication with the Google account
    private fun firebaseAuthWithGoogle(
        account: GoogleSignInAccount,
        onResult: (SignInResult) -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                auth.currentUser?.let { firebaseUser ->
                    onResult(
                        SignInResult(
                            data = UserData(
                                userId = firebaseUser.uid,
                                displayName = firebaseUser.displayName,
                                userPic = firebaseUser.photoUrl?.toString(),
                                email = firebaseUser.email
                            ),
                            success = true
                        )
                    )
                }
            } else {
                onResult(SignInResult(success = false, errorMessage = task.exception.toString()))
            }
        }
    }
}
