package com.example.kamera

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

            companion object{
                private  const val  CAMERA_PERRMISSION_CODE =1
                private  const val CAMERA = 2

            }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            if(ContextCompat.checkSelfPermission(
                            this,
                            android.Manifest.permission.CAMERA
                    )== PackageManager.PERMISSION_GRANTED
            ){
                 val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                 startActivityForResult(intent, CAMERA_PERRMISSION_CODE)

            }else{
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.CAMERA),
                        CAMERA_PERRMISSION_CODE
                )
            }


        }// sprawdzenie dostepu do kamery


   }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERRMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_PERRMISSION_CODE)
            }else{
                Toast.makeText(
                            this,
                            "Prosze o dostep do kamery", Toast.LENGTH_LONG
                ).show()
            }

    }

}