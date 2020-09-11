package com.vesencom.gadsphaseii.network.networking

import timber.log.Timber
import javax.inject.Inject
import com.vesencom.gadsphaseii.models.Resource

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService): RemoteDataSource {
//    override suspend fun getTopLearningLeaders(): Resource<List<LearningLeader>> =
//        try {
//            val result = apiService.getTopLearningLeaders()
//            Resource.Success(result)
//        }
//        catch (error: Throwable){
//            Timber.d(error.message.toString())
//            Resource.Error("Server error encountered!")
//        }
//
//    override suspend fun getTopIQLeaders(): Resource<List<SkillIQLeader>> =
//        try {
//            val result = apiService.getTopIQLeaders()
//            Resource.Success(result.body()!!)
//        }
//        catch (error: Throwable){
//            Timber.d(error.message.toString())
//            Resource.Error("Server error encountered!")
//        }

    override suspend fun submitProject(
        firstName: String,
        lastName: String,
        emailAddress: String,
        projectLink: String
    ): Resource<Int> =
        try {
            val result = apiService.submitProject(
                firstName = firstName, lastName = lastName,
                emailAddress = emailAddress, projectLink = projectLink
            ).code()
            Timber.d(result.toString())
            if (result == 200){
                Resource.Success(result)
            }
            else {
                Timber.d(result.toString())
                Resource.Error("Submission not successful", null)
            }
        }
        catch (error: Throwable){
            Resource.Error(error.message.toString(), null)
        }
}