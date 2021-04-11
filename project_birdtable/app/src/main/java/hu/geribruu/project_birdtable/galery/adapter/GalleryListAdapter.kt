package hu.geribruu.project_birdtable.galery.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import hu.geribruu.project_birdtable.R
import hu.geribruu.project_birdtable.database.model.Bird
import kotlinx.android.synthetic.main.layout_galery_listitem.view.*
import java.io.File

class GalleryListAdapter : ListAdapter<Bird, GalleryListAdapter.GalleryViewHolder>(BIRDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvBirdName : TextView = itemView.tv_name_galery_item
        private val tvBirdCaptureDate : TextView = itemView.tv_date_galery_item
        private val tvBirdUrl : TextView = itemView.tv_url_galery_item
        private val imgBird : CircleImageView = itemView.img_galery_item

        fun bind(bird: Bird) {
            tvBirdName.text = bird.name
            tvBirdCaptureDate.text = bird.captureDate
            tvBirdUrl.text = bird.imageUrl

            val imgFile = File(bird.imageUrl)
            if (imgFile.exists()) {
                val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                imgBird.setImageBitmap(myBitmap)
            }
        }

        companion object {
            fun create(parent: ViewGroup): GalleryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_galery_listitem, parent, false)
                return GalleryViewHolder(view)
            }
        }
    }

    companion object {
        private val BIRDS_COMPARATOR = object : DiffUtil.ItemCallback<Bird>() {
            override fun areItemsTheSame(oldItem: Bird, newItem: Bird): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Bird, newItem: Bird): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}