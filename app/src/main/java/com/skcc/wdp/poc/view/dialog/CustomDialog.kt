package com.skcc.wdp.poc.view.dialog

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.skcc.wdp.poc.R

class CustomDialog(var context: Activity) {
    fun create(title: String, contents: String, start: String, end: String, startClick: () -> Unit, endClick: () -> Unit): Dialog {
        var dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_custom)

        dialog.window?.run {
            var params = attributes
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            params.dimAmount = 0.3f
            attributes = params
        }
        dialog.setCancelable(false)

        dialog.findViewById<TextView>(R.id.tv_dialog_title).text = title
        dialog.findViewById<TextView>(R.id.tv_dialog_contents).text = contents

        dialog.findViewById<MaterialButton>(R.id.mb_start).text = start
        dialog.findViewById<MaterialButton>(R.id.mb_start).setOnClickListener {
            dialog.dismiss()
            startClick()
        }
        dialog.findViewById<MaterialButton>(R.id.mb_end).text = end
        dialog.findViewById<MaterialButton>(R.id.mb_end).setOnClickListener {
            dialog.dismiss()
            endClick()
        }
        return dialog
    }

    fun create(title: String, contents: String, end: String, endClick: () -> Unit): Dialog {
        var dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_custom)

        dialog.window?.run {
            var params = attributes
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            params.dimAmount = 0.3f
            attributes = params
        }
        dialog.setCancelable(false)

        dialog.findViewById<TextView>(R.id.tv_dialog_title).text = title
        dialog.findViewById<TextView>(R.id.tv_dialog_contents).text = contents

        dialog.findViewById<MaterialButton>(R.id.mb_start).visibility = View.GONE

        dialog.findViewById<MaterialButton>(R.id.mb_end).text = end
        dialog.findViewById<MaterialButton>(R.id.mb_end).setOnClickListener {
            dialog.dismiss()
            endClick()
        }

        return dialog
    }

    fun create(title: String, contents: String, start: String): Dialog {
        var dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_custom)

        dialog.window?.run {
            var params = attributes
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            params.dimAmount = 0.3f
            attributes = params
        }
        dialog.setCancelable(false)

        dialog.findViewById<TextView>(R.id.tv_dialog_title).text = title
        dialog.findViewById<TextView>(R.id.tv_dialog_contents).text = contents

        dialog.findViewById<MaterialButton>(R.id.mb_start).visibility = View.GONE

        dialog.findViewById<MaterialButton>(R.id.mb_end).text = start
        dialog.findViewById<MaterialButton>(R.id.mb_end).setOnClickListener {
            dialog.dismiss()
        }
        return dialog
    }

    fun permissions(startClick: (Dialog) -> Unit): Dialog {
        var dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_permissions)

        dialog.window?.run {
            var params = attributes
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            params.dimAmount = 0.3f
            attributes = params
        }
        dialog.setCancelable(false)

        dialog.findViewById<MaterialButton>(R.id.mb_ok).setOnClickListener {
            startClick(dialog)
        }
        return dialog
    }
}