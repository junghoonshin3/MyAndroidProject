package com.skcc.wdp.poc.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.skcc.wdp.poc.R
import com.skcc.wdp.poc.common.Const
import com.skcc.wdp.poc.utils.CertificateUtil
import com.skcc.wdp.poc.view.dialog.CustomDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkVerify()
    }

    private fun checkVerify() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            var dialog = CustomDialog(this@MainActivity).permissions {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ), Const.REQUEST_PERMISSION
                )
                it.dismiss()
            }
            dialog.show()
        } else {
            CertificateUtil().getPublicKey()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == Const.REQUEST_PERMISSION) {
            var isPermission = true
            for (grantResult in grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED) {
                    isPermission = false
                    break
                }
            }

            if (isPermission) {
                CertificateUtil().getPublicKey()
            } else {

            }
        }
    }
}
