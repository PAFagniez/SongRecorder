package paf.songrecorder.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import paf.songrecorder.helpers.FileHelper
import paf.songrecorder.models.Song
import paf.songrecorder.models.Track
import paf.songrecorder.views.adapters.TrackAdapter
import java.io.File


class TrackModel(var song: Song): ViewModel() {

    private lateinit var adapter: TrackAdapter
    private lateinit var selected: MutableLiveData<Track>
//    private lateinit var loading: ObservableInt
//    private lateinit var showEmpty: ObservableInt

    private var trackList: ArrayList<Track> = ArrayList()
    private var songFolderPath: String = song.songFolder

    private fun createTrackList() {
        val trackFolderList = FileHelper.getFileListFromDirectorySortedByLastModified(songFolderPath)

        trackFolderList?.forEach { trackFolder ->
            val track = Track(
                    trackFolder.name,
                    trackFolder.lastModified().toString(),
                    null,
                    File("$songFolderPath/${trackFolder.name}")
            )

            trackList.add(track)
        }
    }

    fun getListOfTracks() : ArrayList<Track>{
        createTrackList()
        return trackList
    }
}