package paf.songrecorder.views

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_song.*
import paf.songrecorder.R
import paf.songrecorder.models.Song
import paf.songrecorder.helpers.DateHelper
import paf.songrecorder.viewmodels.SongPlayerViewModel
import paf.songrecorder.viewmodels.SongRecorderViewModel
import paf.songrecorder.views.adapters.TrackAdapter
import java.io.File

class SongActivity : AppCompatActivity() {

    companion object {
        private const val SONG_KEY = "SONG"
        private const val APP_NAME = "Song Recorder"
    }

    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var playButton: Button
    private lateinit var playerViewModel: SongPlayerViewModel
    private lateinit var song: Song
    private lateinit var newSongFolder: File
    private lateinit var outputFile: File
    private val songRecorderViewModel = SongRecorderViewModel()
    private lateinit var currentDateTime: String
    private lateinit var adapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        song = intent.getSerializableExtra(SONG_KEY) as Song
        startButton = b_startRecording
        stopButton = b_stopRecording
        playButton = b_playSong

        stopButton.isEnabled = false
        playButton.isEnabled = false

        setUpRecyclerView()

        var appFolder = File(Environment.getExternalStorageDirectory().toString() + "/$APP_NAME")

        if(appFolder.isDirectory) {
            newSongFolder = File(appFolder.absolutePath + "/${song.title}")
            newSongFolder.mkdirs()
            createFolder(newSongFolder.absolutePath + "/${song.title}.3gp")

        } else {
            appFolder = File(Environment.getExternalStorageDirectory().toString(), APP_NAME)
            appFolder.mkdirs()
            newSongFolder = File(appFolder.absolutePath + "/${song.title}")
            newSongFolder.mkdirs()
            createFolder(newSongFolder.absolutePath + "/${song.title}.3gp")
        }

        startButton.setOnClickListener {
            startRecording()
        }
        stopButton.setOnClickListener {
            stopRecording()
        }
        playButton.setOnClickListener {
            playTrack(song.title + ".3gp")
        }

        playerViewModel = SongPlayerViewModel()
    }

    fun createFolder(path: String){
        val root = File(path)
        root.mkdirs()
//        return File(root, folderName)
//        var fos = FileOutputStream(folder)
    }

    private fun createNewFile() : File{
        return File(song.songFolder + DateHelper.getCurrentDateAndTimeAsString())
    }

    private fun startRecording() {
        songRecorderViewModel.startRecorder(createNewFile())
        startButton.isEnabled = false
        stopButton.isEnabled = true
        Toast.makeText(applicationContext, "Recording started, Toast.LENGTH_LONG", Toast.LENGTH_LONG).show()
    }

    private fun stopRecording() {
        startButton.isEnabled = true
        stopButton.isEnabled = false
        playButton.isEnabled = true
        songRecorderViewModel.stopRecorder()
        Toast.makeText(applicationContext, "Audio Recorder successfully", Toast.LENGTH_LONG).show()
    }

    private fun playTrack(songTitle: String) {
//        val mediaPlayer = MediaPlayer()
        playerViewModel.startPlayer(outputFile)
//        try {
//            mediaPlayer.setDataSource(songTitle)
//            mediaPlayer.prepare()
//            mediaPlayer.start()
        Toast.makeText(applicationContext, "Playing Audio", Toast.LENGTH_LONG).show()
//        } catch (e: Exception) {
//            // make something
//        }
    }

    private fun setUpRecyclerView() {

        trackListView.layoutManager = LinearLayoutManager(this)

        adapter = TrackAdapter(song.trackList)
        trackListView.adapter = adapter
    }

}
