package paf.songrecorder.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import paf.songrecorder.databinding.ItemSongBinding
import paf.songrecorder.models.Song
import paf.songrecorder.views.viewholders.SongBindingViewHolder



class SongAdapter(private var songList: ArrayList<Song>) : RecyclerView.Adapter<SongBindingViewHolder>(){

    companion object {
        private const val SONG_KEY = "SONG"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongBindingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val songBinding = ItemSongBinding.inflate(inflater, parent, false)
        return SongBindingViewHolder(songBinding)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: SongBindingViewHolder, position: Int) {

        val currentSong = songList[position]
        holder.bind(currentSong)
    }

    fun update(songs: ArrayList<Song>) {
        songList.clear()
        songList.addAll(songs)
        notifyDataSetChanged()
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val inflatedView = inflater.inflate(R.layout.item_song, parent, false)
//        return SongViewHolder(inflatedView)
//    }

//    override fun getItemCount(): Int {
//        return songList.size
//    }
//
//    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
//        val currentSong = songList[position]
//        holder.bindSong(currentSong)
//    }

//    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
//
//        private var view: View = itemView
//        private var song: Song? = null
//
//        init {
//            itemView.setOnClickListener(this)
//        }
//
//        override fun onClick(view: View?) {
//            Log.d("RecyclerView", "CLICK!")
//            val context = itemView.context
//            val editSongIntent = Intent(context, SongActivity::class.java)
//            editSongIntent.putExtra(SONG_KEY, song)
//            context.startActivity(editSongIntent)
//        }
//
//        fun bindSong (song: Song) {
//            this.song = song
//            view.songTitleText.text = song.title
//            view.songDateText.text = song.date
//        }
//
//    }
}
