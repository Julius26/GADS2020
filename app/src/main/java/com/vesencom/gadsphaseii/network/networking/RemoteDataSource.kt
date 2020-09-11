package com.vesencom.gadsphaseii.network.networking

import com.vesencom.gadsphaseii.models.Resource

/**
 * Holds decoupled logic for api calls
 */
interface RemoteDataSource {

    suspend fun submitProject(
        firstName: String,
        lastName: String,
        emailAddress: String,
        projectLink: String
    ): Resource<Int>
}