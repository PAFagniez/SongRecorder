package paf.songrecorder.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import paf.songrecorder.databinding.SongBinding
import paf.songrecorder.viewmodels.SongModel
import paf.songrecorder.views.viewholders.SongBindingViewHolder





class SongAdapter(private var songList: ArrayList<SongModel>) : RecyclerView.Adapter<SongBindingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val songBinding = SongBinding.inflate(inflater, parent, false)
        return SongBindingViewHolder(songBinding)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: SongBindingViewHolder, position: Int) {

        val currentSong = songList[position]
        holder.bind(currentSong)
    }

    fun update(songs: ArrayList<SongModel>) {
        songList.clear()
        songList.addAll(songs)
        notifyDataSetChanged()
    }

    fun setData(songModelList: ArrayList<SongModel>) {
        this.songList.clear()
        songList.addAll(songModelList)
        notifyDataSetChanged()
    }
}
