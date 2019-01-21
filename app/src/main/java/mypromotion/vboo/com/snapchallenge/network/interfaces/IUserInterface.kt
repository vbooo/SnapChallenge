package mypromotion.vboo.com.snapchallenge.network.interfaces

import mypromotion.vboo.com.snapchallenge.model.User

interface IUserInterface {
    fun addUser(resultListener: IServiceResultListener<User>)
}