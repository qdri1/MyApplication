package com.example.myapplication

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainViewStateDelegate {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.delegate = this
        viewModel.onViewPrepared()

        nameTV.setOnClickListener {
            showDialogDSL()
        }
    }

    override fun updateUser(user: UserModel) {
        nameTV.text = user.name
        ageTV.text = user.age.toString()
        hobbyTV.text = user.getValueOf("hobby") from applicationContext
    }

    private fun showDialog() {
        CustomDialog(
            "Важное сообщение!",
            "Покормите кота!",
            R.mipmap.ic_launcher,
            "ОК, иду на кухню"
        ) { _, _ ->
            println("###clicked")
        }.show(supportFragmentManager, "CustomDialog")
    }

    private fun showDialogDSL() {
        dialog {
            title = "Важное сообщение!"
            message = "Покормите кота!"
            icon = R.mipmap.ic_launcher
            positiveButton {
                text = "ОК, иду на кухню"
                click = ::positiveButtonClicked
            }
        }.show(supportFragmentManager, "CustomDialog")
    }

    private fun positiveButtonClicked(dialog: DialogInterface, which: Int) {
        println("###clicked")
    }

}

