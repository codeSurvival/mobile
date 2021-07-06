package fr.esgi.codesurvival.users

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserRepository {
    private var apiService: UserApiService? = null

    init {
        this.apiService = Retrofit.Builder()
            .baseUrl(UserApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }
    fun getUser(callback : ServerLoginTask) = this.apiService?.getUserInfo()?.enqueue(callback)
}