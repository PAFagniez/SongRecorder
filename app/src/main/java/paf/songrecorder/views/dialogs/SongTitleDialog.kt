package paf.songrecorder.views.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle

class SongTitleDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
//            builder.apply {
//                setTitle("Enter song's title")
//                setView(R.layout.editText)
//                setPositiveButton("Validate", DialogInterface.OnClickListener() {
//
//                })
//            }

            builder.create()
        } ?: throw IllegalStateException("Activity can't be null")
    }
}