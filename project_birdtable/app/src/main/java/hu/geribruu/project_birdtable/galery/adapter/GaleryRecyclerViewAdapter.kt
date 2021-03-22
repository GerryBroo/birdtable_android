package hu.geribruu.project_birdtable.galery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import hu.geribruu.project_birdtable.R
import hu.geribruu.project_birdtable.galery.model.Bird
import kotlinx.android.synthetic.main.layout_galery_listitem.view.*

class GaleryRecyclerViewAdapter : RecyclerView.Adapter<GaleryRecyclerViewAdapter.ViewHolder>() {

    private val birdList = mutableListOf<Bird>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_galery_listitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bird = birdList[position]

        holder.bird = bird

        holder.tvBirdName.text = bird.name
    }

    override fun getItemCount() = birdList.size

    fun addAll(birds : List<Bird>) {
        val size = birdList.size
        birdList += birds
        notifyItemRangeInserted(size, birds.size)
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val tvBirdName : TextView = view.tv_galery_item

        var bird : Bird? = null
    }
}