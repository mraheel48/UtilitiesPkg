@file:Suppress("unused")

package com.example.utilitiespkg.utils


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.util.Log

private var TAG: String = ChooserHelper::class.java.simpleName
const val REQUEST_PHOTO = 1011
const val REQUEST_VIDEO = 1012

object ChooserHelper

/**
 * ToDO.. Open Gallery and select single image
 *
 *
 * Required Permission
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>
 *
 * @param REQUEST_PHOTO Application specific request code to match with a result
 */
fun Context.chooseImage(REQUEST_PHOTO: Int) {
    try {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        (this as Activity).startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            REQUEST_PHOTO
        )
    } catch (e: Exception) {
        Log.d(TAG, e.toString())
    }
}

fun Context.chooseMultipleImage(REQUEST_MULTIPLE_PHOTO: Int) {
    val intent = Intent()
    intent.type = "image/*"
    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
    intent.action = Intent.ACTION_GET_CONTENT
    (this as Activity).startActivityForResult(
        Intent.createChooser(intent, "Select Picture"),
        REQUEST_MULTIPLE_PHOTO
    )
}

/**
 * ToDO.. Open Gallery and select video
 *
 *
 * Required Permission
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>
 *
 * @param REQUEST_VIDEO Application specific request code to match with a result
 */
fun Context.chooseVideo(REQUEST_VIDEO: Int) {
    try {
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        (this as Activity).startActivityForResult(
            Intent.createChooser(intent, "Select Video"),
            REQUEST_VIDEO
        )
    } catch (e: Exception) {
        Log.d(TAG, e.toString())
    }
}