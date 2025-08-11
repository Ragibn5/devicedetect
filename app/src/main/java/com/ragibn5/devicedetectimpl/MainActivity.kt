package com.ragibn5.devicedetectimpl

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ragibn5.devicedetect.DeviceVendor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        test()
    }

    private fun test() {
        val vendor = DeviceVendor.detect()
        findViewById<TextView>(R.id.preview).text = String.format(
            "Brand: %s\nManufacturer: %s",
            vendor.brand,
            vendor.manufacturer,
        )
    }
}