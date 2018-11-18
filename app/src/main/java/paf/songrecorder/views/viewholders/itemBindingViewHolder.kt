package paf.songrecorder.views.viewholders

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import paf.songrecorder.databinding.ItemTrackBinding

class itemBindingViewHolder (private val binding: ItemTrackBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    override fun onClick(view: View?) {
        Log.d("RecyclerView", "CLICK!")
//        val songPlayerViewModel = SongPlayerViewModel(track.audioFile)
//        songPlayerViewModel.startPlayer()
    }

    fun bind (obj: Object) {
//        binding.setVariable(BR.obj, obj)
        binding.executePendingBindings()
    }
}