package com.hdd.pakwan.repository

import com.hdd.pakwan.data.models.User
import com.hdd.pakwan.data.remoteDataSource.HttpRequestNetworkCall
import com.hdd.pakwan.data.remoteDataSource.ServiceBuilder
import com.hdd.pakwan.data.remoteDataSource.response.ImageResponse
import com.hdd.pakwan.data.remoteDataSource.response.UserResponse
import com.hdd.pakwan.data.remoteDataSource.services.UserServices
import okhttp3.MultipartBody


class UserRepository : HttpRequestNetworkCall() {
    private val userService = ServiceBuilder.buildService(UserServices::class.java)

    suspend fun registerUser(user: User): UserResponse {
        return myHttpRequestNetworkCall {
            userService.registerUser(user)
        }
    }

    suspend fun loginUser(user: User): UserResponse {
        return myHttpRequestNetworkCall {
            userService.login(user)
        }
    }
    suspend fun getUserProfile(): UserResponse {
        return myHttpRequestNetworkCall {
            userService.getUserProfile(ServiceBuilder.token!!)
        }
    }
    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return myHttpRequestNetworkCall {
            userService.uploadImage(ServiceBuilder.token!!, id, body)
        }
    }

    suspend fun updateUserProfile(id: String,user: User)
            : UserResponse {
        return myHttpRequestNetworkCall {
            userService.updateUserProfile(ServiceBuilder.token!!, id,user)
        }
    }

    suspend fun changePassword(id: String,user: User)
            : UserResponse {
        return myHttpRequestNetworkCall {
            userService.changePassword(ServiceBuilder.token!!,id,user)
        }
    }

}