package fr.esgi.codesurvival

class RequestConstants {
    //Service names
    private val USER_CONNECTION = "checkCodeOrAppValidity" // Connexion

    //Service params
    val PASSWORD = "password"
    val USERNAME = "username"

    //Service params
    val JSON_PARAM_STATUS = "status"
    val JSON_PARAM_SUBSCRIPTION_TYPE = "subscriptionType"

    val DEFAULT_CODE_SURVIVAL_URL = "localhost:8081/"
    val VAL_APP_USER = "users"

    //param
    val PARAM_USERNAME: String = "&username="
    val PARAM_PASSWORD: String = "&password="

    //Service return keys
    val JSON_PARAM_STATUS_OK = "OK"
    val JSON_PARAM_STATUS_KO = "KO"
    val JSON_PARAM_ERROR_ALREADY_USED_CODE = "Wrongname"
    val JSON_PARAM_ERROR_ALREADY_INVALID_CODE = "Unautorized"

    fun buildUrlUserConnection(code: String): String {
        return DEFAULT_CODE_SURVIVAL_URL + VAL_APP_USER +
                PARAM_USERNAME + /*.getAppManager().getAppSubName() + */
                PARAM_PASSWORD /*+ AppManager.getAppManager().getAndroidID() + */
    }


}