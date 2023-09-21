package net.quazar.logisticsassistant.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.redmadrobot.inputmask.MaskedTextChangedListener
import net.quazar.logisticsassistant.MainActivity
import net.quazar.logisticsassistant.R

private const val PHONE_PARAM = "phoneNumber"

class LoginFragment : Fragment(R.layout.fragment_login) {
    // TODO: Rename and change types of parameters
    private var phoneNumber: String? = null
    private var extractedPhoneValue: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            phoneNumber = it.getString(PHONE_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val button: Button = view.findViewById(R.id.loginButton)
        val loginField: EditText = view.findViewById(R.id.loginPhoneNumber)
        if (phoneNumber != null)
            loginField.setText(phoneNumber)
        loginField.requestFocus()
        val listener: MaskedTextChangedListener = MaskedTextChangedListener.installOn(
            loginField,
            "+7 ([000]) [000]-[00]-[00]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String,
                    tailPlaceholder: String
                ) {
                    button.isEnabled = maskFilled
                    extractedPhoneValue = extractedValue
                }
            }
        )

        loginField.hint = listener.placeholder()

        button.setOnClickListener { _ ->
            MainActivity.getLoginService().sendLoginCode(extractedPhoneValue!!)
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.loginFragmentContainer,
                    RequestCodeFragment(loginField.text.toString())
                )
                .commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(phoneNumber: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(PHONE_PARAM, phoneNumber)
                }
            }
    }
}