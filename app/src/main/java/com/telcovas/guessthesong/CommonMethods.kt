package com.telcovas.guessthesong


import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.finishAffinity
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.regex.Pattern

class CommonMethods {

    fun addSupportActionBar(context: AppCompatActivity, title: Int) {
        if (context.supportActionBar != null) {
            context.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            context.supportActionBar?.setDisplayShowHomeEnabled(true)
            context.supportActionBar?.title = context.getString(title)
        }
    }


    companion object{
        val EMAIL_ADDRESS: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        fun showMessage(context: Context, messageData: String){
            Toast.makeText(context, messageData, Toast.LENGTH_SHORT).show()
        }

        fun openActivity(context: Context, activity: AppCompatActivity) {
            context.startActivity(Intent(context, activity::class.java), ActivityOptions.makeSceneTransitionAnimation(context as Activity).toBundle())
           /* val intentData = Intent(context, activity::class.java)
            intentData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intentData)*/
        }

        fun intentCalling(context: Context, activity: AppCompatActivity) {
            context.startActivity(Intent(context, activity::class.java))
            /* val intentData = Intent(context, activity::class.java)
             intentData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
             context.startActivity(intentData)*/
        }

        fun setSharedPreference(context: Context, preferenceName: String, preferenceValue: String?) {
            val editor = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE).edit()
            editor.putString(preferenceName, preferenceValue)
            editor.commit()
        }

        fun getSharedPreference(context: Context, preferenceName: String): String? {
            val pref = context.applicationContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
            return pref.getString(preferenceName, "")
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun isInternetAvailable(context: Context): Boolean {
            var result = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

            return result
        }

        fun isEmpty(text: EditText): Boolean {
            val str = text.text.toString()
            return TextUtils.isEmpty(str)
        }

        fun isEmail(text: EditText): Boolean {
            val email = text.text.toString()
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun hideKeyboard(context: Context, view: View) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun dateFormatConvert(context: Context, inputDateFormat: String, outputDateFormat: String, inputDateStr: String): String {
            val inputFormat = SimpleDateFormat(inputDateFormat)
            val outputFormat = SimpleDateFormat(outputDateFormat)
            val date = inputFormat.parse(inputDateStr)
            val outputDateStr = outputFormat.format(date)
            return outputDateStr
        }



        fun resizeAndCompressImageBeforeSend(
            context: Context,
            filePath: String?,
            fileName: String
        ): Bitmap {
            val MAX_IMAGE_SIZE = 300 * 300 // max final file size in kilobytes

// First decode with inJustDecodeBounds=true to check dimensions of image
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(filePath, options)

// Calculate inSampleSize(First we are going to resize the image to 800x800 image, in order to not have a big but very low quality image.
//resizing the image will already reduce the file size, but after resizing we will check the file size and start to compress image
            options.inSampleSize =
                calculateInSampleSize(
                    options,
                    80,
                    80
                )

// Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false
            options.inPreferredConfig = Bitmap.Config.ARGB_8888
            val bmpPic = BitmapFactory.decodeFile(filePath, options)
            var compressQuality = 50 // quality decreasing by 5 every loop.
            var streamLength: Int
            do {
               val bitmapStream = ByteArrayOutputStream()
                Log.d("compressBitmap", "Quality: $compressQuality")
                bmpPic.compress(Bitmap.CompressFormat.JPEG, compressQuality, bitmapStream)
                val bmpPicByteArray = bitmapStream.toByteArray()
                streamLength = bmpPicByteArray.size
                compressQuality -= 5
//   Log.d("compressBitmap", "Size: $streamLength" / 1024 + " kb")
            } while (streamLength >= MAX_IMAGE_SIZE)
            try {
//save the resized and compressed file to disk cache
                Log.d("compressBitmap", "cacheDir: " + context.cacheDir)
                val bmpFile = FileOutputStream(context.cacheDir.toString() + fileName + ".jpg")
                bmpPic.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpFile)

                Log.d("compressBitmap", "bmpPic: " + bmpPic.byteCount)

                bmpFile.flush()
                bmpFile.close()
            } catch (e: java.lang.Exception) {
                Log.e("compressBitmap", "Error on saving file")
            }
//return the path of resized and compressed file
//return context.cacheDir.toString() + fileName + ".jpg"
            return bmpPic

        }

        private fun calculateInSampleSize(
            options: BitmapFactory.Options,
            reqWidth: Int, reqHeight: Int
        ): Int {
// Raw height and width of image
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1
            if (height > reqHeight || width > reqWidth) {

// Calculate ratios of height and width to requested height and width
                val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
                val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())

// Choose the smallest ratio as inSampleSize value, this will guarantee a final image
// with both dimensions larger than or equal to the requested height and width.
                inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio

// This offers some additional logic in case the image has a strange
// aspect ratio. For example, a panorama may have a much larger
// width than height. In these cases the total pixels might still
// end up being too large to fit comfortably in memory, so we should
// be more aggressive with sample down the image (=larger inSampleSize).
                val totalPixels = (width * height).toFloat()

// Anything more than 2x the requested pixels we'll sample down further
                val totalReqPixelsCap = (reqWidth * reqHeight * 2).toFloat()
                while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                    inSampleSize++
                }
            }
            return inSampleSize
        }

        @Throws(IOException::class)
        private fun rotateImageIfRequired(context: Context, img: Bitmap, selectedImage: Uri): Bitmap? {
            val input = context.contentResolver.openInputStream(selectedImage)

            val ei: ExifInterface

//  ei = ExifInterface(selectedImage.path!!)
            ei =
                if (Build.VERSION.SDK_INT > 23)
                    ExifInterface(input!!)
                else ExifInterface(selectedImage.path!!)
            val orientation =
                ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            Log.e("orientation111", ":" + orientation)
            return when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(img, 90)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(img, 180)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(img, 270)
                ExifInterface.ORIENTATION_UNDEFINED -> rotateImage(img, 90)
                else -> img
            }
        }

        private fun rotateImage(img: Bitmap, degree: Int): Bitmap? {
            val matrix = Matrix()
            matrix.postRotate(degree.toFloat())
            val rotatedImg = Bitmap.createBitmap(img, 0, 0, img.width, img.height, matrix, true)
            img.recycle()
            return rotatedImg
        }
    }



    fun String.areDigitsOnly() = matches(Regex("[0-9]+"))

    fun String.areLettersOnly() = matches(Regex("[a-zA-Z]+"))


}