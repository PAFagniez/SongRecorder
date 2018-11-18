package paf.songrecorder.views

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import paf.songrecorder.R
import paf.songrecorder.models.Track

class TestBindingTrackAdapter(trackList: List<Track>) : RecyclerView.Adapter<TestBindingTrackAdapter.TrackViewHolder>(){

    companion object {
        private val TRACK_KEY = "TRACK"
    }

    private var trackList: List<Track> = trackList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val inflatedView = inflater.inflate(R.layout.item_song, parent, false)
        return TrackViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
//        val currentSong = trackList.get(position)
        val currentTrack = trackList[position]
        holder.bindTrack(currentTrack)
    }

    inner class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

//        private val binding : Item
        private var view: View = itemView
        private var track: Track? = null

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            Log.d("RecyclerView", "CLICK!")
            val context = itemView.context
            val editSongIntent = Intent(context, SongActivity::class.java)
            editSongIntent.putExtra(TRACK_KEY, track)
            context.startActivity(editSongIntent)
        }

        fun bindTrack (track: Track) {
            this.track = track
//            view.trackTitleText.text = track.title
//            view.trackDateText.text = track.date
        }

    }
}
