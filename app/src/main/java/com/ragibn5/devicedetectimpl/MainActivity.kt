package com.ragibn5.devicedetectimpl

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ragibn5.devicedetect.DeviceVendor
import com.ragibn5.devicedetect.utils.DefaultTerminal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

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

        initialize()
    }

    private fun initialize() {
        CoroutineScope(Dispatchers.IO).launch {
            val vendor = DeviceVendor.detect()
            val fingerprint = Build.FINGERPRINT
            val commandResult = DefaultTerminal().executeCommand("getprop") ?: "N/A"
            CoroutineScope(Dispatchers.Main).launch {
                findViewById<TextView>(R.id.overview).text = String.format(
                    "Brand: %s\nManufacturer: %s\nOS: %s\n\nFINGERPRINT: %s",
                    vendor.brand,
                    vendor.manufacturer,
                    vendor.os,
                    Build.FINGERPRINT
                )
                findViewById<TextView>(R.id.props).text = commandResult
                findViewById<Button>(R.id.share).setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        handleShareFileRequest(vendor, fingerprint, commandResult)
                    }
                }
                findViewById<Button>(R.id.share).setEnabled(true)
            }
        }
    }

    suspend fun handleShareFileRequest(
        vendor: DeviceVendor,
        fingerprint: String?,
        commandResult: String
    ) {
        // Create what to share
        val textToShare = String.format(
            "%s\n\n%s",
            String.format(
                "OVERVIEW:\nBrand: %s\nManufacturer: %s\nOS: %s\nFINGERPRINT: %s",
                vendor.brand,
                vendor.manufacturer,
                vendor.os,
                fingerprint
            ),
            "DEVICE_PROPS:\n$commandResult"
        )

        // Create a temporary file in the cache directory
        val fileUri: Uri? = runCatching {
            val file = File.createTempFile("device_info_", ".txt", externalCacheDir)
            file.writeText(textToShare)

            // Use FileProvider for sharing
            FileProvider.getUriForFile(
                this@MainActivity,
                "${this@MainActivity.packageName}.fileprovider",
                file
            )
        }.getOrElse {
            it.printStackTrace()
            null
        }

        if (fileUri != null) {
            runCatching {
                startActivity(
                    Intent.createChooser(
                        Intent(Intent.ACTION_SEND).apply {
                            setDataAndType(
                                fileUri,
                                this@MainActivity.contentResolver.getType(fileUri)
                            )
                            putExtra(
                                Intent.EXTRA_EMAIL,
                                arrayOf("nowrose.ragib@vivasoftltd.com")
                            )
                            putExtra(
                                Intent.EXTRA_SUBJECT,
                                "Device info for PiTracker"
                            )
                            putExtra(
                                Intent.EXTRA_STREAM,
                                fileUri
                            )
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        },
                        "Share via"
                    )
                )
            }.onFailure {
                startActivity(
                    Intent.createChooser(
                        Intent().apply {
                            action = Intent.ACTION_SEND
                            type = "text/plain"
                            putExtra(Intent.EXTRA_STREAM, fileUri)
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        },
                        "Share via"
                    )
                )
            }
        } else {
            // fallback: share plain text
            startActivity(
                Intent.createChooser(Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, textToShare)
                    type = "text/plain"
                }, "Share via")
            )
        }
    }
}