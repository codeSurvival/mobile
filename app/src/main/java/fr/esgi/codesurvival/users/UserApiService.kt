package fr.esgi.codesurvival.users

import retrofit2.Call
import retrofit2.http.GET

interface UserApiService {
    companion object{
        /*const val BASE_URL = "http://127.0.0.1:8081/"
        private const val URI = "signin"*/
        const val BASE_URL = "http://www.json-generator.com/"
        private const val URI = "api/json/get/cffbFoeNyW?indent=2"
    }

    @GET(URI)
    fun getUserInfo() : Call<User>

}