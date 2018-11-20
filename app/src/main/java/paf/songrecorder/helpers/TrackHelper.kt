package paf.songrecorder.helpers

import android.arch.lifecycle.ViewModel
import paf.songrecorder.models.Song
import paf.songrecorder.models.Track
import paf.songrecorder.viewmodels.SongModel
import paf.songrecorder.viewmodels.TrackModel
import java.io.File


class TrackHelper(var song: Song): ViewModel() {



    companion object {

        private fun createTrackList(song: Song) : ArrayList<Track>{
            val trackFolderList = FileHelper
                    .getAudioFileListFromDirectorySortedByLastModified(song.songFolder)
            val trackList = ArrayList<Track>()

            trackFolderList?.forEach { trackFolder ->
                val track = Track(
                        trackFolder.name,
                        trackFolder.lastModified().toString(),
                        null,
                        File("$song.songFolder/${trackFolder.name}")
                )

                trackList.add(track)
            }

            return trackList
        }

        private fun createTrackModelList(songModel: SongModel) : ArrayList<TrackModel> {
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

        fun getListOfTrackModels(song: SongModel) : ArrayList<TrackModel>{

            val trackModelList = createTrackModelList(song)
            return trackModelList
        }

        fun getListOfTracks(song: Song) : ArrayList<Track>{
            val trackList: ArrayList<Track> = createTrackList(song)
            return trackList
        }
    }
}