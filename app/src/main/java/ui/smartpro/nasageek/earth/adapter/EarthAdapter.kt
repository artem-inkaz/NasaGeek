package ui.smartpro.nasageek.earth.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ui.smartpro.nasageek.R
import ui.smartpro.nasageek.databinding.EarthViewHolderBinding
import ui.smartpro.nasageek.earth.api.EarthApiDto
import ui.smartpro.nasageek.earth.api.EarthModel

// Для вывода просто списком
class EarthAdapter() :
    RecyclerView.Adapter<EarthAdapter.EarthListViewHolder>() {

    private var earthData: List<EarthModel> = listOf()
    private lateinit var binding: EarthViewHolderBinding

    private var url: String? = null

    //    fun bindMovie(data: List<MarsModel>) {
    fun setMars(data: List<EarthModel>) {
        earthData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EarthAdapter.EarthListViewHolder {
        binding =
            EarthViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EarthListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: EarthAdapter.EarthListViewHolder, position: Int) {
       holder.bind(earthData[position])
    }

    override fun getItemCount(): Int {
        return earthData.size
    }

    inner class EarthListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //        companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_no_photo_vector)
            .fallback(R.drawable.ic_no_photo_vector)
            .centerCrop()
//        }

        @SuppressLint("SetTextI18n")
        fun bind(earthModel: EarthModel) = with(binding) {
            Glide.with(itemView.context)
                .load(earthModel.imagePath)
                .apply(imageOption)
                .into(imageView)

//            imageView.load(url) {
////                lifecycle(itemView.context)
//                error(R.drawable.ic_load_error_vector)
//                placeholder(R.drawable.ic_no_photo_vector)
//            }
            datePhoto.text = earthModel.date
        }
    }
}
