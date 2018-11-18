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
import kotlinx.android.synthetic.main.song_list_activity.*
import paf.songrecorder.R
import paf.songrecorder.helpers.DateHelper
import paf.songrecorder.models.Song
import paf.songrecorder.viewmodels.SongModel
import paf.songrecorder.views.adapters.SongAdapter
import java.io.File
import java.util.*


class MainActivity : AppCompatActivity(){

    companion object {
        private const val APP_NAME = "Song Recorder"
        private val APP_FOLDER_NAME = "${Environment.getExternalStorageDirectory()}/$APP_NAME/"
        private const val SONG = "SONG"
    }

    private lateinit var songList : ArrayList<Song>
    private var adapter: SongAdapter? = null
    private lateinit var songModel: SongModel

    private val path: File = Environment.getDataDirectory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_list_activity)

        songModel = SongModel(APP_FOLDER_NAME)

        setUpRecyclerView()

        startSongActivity()

        if(!path.exists()){
            path.mkdirs()
        }

    }

    private fun setUpRecyclerView() {

        songListView.layoutManager = LinearLayoutManager(this)

        adapter = SongAdapter(
                songList = songModel.getListOfSongs()
        )
        songListView.adapter = adapter
    }

    private fun startSongActivity() {
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

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "Back activity", Toast.LENGTH_LONG).show()
        resetRecyclerView()
        setUpRecyclerView()
    }

    fun resetRecyclerView(){
        adapter = null
        songListView.adapter = null
    }

    private fun updateListOfSongsView() {

    }

//    inner class CustomAdapter : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){
//
//        var folderList: ArrayList<File> = FileHelper.getDirectoryListSortedByLastModified(APP_FOLDER_NAME)!!
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
//            val inflater = LayoutInflater.from(parent.context)
//            val view = inflater.inflate(R.layout.item_song, parent, false)
//            return CustomViewHolder(view)
//        }
//
//        override fun getItemCount(): Int {
//            return folderList.size
//        }
//
//        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//            var currentItem = folderList.get(position)
//            holder.title.text = currentItem.name //("song_name")
//        }
//
//        inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
//
//            var title: TextView = itemView.findViewById(R.id.tv_title)
//            var date: TextView = itemView.findViewById(R.id.tv_date_and_time)
//            var imageSong: ImageView? = itemView.findViewById(R.id.imv_date_and_time)
//
//            override fun onClick(view: View?) {
//
//            }
//        }
//    }
}
