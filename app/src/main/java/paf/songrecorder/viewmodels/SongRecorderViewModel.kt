package paf.songrecorder.viewmodels

import android.media.MediaRecorder
import android.util.Log
import java.io.File
import java.io.IOException

class SongRecorderViewModel(){

    private val TAG = "Song Recorder"
//    var outputFile : String
    var recorder: MediaRecorder = MediaRecorder()
//    var songFolder: String

    init {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
    }

    fun startRecorder(audioFile: File) {
        try {
            recorder.setOutputFile(audioFile.absolutePath)
            recorder.prepare()
            recorder.start()
        } catch (e: IllegalStateException) {
            Log.e(TAG, "Failed recording sorry :( : $e")
        }
         catch (e: IOException) {
            Log.e(TAG, "Failed preparing the recorder : $e")
        }
    }

    fun stopRecorder() {
            recorder.stop()
            recorder.release()
    }

}