package com.vesencom.gadsphaseii.common.utils


interface Validator {
    fun setCredentials(firstName: String, lastName: String, email: String, projectLink: String)
    fun isFirstNameValid(): Boolean
    fun isLastNameValid(): Boolean
    fun isEmailValid(): Boolean
    fun isProjectLinkValid(): Boolean
}