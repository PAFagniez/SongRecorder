package paf.songrecorder.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.song_list_activity.*
import paf.songrecorder.R
import paf.songrecorder.helpers.DateHelper
import paf.songrecorder.helpers.FileHelper
import paf.songrecorder.helpers.SongHelper
import paf.songrecorder.models.Song
import paf.songrecorder.viewmodels.SongModel
import paf.songrecorder.views.adapters.SongAdapter
import java.io.File


class MainActivity : AppCompatActivity(){

    companion object {
        private const val APP_NAME = "Song Recorder"
        val APP_FOLDER_NAME = "${Environment.getExternalStorageDirectory()}/$APP_NAME/"
        private const val SONG = "SONG"
        private const val ADDED_SONG_CODE = 0
        private const val SONG_LIST = "SONG_LIST"
    }

    private lateinit var songList : ArrayList<Song>
    private var adapter: SongAdapter? = null
    private lateinit var songModel: SongModel
    private lateinit var newSongDialog: AlertDialog

    private val path: File = Environment.getDataDirectory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_list_activity)

        setUpRecyclerView()

        initNewSongDialog()

        if(!path.exists()){
            path.mkdirs()
        }

        newSongButton.setOnClickListener {
            if (!newSongDialog.isShowing) {
                newSongDialog.show()
            }
        }
    }

    private fun setUpRecyclerView() {

        songListView.layoutManager = LinearLayoutManager(this)

        adapter = SongAdapter(
                songList = SongHelper.getSongModelList(APP_FOLDER_NAME)
        )
        songListView.adapter = adapter
    }

    private fun initNewSongDialog() {
        val li = LayoutInflater.from(this)
        val dialogView = li.inflate(R.layout.text_edit_dialog, null)

        val currentDateTime = DateHelper.getCurrentDateAndTimeAsString()

        val defaultName = "audioTrack_$currentDateTime"
        var songTitle : String

        val dialogEditText = dialogView.findViewById<EditText>(R.id.editText_dialog)

        val songTitleDialogBuilder = AlertDialog.Builder(this)
        songTitleDialogBuilder.setView(dialogView)
        songTitleDialogBuilder
                .setTitle("Enter the name of the song")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, id ->
                    if (dialogEditText.text.isNullOrEmpty())
                        songTitle = defaultName
                    else
                        songTitle = dialogEditText.text.toString()

                    val newSongIntent = Intent(this, SongActivity::class.java)
                    val song = Song(songTitle, null, "$APP_FOLDER_NAME$songTitle/")
                    val songModel = SongModel(song)
                    val folderCreationCode = FileHelper.createNewSongFolder(songModel)

                    if(folderCreationCode == FileHelper.FOLDER_CREATED) {
                        newSongIntent.putExtra(SONG, songModel)
                        Toast.makeText(applicationContext, "Creating $songTitle",
                                Toast.LENGTH_LONG).show()
                        startActivityForResult(newSongIntent, ADDED_SONG_CODE)
                    }
                    else if (folderCreationCode == FileHelper.FOLDER_CREATION_FAILED){
                        Toast.makeText(applicationContext, "Failed to create the song",
                                Toast.LENGTH_LONG).show()
                    }
                    else if (folderCreationCode == FileHelper.FOLDER_ALREADY_EXISTS) {
                        Toast.makeText(applicationContext, "Name of the song already exists",
                                Toast.LENGTH_LONG).show()
                    }

//                    FileHelper.createNewAudioFile(song.title+".3gp")
                }
                .setNegativeButton("Cancel") { dialog, id -> dialog.cancel() }

        newSongDialog = songTitleDialogBuilder.create()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "Back activity", Toast.LENGTH_LONG).show()
//        resetRecyclerView()
//        setUpRecyclerView()
    }

    fun resetRecyclerView(){
        adapter = null
        songListView.adapter = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADDED_SONG_CODE && resultCode == Activity.RESULT_OK){
            val updatedSongList = data?.extras?.get(SONG_LIST) as ArrayList<SongModel>
            adapter?.update(updatedSongList)
        }
    }
}
