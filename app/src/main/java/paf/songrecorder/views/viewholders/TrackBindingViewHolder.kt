package paf.songrecorder.views.viewholders

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import paf.songrecorder.databinding.TrackBinding
import paf.songrecorder.viewmodels.SongPlayerController
import paf.songrecorder.viewmodels.TrackModel

class TrackBindingViewHolder(private val binding: TrackBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var trackModel: TrackModel

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        Log.d("RecyclerView", "CLICK!")
        val songPlayerViewModel = SongPlayerController()
        songPlayerViewModel.startPlayer(trackModel.audioFile)
    }

    fun bind (trackModel: TrackModel) {
        this.trackModel = trackModel
        binding.track = trackModel
        binding.executePendingBindings()
    }
}