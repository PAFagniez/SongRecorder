package paf.songrecorder.helpers

import paf.songrecorder.models.Song
import paf.songrecorder.viewmodels.SongModel


class SongHelper(private var rootFolderPath: String) {

    companion object {

        fun getSongModelList(rootFolderPath: String) : ArrayList<SongModel> {
            val songFolderList = FileHelper.getDirectoryListSortedByLastModified(rootFolderPath)
            val songList: ArrayList<SongModel> = ArrayList()

            songFolderList?.forEach { songFolder ->
                val song = Song(
                        title = songFolder.name,
                        date = DateHelper.convertDateToReadableDateAndTime(
                                songFolder.lastModified()
                        ),
                        songFolder = "$rootFolderPath${songFolder.name}"
                )

//                song.trackList = TrackHelper.getListOfTracks(song)
                songList.add(SongModel(song))
            }
            return songList
        }

        fun getSongList(rootFolderPath: String) : ArrayList<Song> {
            val songFolderList = FileHelper.getDirectoryListSortedByLastModified(rootFolderPath)
            val songList: ArrayList<Song> = ArrayList()

            songFolderList?.forEach { songFolder ->
                val song = Song(
                        title = songFolder.name,
                        date = DateHelper.convertDateToReadableDateAndTime(
                                songFolder.lastModified()
                        ),
                        songFolder = "$rootFolderPath${songFolder.name}"
                )

//                song.trackList = TrackHelper.getListOfTracks(song)
                songList.add(song)
            }
            return songList
        }
    }
}