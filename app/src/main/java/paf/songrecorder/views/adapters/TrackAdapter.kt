package paf.songrecorder.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import paf.songrecorder.databinding.TrackBinding
import paf.songrecorder.models.Track
import paf.songrecorder.viewmodels.TrackModel
import paf.songrecorder.views.viewholders.TrackBindingViewHolder

class TrackAdapter(private var trackList: ArrayList<Track>) : RecyclerView.Adapter<TrackBindingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val trackBinding = TrackBinding.inflate(inflater, parent, false)
        return TrackBindingViewHolder(trackBinding)
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    override fun onBindViewHolder(holder: TrackBindingViewHolder, position: Int) {

        val currentTrack = trackList[position]
        holder.bind(currentTrack)
    }

    fun update(tracks: ArrayList<Track>) {
        trackList.clear()
        trackList.addAll(tracks)
        notifyDataSetChanged()
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
//            val songPlayerViewModel = track!!.audioFile?.let { SongPlayerController(it) }
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
