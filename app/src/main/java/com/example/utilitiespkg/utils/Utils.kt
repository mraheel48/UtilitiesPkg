package com.example.utilitiespkg.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur
import com.example.utilitiespkg.BlurBuilder
import kotlin.math.roundToInt


fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(isEnabled: Boolean) {
    setEnabled(isEnabled)
    alpha = if (isEnabled) 1f else 0.5f
}

fun Context.toast(text: String?) {
    try {
        if (!(this as Activity).isFinishing) {
            val activity = this
            activity.runOnUiThread {
                Toast.makeText(this, "$text", Toast.LENGTH_SHORT).show()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


fun Context.showPermissionRequestDialog(
    title: String,
    body: String,
    callback: () -> Unit
) {
    AlertDialog.Builder(this).also {
        it.setTitle(title)
        it.setMessage(body)
        it.setPositiveButton("Ok") { _, _ ->
            callback()
        }
    }.create().show()
}


fun Context.blur(image: Bitmap?, radiusValue: Float = 7.5f): Bitmap? {

    return if (image != null && image.width > 0 && image.height > 0) {

        val width = (image!!.width * 0.4f).roundToInt()
        val height = (image.height * 0.4f).roundToInt()

        val inputBitmap = Bitmap.createScaledBitmap(image, width, height, false)
        val outputBitmap = Bitmap.createBitmap(inputBitmap)

        val rs = RenderScript.create(this)
        val theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
        val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
        theIntrinsic.setRadius(radiusValue)
        theIntrinsic.setInput(tmpIn)
        theIntrinsic.forEach(tmpOut)
        tmpOut.copyTo(outputBitmap)

        return outputBitmap

    } else {
        null
    }

}
