package com.example.smalltalk

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.smalltalk.database.AppDatabase
import com.example.smalltalk.database.User
import com.example.smalltalk.database.viewModels.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    lateinit var loginButton: AppCompatButton
    lateinit var loginUsername: EditText
    lateinit var loginPassword: EditText
    lateinit var loginStatus: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton = view.findViewById(R.id.login_button)
        loginUsername = view.findViewById(R.id.login_username)
        loginPassword = view.findViewById(R.id.login_password)
        loginStatus = requireActivity().getPreferences(Context.MODE_PRIVATE)

        login()
    }

    private fun login() {
        loginButton.setOnClickListener {
            if (loginUsername.text.toString() == "t" && loginPassword.text.toString() == "k") {
                loginStatus
                    .edit()
                    .putBoolean(loginKey, true)
                    .apply()

                val user = User(
                    username = "Korsfareren",
                    fullName = "Thomas Korsnes"
                )

                val database = AppDatabase.getInstance(requireContext())
                val userDao = database.userDao()

                CoroutineScope(Dispatchers.IO).launch {
                    userDao.removeAllUsers()
                    userDao.saveUser(user)

                    requireActivity().supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        add<MainFragment>(R.id.fragment_container)
                        addToBackStack(null)
                    }
                }
            } else {
                Toast.makeText(activity, "Feil brukernavn eller passord", Toast.LENGTH_SHORT).show()
            }
        }
    }
}