import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.example.googleauth.data.SignInResult
import com.example.mychat.R

class GoogleSignInManager(private val activity: Activity) {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    // Configure Google Sign-In options
    private val googleSignInClient: GoogleSignInClient by lazy {
        GoogleSignIn.getClient(
            activity,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.requestIdToken))
                .requestEmail()
                .build()
        )
    }

    // Launch Google Sign-In Intent
    fun getSignInIntent(): Intent = googleSignInClient.signInIntent

    // Handle sign-in result
    fun handleSignInResult(
        data: Intent?,
        onResult: (SignInResult) -> Unit
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.let { firebaseAuthWithGoogle(it, onResult) }
        } catch (e: ApiException) {
            onResult(SignInResult(success = false, errorMessage = e.localizedMessage ?: "Sign-in failed"))
        }
    }

    // Firebase authentication using Google account
    private fun firebaseAuthWithGoogle(
        account: GoogleSignInAccount,
        onResult: (SignInResult) -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(SignInResult(success = true))
            } else {
                onResult(SignInResult(success = false, errorMessage = task.exception?.localizedMessage ?: "Authentication failed"))
            }
        }
    }
}

