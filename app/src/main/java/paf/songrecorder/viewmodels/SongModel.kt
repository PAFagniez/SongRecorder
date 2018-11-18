package paf.songrecorder.viewmodels

import io.reactivex.Observable
import paf.songrecorder.helpers.FileHelper
import paf.songrecorder.models.Song


class SongModel(private var rootFolderPath: String) {

    private var songList: ArrayList<Song> = ArrayList()
    private lateinit var songListObservable: Observable<List<Song>>

    private fun createSongList() {
        val songFolderList = FileHelper.getDirectoryListSortedByLastModified(rootFolderPath)

        songFolderList?.forEach { songFolder ->
            val song = Song(
                    songFolder.name,
                    songFolder.lastModified().toString(),
                    "$rootFolderPath/${songFolder.name}"
            )

            val trackController = TrackModel(song)
            song.trackList = trackController.getListOfTracks()
            songList.add(song)
        }
    }

        private fun testCreateSongList(songs: MutableList<Song>) {
            val songFolderList = FileHelper.getDirectoryListSortedByLastModified(rootFolderPath)

            songFolderList?.forEach { songFolder ->
                val song = Song(
                        songFolder.name,
                        songFolder.lastModified().toString(),
                        "$rootFolderPath/${songFolder.name}"
                )

                val trackController = TrackModel(song)
                song.trackList = trackController.getListOfTracks()
                songList.add(song)
                songs.add(song)
            }

//        songListObservable = Observable.create { emitter: ObservableEmitter<List<Song>> ->
//            try {
//                emitter.onNext(songList)
//            } catch (e: Exception) {
//                emitter.onError(e)
//            }
//        }
    }

    fun testGetListOfSongs() : Observable<List<Song>> {
//        SystemClock.sleep(8000)
//        createSongList()
//        return songListObservable
        return Observable.create{
            subscriber ->
            val songs = mutableListOf<Song>()
            testCreateSongList(songs)
            subscriber.onNext(songs)
            subscriber.onComplete()
        }
    }

    fun getListOfSongs() : ArrayList<Song> {
        createSongList()
        return songList
    }
}