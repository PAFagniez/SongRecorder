package paf.songrecorder.helpers

import io.reactivex.Observable
import paf.songrecorder.models.Song
import paf.songrecorder.viewmodels.SongModel


class SongHelper(private var rootFolderPath: String) {

    companion object {

        fun getSongModelList(rootFolderPath: String) : ArrayList<SongModel> {
            val songFolderList = FileHelper.getDirectoryListSortedByLastModified(rootFolderPath)
            val songList: ArrayList<SongModel> = ArrayList()

            songFolderList?.forEach { songFolder ->
                val song = Song(
                        songFolder.name,
                        songFolder.lastModified().toString(),
                        "$rootFolderPath${songFolder.name}"
                )

//                song.trackList = TrackHelper.getListOfTracks(song)
                songList.add(SongModel(song))
            }
            return songList
        }
    }

        private fun testCreateSongList(songs: MutableList<Song>) {
            val songFolderList = FileHelper.getDirectoryListSortedByLastModified(rootFolderPath)
            val songList = ArrayList<Song>()
            songFolderList?.forEach { songFolder ->
                val song = Song(
                        songFolder.name,
                        songFolder.lastModified().toString(),
                        "$rootFolderPath/${songFolder.name}/"
                )

                song.trackList = TrackHelper.getListOfTracks(song)
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
//        getSongModelList()
//        return songListObservable
        return Observable.create{
            subscriber ->
            val songs = mutableListOf<Song>()
            testCreateSongList(songs)
            subscriber.onNext(songs)
            subscriber.onComplete()
        }
    }
}