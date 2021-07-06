package fr.esgi.codesurvival.users

data class User(var id: String?, var email: String?, var role: String?,var username: String?,var token: String?) {

}

/*
{
    "id": "255c8cbb-d2ec-4df4-af1c-dc14031e6ec9",
    "email": "monemail@gmail.com",
    "role": "ADMIN",
    "username": "test",
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjI2MDIxMDkwfQ._aTE8UppY_a_BHjTHOPMev1ejmQCCmbxhNXv1XtFac83UEzSsliPQdQYeo_jIFNIHLvcTKrOdEcG2dYl3KZPyQ"
}
*/