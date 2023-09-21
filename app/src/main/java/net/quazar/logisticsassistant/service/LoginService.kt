package net.quazar.logisticsassistant.service

import net.quazar.logisticsassistant.data.Result
import net.quazar.logisticsassistant.data.model.LoggedInUser
import java.util.function.Consumer

interface LoginService {
    fun sendLoginCode(phoneNumber: String)

    fun login(phoneNumber: String, code: String, result: Consumer<Result<LoggedInUser>>)

    fun isLoggedIn() : Boolean

    fun logout()
}