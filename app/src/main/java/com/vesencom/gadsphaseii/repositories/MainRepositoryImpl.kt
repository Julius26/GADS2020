package com.vesencom.gadsphaseii.repositories

import com.vesencom.gadsphaseii.models.Resource
import com.vesencom.gadsphaseii.network.networking.RemoteDataSource
import javax.inject.Inject

class MainRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDataSource): MainRepository {

    override suspend fun submitProject(
        firstName: String, lastName: String, email: String, projectLink: String
    ): Resource<Int> =
        remoteDataSource.submitProject(firstName, lastName, email, projectLink)
}