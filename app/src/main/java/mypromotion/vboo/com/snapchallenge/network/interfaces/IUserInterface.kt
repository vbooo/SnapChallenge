package mypromotion.vboo.com.snapchallenge.network.interfaces

import mypromotion.vboo.com.snapchallenge.model.network.*

interface IUserInterface {
    fun login(userLoginData: UserLoginData, bodyResultListener: IServiceResultListener<LoginBodyResult>)
    fun add(addUserData: AddUserData, bodyResultListener: IServiceResultListener<LoginBodyResult>)
    fun findById(userId: String, token: String, bodyResultListener: IServiceResultListener<FindUserByIdResult>)
    fun getAllUsers(token: String, bodyResultListener: IServiceResultListener<AllUsersResult>)
}