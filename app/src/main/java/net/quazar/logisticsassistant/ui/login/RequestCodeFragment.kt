package net.quazar.logisticsassistant.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.redmadrobot.inputmask.MaskedTextChangedListener
import net.quazar.logisticsassistant.MainActivity
import net.quazar.logisticsassistant.R

class RequestCodeFragment(val phoneNumber: String) : Fragment(R.layout.fragment_request_code) {

    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_request_code, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val loginButton: Button = view.findViewById(R.id.loginButton)
        val sentCodeText: TextView = view.findViewById(R.id.sentCodeText)
        val codeField: EditText = view.findViewById(R.id.codeInput)
        val resendCodeTimerText: TextView = view.findViewById(R.id.resendCodeTimer)
        val resendCode: TextView = view.findViewById(R.id.codeResend)

        codeField.requestFocus()
        sentCodeText.text = sentCodeText.text.toString().format(phoneNumber)

        view.findViewById<ImageView>(R.id.codeRequestBackButton).setOnClickListener { _ ->
            timer?.cancel()
            parentFragmentManager.beginTransaction()
                .detach(this)
                .replace(R.id.loginFragmentContainer, LoginFragment.newInstance(phoneNumber))
                .commit()
        }
        MaskedTextChangedListener.installOn(
            codeField,
            "[000000]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String,
                    tailPlaceholder: String
                ) {
                    loginButton.isEnabled = maskFilled
                }
            }
        )

        val resend = resources.getString(R.string.resend_code_time);
        timer = object: CountDownTimer(1000 * 60, 1000) {
            override fun onTick(millsFinished: Long) {
                val minutes = millsFinished / 1000 / 60
                val seconds = (millsFinished / 1000) % 60
                resendCodeTimerText.text = resend.format(minutes, seconds)
            }

            override fun onFinish() {
                resendCodeTimerText.visibility = View.GONE
                resendCode.visibility = View.VISIBLE
            }
        }
        startTimer()

        resendCode.setOnClickListener { _ ->
            MainActivity.getLoginService().sendLoginCode(phoneNumber)
            resendCode.visibility = View.GONE
            resendCodeTimerText.visibility = View.VISIBLE
            startTimer()
        }

        loginButton.setOnClickListener { _ ->
            MainActivity.getLoginService().login(phoneNumber, codeField.text.toString()) { result ->
                if (result is Error) return@login
                activity?.finish()
            }
        }
    }

    private fun startTimer() {
        timer?.start()
    }

    fun cancelTimer() {
        timer?.cancel()
    }
}