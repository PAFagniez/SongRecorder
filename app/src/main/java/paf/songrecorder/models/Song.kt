package paf.songrecorder.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(var title: String,
                var date: String?,
//                var image: ImageView,
                var songFolder: String,
                var trackList: ArrayList<Track> = ArrayList()
//                var tracks: MutableLiveData<List<Track>> = MutableLiveData()
    ) : Parcelable {

    fun addTrack(track: Track) {
        trackList.add(track)
    }

}