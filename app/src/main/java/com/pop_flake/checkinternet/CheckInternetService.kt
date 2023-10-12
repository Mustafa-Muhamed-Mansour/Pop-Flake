package com.pop_flake.checkinternet

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.RelativeLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.pop_flake.R


class CheckInternetService : BroadcastReceiver() {


    override fun onReceive(p0: Context, p1: Intent) {
        val dialog = Dialog(p0, android.R.style.Animation)
        dialog.setContentView(R.layout.cutsom_dialog_internet)
        val btnRetry = dialog.findViewById<MaterialButton>(R.id.btn_retry)
        val parentRelative =
            dialog.findViewById<RelativeLayout>(R.id.parent_relative_custom_dialog_internet)

        try {
            if (NetworkUtil.networkState(p0)) {
                dialog.dismiss()
            } else {
                dialog.show()
            }

        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        btnRetry.setOnClickListener {
            try {
                if (NetworkUtil.networkState(p0)) {
                    dialog.dismiss()
                } else {
                    dialog.show()
                    Snackbar.make(parentRelative, "No Internet Connection", Snackbar.LENGTH_SHORT)
                        .show()
                }

            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }
}