package com.vesencom.gadsphaseii.network

import com.vesencom.gadsphaseii.models.Learner
import com.vesencom.gadsphaseii.models.Skill
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/api/hours")
    suspend fun getHours() : Response<List<Learner>>

    @GET("/api/skilliq")
    suspend fun getSkillIq() : Response<List<Skill>>
}