package com.example.todo.ui.fragment.board


import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.databinding.FragmentOnBoardBinding
import com.example.todo.ui.App
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate),
    OnboardListener{

    private lateinit var  adapter : BoardAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun setupUI() {
        adapter = BoardAdapter(this)
        binding.boardPager.adapter = adapter

        initSignInClient()

        TabLayoutMediator(binding.tabLayout, binding.boardPager) {tab, _ ->
            tab.setIcon(R.drawable.tab_selector)
        }.attach()

    }

    private fun initSignInClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = Firebase.auth
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account.idToken?.let { firebaseAuthWithGoogle(it) }
            } catch (e : ApiException) {
                e.localizedMessage?.let { Log.e(TAG, it) }
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    controller.navigateUp()
                } else {
                    Toast.makeText(requireContext(), task.exception.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun signIn() {
        val intent = googleSignInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun onBoardClick() {
        signIn()
        App.prefs.saveBoardState()
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }


}