package paf.songrecorder.viewmodels

import android.media.MediaPlayer
import java.io.File

class SongPlayerController{

    val TAG = "Song Player"
    var player: MediaPlayer = MediaPlayer()

    init {

        //        try {
//        } catch (e: IOException) {
//            Log.e(TAG, "Failed preparing the player")
//        }
    }

    fun startPlayer(audioFile: File) {
        if(!player.isPlaying) {
            player.stop()
        }
        player.setDataSource(audioFile.absolutePath)
        player.prepare()
        player.start()
    }

    fun stopPlayer() {
        player.stop()
        player.release()

    }
}