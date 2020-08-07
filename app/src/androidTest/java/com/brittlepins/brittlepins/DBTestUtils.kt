package com.brittlepins.brittlepins

import com.brittlepins.brittlepins.db.USER_ROLES
import com.brittlepins.brittlepins.db.User
import java.util.*

object DBTestUtils {
    fun createUser() = User(
        id = UUID.randomUUID().toString(),
        email = "john@doe.com",
        username = "john-doe",
        role = USER_ROLES.user.toString(),
        token = "",
        csrf = ""
    )
}
