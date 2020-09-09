package com.vesencom.gadsphaseii.network.state

import com.vesencom.gadsphaseii.models.Skill

sealed class NetworkStateIQ {
    data class Success(val data: List<Skill>) :
        NetworkStateIQ()
    object  InvalidData: NetworkStateIQ()

    data class Error(val error: String) : NetworkStateIQ()
    data class NetworkException(val error: String) : NetworkStateIQ()


    sealed class HttpErrors : NetworkStateIQ(){
        data class ResourceForbidden(val exception: String) : HttpErrors()
        data class ResourceNotFound(val exception: String) : HttpErrors()
        data class InternalServerError(val exception: String) : HttpErrors()
        data class BadGateWay(val exception: String) : HttpErrors()
        data class ResourceRemoved(val exception: String) : HttpErrors()
        data class RemovedResourceFound(val exception: String) : HttpErrors()
    }
}