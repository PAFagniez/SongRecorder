package paf.songrecorder.viewmodels

import android.databinding.BaseObservable
import android.databinding.Bindable
import paf.songrecorder.BR
import paf.songrecorder.helpers.DateHelper
import paf.songrecorder.models.Song
import paf.songrecorder.models.Track
import java.io.Serializable

class SongModel(val song: Song): BaseObservable(), Serializable {

    @Bindable
    var title = song.title
        set(title) {
            field = title
            date = DateHelper.getCurrentDateAndTimeAsString()
            notifyPropertyChanged(BR.title)
        }
    @Bindable
    var date = song.date
    //                var image: ImageView,
    @Bindable
    var songFolder = song.songFolder
    @Bindable
    var trackList = song.trackList

    fun addNewTrackToTrackList(track: Track){
        trackList.add(track)
    }
}