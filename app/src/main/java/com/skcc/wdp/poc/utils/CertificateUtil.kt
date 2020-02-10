package com.skcc.wdp.poc.utils

import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate


class CertificateUtil {
    fun getPublicKey() {
        var file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        var folders = file.list()

        getDirectoryList()?.run {
            Log.i("sgim", "${this[0].path}")
            val fis =
                FileInputStream(File("${this[0]}/signCert.der"))
            val certificateFactory = CertificateFactory.getInstance("X509")
            val cert = certificateFactory.generateCertificate(fis) as X509Certificate

            fis.close()

            println(cert.notAfter)
            println("-----------------")
            println(cert)
            println("-----------------")
            println(cert.publicKey)
        }
    }

    private fun getDirectoryList(): Array<File>? {
        var path = File("${Environment.getExternalStorageDirectory()}/NPKI/yessign/USER")

        Log.i("sgim", "path : $path")
        if (!path.exists()) {
            Log.i("sgim", "경로가 존재하지 않습니다 ")
        }
        return path.listFiles()
    }
}