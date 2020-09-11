package com.vesencom.gadsphaseii.repositories

import com.vesencom.gadsphaseii.models.Resource

interface MainRepository {


    suspend fun submitProject(
        firstName: String, lastName: String, email: String, projectLink: String
    ): Resource<Int>
}