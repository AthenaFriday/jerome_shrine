package com.google.codelabs.mdc.kotlin.shrine

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private lateinit var passwordEditText: TextInputEditText
    private lateinit var passwordTextInput: TextInputLayout
    private lateinit var nextButton: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.shr_login_fragment, container, false)

        // Initialize the views
        passwordEditText = view.findViewById(R.id.password_edit_text)
        passwordTextInput = view.findViewById(R.id.password_text_input)
        nextButton = view.findViewById(R.id.next_button)

        // Set an error if the password is less than 8 characters
        nextButton.setOnClickListener {
            if (!isPasswordValid(passwordEditText.text)) {
                passwordTextInput.error = getString(R.string.shr_error_password)
            } else {
                // Clear the error
                passwordTextInput.error = null
                // Navigate to the next Fragment
                (activity as? NavigationHost)?.navigateTo(ProductGridFragment(), false)
            }
        }

        // Clear the error once more than 8 characters are typed
        passwordEditText.setOnKeyListener { _, _, _ ->
            if (isPasswordValid(passwordEditText.text)) {
                // Clear the error
                passwordTextInput.error = null
            }
            false
        }

        return view
    }

    // Function to validate password length
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }
}
