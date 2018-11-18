package paf.songrecorder.views.viewholders

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import paf.songrecorder.models.Track
import paf.songrecorder.databinding.ItemTrackBinding
import paf.songrecorder.viewmodels.SongPlayerViewModel

class TrackBindingViewHolder(private val binding: ItemTrackBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var track: Track

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        Log.d("RecyclerView", "CLICK!")
        val songPlayerViewModel = SongPlayerViewModel()
        songPlayerViewModel.startPlayer(track.audioFile)
    }

    fun bind (track: Track) {
        this.track = track
        binding.track = track
        binding.executePendingBindings()
    }
}