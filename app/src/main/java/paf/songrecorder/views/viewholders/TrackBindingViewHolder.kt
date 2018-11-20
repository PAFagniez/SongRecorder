package paf.songrecorder.views.viewholders

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import paf.songrecorder.databinding.TrackBinding
import paf.songrecorder.models.Track
import paf.songrecorder.viewmodels.SongPlayerController

class TrackBindingViewHolder(private val binding: TrackBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var track: Track

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        Log.d("RecyclerView", "CLICK!")
        val songPlayerViewModel = SongPlayerController()
        songPlayerViewModel.startPlayer(track.audioFile)
    }

    fun bind (track: Track) {
        this.track = track
        binding.track = track
        binding.executePendingBindings()
    }
}