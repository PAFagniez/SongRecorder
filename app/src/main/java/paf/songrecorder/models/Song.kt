package paf.songrecorder.models

import java.io.Serializable

data class Song(var title: String,
                var date: String?,
//                var image: ImageView,
                var songFolder: String,
                var trackList: ArrayList<Track> = ArrayList()
//                var tracks: MutableLiveData<List<Track>> = MutableLiveData()
)
    : Serializable {

    fun addTrack(track: Track) {
        trackList.add(track)
    }
}