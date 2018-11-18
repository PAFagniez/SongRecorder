//package paf.songrecorder.views.adapters
//
//import android.support.v7.widget.ForwardingListener
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import paf.songrecorder.data.Track
//
//class TestBindingTrackAdapter(var dataList: List<Track>): RecyclerView.Adapter<TestBindingTrackAdapter.BindingHolder>() {
//
//    lateinit var listener: OnItemClickListener
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestBindingTrackAdapter.BindingHolder {
//        setOnItemClickListener(listener)
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = Origina
//    }
//
//    override fun getItemCount(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun onBindViewHolder(holder: TestBindingTrackAdapter.BindingHolder, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    interface OnItemClickListener{
//        fun onClick(view: View, track: Track)
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener) {
//        this.listener = listener
//    }
//
//    class BindingHolder(var binding: OriginalItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
//}