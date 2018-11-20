package paf.songrecorder.views.viewholders

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import paf.songrecorder.databinding.SongBinding
import paf.songrecorder.viewmodels.SongModel
import paf.songrecorder.views.SongActivity

class SongBindingViewHolder(private val binding: SongBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener  {

    companion object {
        private const val SONG_KEY = "SONG"
    }

    private lateinit var songModel: SongModel

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        Log.d("RecyclerView", "CLICK!")
        val context = itemView.context
        val editSongIntent = Intent(context, SongActivity::class.java)
        editSongIntent.putExtra(SONG_KEY, songModel)
        context.startActivity(editSongIntent)
    }

    fun bind (songModel: SongModel) {
        this.songModel = songModel
        binding.song = songModel
        binding.executePendingBindings()
    }

}