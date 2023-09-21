package net.quazar.logisticsassistant.service

import net.quazar.logisticsassistant.data.Result
import net.quazar.logisticsassistant.data.model.LoggedInUser
import java.util.function.Consumer

class LoginServiceTestImpl : LoginService {
    var currentUser: LoggedInUser? = null
        private set

    override fun sendLoginCode(phoneNumber: String) {
        // TODO
    }

    override fun login(phoneNumber: String, code: String, result: Consumer<Result<LoggedInUser>>) {
        val res = Result.Success(LoggedInUser("1", "Максим"))
        result.accept(res)
        currentUser = res.data
    }

    override fun isLoggedIn(): Boolean {
        return currentUser != null
    }

    override fun logout() {
        currentUser = null
    }
}