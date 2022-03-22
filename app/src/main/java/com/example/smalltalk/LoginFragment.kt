package com.example.smalltalk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.smalltalk.database.User
import com.example.smalltalk.viewmodels.LoginViewModel


class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    lateinit var loginButton: AppCompatButton
    lateinit var loginUsername: EditText
    lateinit var loginPassword: EditText

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

        loginButton.setOnClickListener {
            if (viewModel.checkLogin(
                    loginUsername.text.toString(),
                    loginPassword.text.toString()
                )
            ) {
                val user = User(
                    username = "Korsfareren",
                    fullName = "Thomas Korsnes"
                )
                switchFragment(user)
            } else {
                Toast.makeText(context, "Wrong username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun switchFragment(user: User) {
        viewModel.saveUserToDatabase(user) {
            //Uten safe args
            //findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())

            /*
            requireActivity().supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MainFragment>(R.id.fragment_container)
                addToBackStack(null)
            }


 */
        }
    }
}
