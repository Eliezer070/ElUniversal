package com.eas.challenge.notes

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.eas.challenge.notes.adapter.AdapterTab


class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.eas.challenge.R.layout.activity_main)

        tryToOpenHomeFragment()

    }


    private fun tryToOpenHomeFragment() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.INTERNET), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS
            )

        } else {
            showHomeFragment()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showHomeFragment()
            } else {
                Toast.makeText(this, "You must have internet permission at least", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showHomeFragment() {
        val fragmentAdapter = AdapterTab(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter
    }

}
