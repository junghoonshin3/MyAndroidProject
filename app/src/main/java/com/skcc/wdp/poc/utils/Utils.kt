package com.skcc.wdp.poc.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.security.MessageDigest


object Utils {

    fun setStatusBarColor(activity: Activity, isLight: Boolean, color: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isLight) {
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = Color.parseColor(color)
        }
    }

    fun setStatusBarColor(activity: Activity, isLight: Boolean, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isLight) {
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = ContextCompat.getColor(activity, color)
        }
    }

    fun getApplicationSignature(context: Context): List<String> {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                // New signature
                val sig = context.packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.GET_SIGNING_CERTIFICATES
                ).signingInfo
                return if (sig.hasMultipleSigners()) {
                    // Send all with apkContentsSigners
                    sig.apkContentsSigners.map {
                        val digest = MessageDigest.getInstance("SHA")
                        digest.update(it.toByteArray())
                        bytesToHex(digest.digest())
                    }
                } else {
                    // Send one with signingCertificateHistory
                    sig.signingCertificateHistory.map {
                        val digest = MessageDigest.getInstance("SHA")
                        digest.update(it.toByteArray())
                        bytesToHex(digest.digest())
                    }
                }
            } else {
                val sig = context.packageManager.getPackageInfo(
                    context.packageName,
                    PackageManager.GET_SIGNATURES
                ).signatures
                return sig.map {
                    val digest = MessageDigest.getInstance("SHA")
                    digest.update(it.toByteArray())
                    bytesToHex(digest.digest())
                }
            }

        } catch (e: Exception) {
            // Handle error
        }
        return emptyList()
    }

    private fun bytesToHex(bytes: ByteArray): String {
        var signature = ""
        for (i in bytes.indices) {
            signature += String.format("%02X", bytes[i])
            if (i != bytes.size - 1) {
                signature += ":"
            }
        }
        return signature
    }

    //인스톨러 확인
    fun verifyGoogleInstaller(context: Context): Boolean {
        val installer = context.packageManager.getInstallerPackageName(context.packageName)
        return installer != null && installer.startsWith("com.android.vending")
    }

    fun checkRootRelease(): Boolean {
        val buildTags = Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    fun checkRootFilePaths(): Boolean {
        val paths = arrayOf(
            "/sbin/su",
            "/system/su",
            "/system/bin/su",
            "/system/sbin/su",
            "/system/xbin/su",
            "/system/xbin/mu",
            "/system/bin/.ext/.su",
            "/system/usr/su-backup",
            "/data/data/com.noshufou.android.su",
            "/system/app/Superuser.apk",
            "/system/app/su.apk",
            "/system/bin/.ext",
            "/system/xbin/.ext",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su",
            "/su/bin/su"
        )
        for (path in paths) {
            if (File(path).exists()) return true
        }
        return false
    }

    fun checkRootCommand(): Boolean {
        var process: Process? = null
        return try {
            process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            val `in` = BufferedReader(InputStreamReader(process!!.inputStream))
            `in`.readLine() != null
        } catch (t: Throwable) {
            false
        } finally {
            process?.destroy()
        }
    }
}