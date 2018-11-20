package paf.songrecorder.views

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import io.reactivex.disposables.Disposable
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.song_list_activity.*
import paf.songrecorder.R
import paf.songrecorder.models.Song
import paf.songrecorder.helpers.SongHelper
import paf.songrecorder.views.adapters.TestSongAdapter
import java.text.SimpleDateFormat
import java.util.*

class TestSongListActivity : AppCompatActivity() {

    companion object {
        private val APP_NAME = "Song Recorder"
        private val APP_FOLDER_NAME = "${Environment.getExternalStorageDirectory()}/$APP_NAME/"
        private val SONG = "SONG"
    }

//    private var items : ArrayList<ViewType>
    private lateinit var songAdapter: TestSongAdapter
    private val songController = SongHelper(APP_FOLDER_NAME)
    private lateinit var songSubscription: Disposable
    var subscriptions = ArrayCompositeSubscription(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureLayout()
        createObservable()
        if(savedInstanceState == null){
            requestSongs()
        }

        startSongActivity()
    }

    private fun configureLayout() {
        setContentView(R.layout.song_list_activity)
        songListView.layoutManager = LinearLayoutManager(this)
        songAdapter = TestSongAdapter()
        songListView.adapter = songAdapter
    }

    private fun startSongActivity() {
        val li = LayoutInflater.from(this)
        val dialogView = li.inflate(R.layout.text_edit_dialog, null)

        val sdf = SimpleDateFormat("dd-M-yyyy_hhmmss")
        val currentDateTime = sdf.format(Date())

        val defaultName = "audioTrack_$currentDateTime"
        var songTitle : String

        val dialogEditText = dialogView.findViewById<EditText>(R.id.editText_dialog)

        val songTitleDialogBuilder = AlertDialog.Builder(this)
        songTitleDialogBuilder.setView(dialogView)
        songTitleDialogBuilder
                .setTitle("Enter the name of the song")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, id ->
                    if (dialogEditText.getText().isNullOrEmpty())
                        songTitle = defaultName
                    else
                        songTitle = dialogEditText.getText().toString()

                    val newSongIntent = Intent(this, SongActivity::class.java)
                    val song = Song(songTitle, null, APP_FOLDER_NAME + songTitle)

                    newSongIntent.putExtra(SONG, song)

                    Toast.makeText(applicationContext, "Creating $songTitle", Toast.LENGTH_LONG).show()
                    startActivity(newSongIntent)
                }
                .setNegativeButton("Cancel") { dialog, id -> dialog.cancel() }

        val songTitleDialog = songTitleDialogBuilder.create()
        newSongButton.setOnClickListener {
            if (!songTitleDialog.isShowing) {
                songTitleDialog.show()
            }
        }
    }

    private fun requestSongs() {
        songSubscription = songController.testGetListOfSongs()
                .subscribeOn(Schedulers.io())
                .subscribe{
                    songAdapter.setSongs(it)
                }
    }

    private fun createObservable() {
//        val subscription = songController.testGetListOfSongs()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//
//                )
//        val songsObservable = Observable.fromCallable { songController.songObservable }
//        songSubscription = songsObservable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { songs -> songAdapter.setSongs(songs) }
    }


    override fun onDestroy() {
        super.onDestroy()
        if (!songSubscription.isDisposed){
            songSubscription.dispose()
        }
    }

    override fun onStop() {
        super.onStop()
        if(!songSubscription.isDisposed){
            songSubscription.dispose()
        }
    }
}
