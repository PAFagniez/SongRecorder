//package paf.songrecorder.views.adapters
//
//import android.databinding.DataBindingUtil
//import android.databinding.ViewDataBinding
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import paf.songrecorder.data.Track
//import paf.songrecorder.viewmoddels.TrackHelper
//
//class TestTrackAdapter(val layoutId: Int, val viewModel: TrackHelper):
//        RecyclerView.Adapter<TestTrackAdapter.GenericViewHolder>() {
//
//    private lateinit var tracks: List<Track>
//
//    private fun getLayoutIdForPosition(position : Int): Int {
//        return layoutId
//    }
//
//    override fun getItemCount(): Int {
//        return tracks.size
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
//
//        return GenericViewHolder(binding);
//    }
//
//    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
////        holder.(viewModel, position)
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return getLayoutIdForPosition(position)
//    }
//
//    fun setTracks(tracks: List<Track>) {
//        this.tracks = tracks
//    }
//
//    class GenericViewHolder(private val binding: ViewDataBinding, itemView: View):
//            RecyclerView.ViewHolder(itemView) {
//
//        val itemView = itemView
//
//        fun bind(viewModel: TrackHelper, position: Int) {
////            with(binding)
//        }
//
//    }
//}