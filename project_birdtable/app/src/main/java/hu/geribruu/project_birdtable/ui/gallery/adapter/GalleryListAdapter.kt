package hu.geribruu.project_birdtable.ui.gallery.adapter

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
import kotlinx.android.synthetic.main.layout_gallery_listitem.view.*
import java.io.File

class GalleryListAdapter(private val onClick : BirdClickListener) : ListAdapter<Bird, GalleryListAdapter.GalleryViewHolder>(BIRDS_COMPARATOR) {

    private lateinit var current : Bird

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder.create(parent, onClick)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        current = getItem(position)
        holder.bind(current, position)
    }

    class GalleryViewHolder(itemView: View, private val onClick: BirdClickListener) : RecyclerView.ViewHolder(itemView) {
        private val tvBirdName : TextView = itemView.tv_name_gallery_item
        private val tvBirdCaptureDate : TextView = itemView.tv_date_gallery_item
        private val imgBird : CircleImageView = itemView.img_gallery_item

        fun bind(bird: Bird, position: Int) {
            tvBirdName.text = bird.name
            tvBirdCaptureDate.text = bird.captureDate

            val imgFile = File(bird.imageUrl)
            if (imgFile.exists()) {
                val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                imgBird.setImageBitmap(myBitmap)
            }

            itemView.setOnClickListener {
                onClick.onClick(bird.id)
            }
        }

        companion object {
            fun create(parent: ViewGroup, onClick: BirdClickListener): GalleryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_gallery_listitem, parent, false)
                return GalleryViewHolder(view, onClick)
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

    interface BirdClickListener {
        fun onClick(id: Long)
    }
}