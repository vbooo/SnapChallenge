package mypromotion.vboo.com.snapchallenge.network.interfaces.user

import mypromotion.vboo.com.snapchallenge.model.network.*
import mypromotion.vboo.com.snapchallenge.model.network.user.AddUserParam
import mypromotion.vboo.com.snapchallenge.model.network.user.AllUsersResult
import mypromotion.vboo.com.snapchallenge.model.network.user.FindUserByIdResult
import mypromotion.vboo.com.snapchallenge.model.network.user.UserLoginParam
import mypromotion.vboo.com.snapchallenge.network.interfaces.IServiceResultListener

interface IUserInterface {
    fun login(userLoginParam: UserLoginParam, bodyResultListener: IServiceResultListener<LoginBodyResult>)
    fun add(addUserParam: AddUserParam, bodyResultListener: IServiceResultListener<LoginBodyResult>)
    fun findById(userId: String, token: String, bodyResultListener: IServiceResultListener<FindUserByIdResult>)
    fun getAllUsers(token: String, bodyResultListener: IServiceResultListener<AllUsersResult>)
}