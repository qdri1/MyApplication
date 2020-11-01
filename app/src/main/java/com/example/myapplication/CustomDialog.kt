package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

inline fun dialog(config: CustomDialogDSL.() -> Unit): CustomDialog {
    val dsl = CustomDialogDSL().apply(config)
    return CustomDialog(
        dsl.title,
        dsl.message,
        dsl.icon,
        dsl.positiveButton.text,
        dsl.positiveButton.click
    )
}

class CustomDialogDSL {

    var title: String = "Title"
    var message: String = "Message"
    var icon: Int = R.mipmap.ic_launcher

    val positiveButton = PositiveButtonDSL()

    inner class PositiveButtonDSL {
        operator fun invoke(config: PositiveButtonDSL.() -> Unit) {
            this.apply(config)
        }
        var text: String = "OK"
        var click: (DialogInterface, Int) -> Unit = { dialog, _ -> dialog.dismiss() }
    }

}

class CustomDialog(
    private val title: String,
    private val message: String,
    private val icon: Int,
    private val positiveButtonText: String,
    private val positiveButton: (DialogInterface, Int) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title)
                .setMessage(message)
                .setIcon(icon)
                .setPositiveButton(positiveButtonText) { dialog, id ->
                    positiveButton(dialog, id)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

