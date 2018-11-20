package paf.songrecorder.views

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_song.*
import paf.songrecorder.R
import paf.songrecorder.databinding.ActivitySongBinding
import paf.songrecorder.helpers.DateHelper
import paf.songrecorder.helpers.SongHelper
import paf.songrecorder.helpers.TrackHelper
import paf.songrecorder.models.Song
import paf.songrecorder.viewmodels.SongModel
import paf.songrecorder.viewmodels.SongPlayerController
import paf.songrecorder.viewmodels.SongRecorderController
import paf.songrecorder.views.adapters.TrackAdapter
import java.io.File

class SongActivity : AppCompatActivity() {

    companion object {
        private const val SONG_KEY = "SONG"
        private const val SONG_LIST = "SONG_LIST"
    }

    private lateinit var playerController: SongPlayerController
    private lateinit var activitySongBinding: ActivitySongBinding
    private lateinit var song: Song
    private lateinit var lastAudioFile: File
    private val songRecorderViewModel = SongRecorderController()
    private lateinit var adapter: TrackAdapter
    private lateinit var songModel: SongModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySongBinding = DataBindingUtil.setContentView(this, R.layout.activity_song)

        song = intent.getParcelableExtra(SONG_KEY) as Song
        songModel = SongModel(song)

        stopRecordingBtn.isEnabled = false
        playSongBtn.isEnabled = false

        setUpRecyclerView()

        startRecordingBtn.setOnClickListener {
            startRecording()
        }
        stopRecordingBtn.setOnClickListener {
            stopRecording()
            adapter.update(TrackHelper.createTrackList(song))
        }
        playSongBtn.setOnClickListener {
            playTrack(songModel.title + ".3gp")
        }

        playerController = SongPlayerController()
        activitySongBinding.song = songModel


    }

    fun createFolder(path: String){
        val root = File(path)
        root.mkdirs()
//        return File(root, folderName)
//        var fos = FileOutputStream(folder)
    }

    private fun createNewFile() : File{
        lastAudioFile = File("${songModel.songFolder}/${DateHelper.getCurrentDateAndTimeForNameAsString()}.3gp")
        return lastAudioFile
    }

    private fun startRecording() {
        songRecorderViewModel.startRecorder(createNewFile())
        startRecordingBtn.isEnabled = false
        stopRecordingBtn.isEnabled = true
        playSongBtn.isEnabled = false
        Toast.makeText(applicationContext, "Recording started, Toast.LENGTH_LONG", Toast.LENGTH_LONG).show()
    }

    private fun stopRecording() {
        startRecordingBtn.isEnabled = true
        stopRecordingBtn.isEnabled = false
        playSongBtn.isEnabled = true
        songRecorderViewModel.stopRecorder()
        Toast.makeText(applicationContext, "Audio Recorder successfully", Toast.LENGTH_LONG).show()
    }

    private fun playTrack(songTitle: String) {
        playerController.startPlayer(lastAudioFile)
        Toast.makeText(applicationContext, "Playing Audio", Toast.LENGTH_LONG).show()
    }

    private fun setUpRecyclerView() {

        trackListView.layoutManager = LinearLayoutManager(this)

        adapter = TrackAdapter(TrackHelper.createTrackList(song))
        trackListView.adapter = adapter
    }

    override fun finish() {
        val returnIntent = Intent()
        returnIntent.putParcelableArrayListExtra(SONG_LIST, SongHelper.getSongList(MainActivity.APP_FOLDER_NAME))
        setResult(Activity.RESULT_OK, returnIntent)
        super.finish()
    }

    override fun onBackPressed() {
        playerController.stopPlayer()
        finish()
    }

}
