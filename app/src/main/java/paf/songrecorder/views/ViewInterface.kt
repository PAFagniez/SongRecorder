package paf.songrecorder.views

import paf.songrecorder.models.Track
import java.io.File

interface ViewInterface {

    fun startSongEditingActivity(title: String, dateTime: String, trackList: List<Track>?, rootFolder: File)

//    fun startTrackEditingActivity(title: String, dateTime: String)

    fun setupAdapterAndView(listOfSongs: List<Track>)

    fun addNewListItemToView(newItem: Track)
}