package paf.songrecorder.helpers

import android.arch.lifecycle.ViewModel
import paf.songrecorder.models.Song
import paf.songrecorder.models.Track
import paf.songrecorder.viewmodels.SongModel
import paf.songrecorder.viewmodels.TrackModel
import java.io.File


class TrackHelper(var song: Song): ViewModel() {



    companion object {

        fun createTrackList(song: Song) : ArrayList<Track>{
            val audioFileList = FileHelper
                    .getAudioFileListFromDirectorySortedByLastModified(song.songFolder)
            val trackList = ArrayList<Track>()

            audioFileList?.forEach { audioFile ->
                val track = Track(
                        audioFile.name,
                        audioFile.lastModified().toString(),
                        audioFile.parent,
                        File(audioFile.absolutePath)
                )

                trackList.add(track)
            }

            return trackList
        }

        fun createTrackModelList(songModel: SongModel) : ArrayList<TrackModel> {
            val trackFolderList = FileHelper
                    .getAudioFileListFromDirectorySortedByLastModified(songModel.songFolder)
            val trackModelList = ArrayList<TrackModel>()

            trackFolderList?.forEach { trackFolder ->
                val track = Track(
                        trackFolder.name,
                        trackFolder.lastModified().toString(),
                        null,
                        File("$songModel.songFolder/${trackFolder.name}")
                )

                trackModelList.add(TrackModel(track))
            }

            return trackModelList
        }
    }
}