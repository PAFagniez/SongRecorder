package paf.songrecorder.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import paf.songrecorder.models.Track
import paf.songrecorder.databinding.ItemTrackBinding
import paf.songrecorder.views.viewholders.TrackBindingViewHolder

class TrackAdapter(private var trackList: List<Track>) : RecyclerView.Adapter<TrackBindingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val trackBinding = ItemTrackBinding.inflate(inflater, parent, false)
        return TrackBindingViewHolder(trackBinding)
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    override fun onBindViewHolder(holder: TrackBindingViewHolder, position: Int) {

        val currentTrack = trackList[position]
        holder.bind(currentTrack)
    }

//    inner class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
//
//        private var view: View = itemView
//        private var track: Track? = null
//
//        init {
//            itemView.setOnClickListener(this)
//        }
//
//        override fun onClick(view: View?) {
//            Log.d("RecyclerView", "CLICK!")
//            val songPlayerViewModel = track!!.audioFile?.let { SongPlayerViewModel(it) }
//            if (songPlayerViewModel != null) {
//                songPlayerViewModel.startPlayer()
//            }
//        }
//
//        fun bindTrack (track: Track) {
//            this.track = track
//            view.trackTitleText.text = track.title
//            view.trackDateText.text = track.date
//        }
//
//    }
}
