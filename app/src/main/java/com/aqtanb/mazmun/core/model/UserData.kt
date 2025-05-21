package com.aqtanb.mazmun.core.model

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)

val EmptyUserData = UserData("", null, null)
