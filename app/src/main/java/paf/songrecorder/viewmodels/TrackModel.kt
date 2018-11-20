package paf.songrecorder.viewmodels

import android.databinding.BaseObservable
import android.databinding.Bindable
import paf.songrecorder.models.Track

class TrackModel(val track: Track): BaseObservable() {
    @Bindable
    var title = track.title
    @Bindable
    var date = track.date
    @Bindable
    var rootFolder = track.rootFolder
    @Bindable
    var audioFile = track.audioFile
}