package fr.esgi.codesurvival

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class ConnectedPage : AppCompatActivity() {
    companion object{
        const val TAG = "NeedANameActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.connected_page)
        Log.d(TAG, "onCreate: ")
    }
}