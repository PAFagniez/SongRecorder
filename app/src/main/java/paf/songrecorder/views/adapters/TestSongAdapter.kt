package paf.songrecorder.views.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import paf.songrecorder.R
import paf.songrecorder.models.Song
import paf.songrecorder.views.SongActivity


class TestSongAdapter: RecyclerView.Adapter<TestSongAdapter.ViewHolder>() {

    companion object {
        private const val SONG_KEY = "SONG"
    }

    private lateinit var context: Context
    private val songList = ArrayList<Song>()

    fun setSongs(newSongs : List<Song>){
        songList.clear()
        songList.addAll(newSongs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSong = songList[position]
        holder.bindSong(currentSong)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var view: View = itemView
        private var song: Song? = null

        override fun onClick(view: View?) {
            Log.d("RecyclerView", "CLICK!")
            val context = itemView.context
            val editSongIntent = Intent(context, SongActivity::class.java)
            editSongIntent.putExtra(SONG_KEY, song)
            context.startActivity(editSongIntent)
        }

        fun bindSong (song: Song) {
            this.song = song
//            view.trackTitleText.text = song.title
//            view.trackDateText.text = song.date
        }
    }
}