package paf.songrecorder.viewmodels

import android.media.MediaPlayer
import java.io.File

class SongPlayerViewModel{

    val TAG = "Song Player"
    var player: MediaPlayer = MediaPlayer()

    init {

        //        try {
//        } catch (e: IOException) {
//            Log.e(TAG, "Failed preparing the player")
//        }
    }

    fun startPlayer(audioFile: File) {
        player.setDataSource(audioFile.absolutePath)
        player.prepare()
        player.start()
        if(!player.isPlaying){
            stopPlayer()
        }
    }

    fun stopPlayer() {
        player.stop()
        player.release()

    }
}