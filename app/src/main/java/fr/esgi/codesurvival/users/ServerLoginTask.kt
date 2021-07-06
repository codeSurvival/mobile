package fr.esgi.codesurvival.users

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ServerLoginTask(
    private var user: User?,
    private val callback: () -> Unit
) : Callback<User> {
    companion object {
        private const val TAG: String = "UserConnection"
    }
    var correctAnswer : Boolean = false

        override fun onFailure(call: Call<User>, t: Throwable) {
            Log.e(TAG, "An error has occurred", t)
        }

        override fun onResponse(call: Call<User>, response: Response<User>) {
            response.body()?.let {
                    user: User -> this.user = user
            }
            Log.d(TAG, "onResponse: user : $user")
            this.callback()
        }
}
/*
[
{
    "id": "255c8cbb-d2ec-4df4-af1c-dc14031e6ec9",
    "email": "monemail@gmail.com",
    "role": "ADMIN",
    "username": "test",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjI2MDIxMDkwfQ._aTE8UppY_a_BHjTHOPMev1ejmQCCmbxhNXv1XtFac83UEzSsliPQdQYeo_jIFNIHLvcTKrOdEcG2dYl3KZPyQ"
}
]
*/