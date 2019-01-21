package mypromotion.vboo.com.snapchallenge.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientWS {

    companion object {
        val BASE_URL_DEV = "https://api.instagram.com/v1/"
        val BASE_URL_PROD = "https://api.instagram.com/v1/"

        fun getClient(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL_DEV)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }

}