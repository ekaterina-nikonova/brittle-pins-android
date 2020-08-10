package com.brittlepins.brittlepins

import com.brittlepins.brittlepins.db.USER_ROLES
import com.brittlepins.brittlepins.db.User
import java.util.*

object DBTestUtils {
    val userString = """{"id": "${UUID.randomUUID()}", "username":"john-doe", "email":"john@doe.com", "role": ""}"""

    fun createUser() = User(
        id = UUID.randomUUID().toString(),
        email = "john@doe.com",
        username = "john-doe",
        role = USER_ROLES.user.toString(),
        token = "",
        csrf = ""
    )
}
