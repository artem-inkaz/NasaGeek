package ui.smartpro.nasageek.mars.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.main_fragment.*
import ui.smartpro.nasageek.R
import ui.smartpro.nasageek.databinding.MarsViewHolderBinding
import ui.smartpro.nasageek.mars.api.MarsModel
import kotlin.coroutines.coroutineContext

// Для вывода просто списком
class MarsAdapter() :
    RecyclerView.Adapter<MarsAdapter.MarsListViewHolder>() {

    private var marsData: List<MarsModel> = listOf()
    private lateinit var binding: MarsViewHolderBinding

    private var url: String? = null

    //    fun bindMovie(data: List<MarsModel>) {
    fun setMars(data: List<MarsModel>) {
        marsData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsListViewHolder {
        binding =
            MarsViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarsListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MarsListViewHolder, position: Int) {
       holder.bind(marsData[position])
    }

    override fun getItemCount(): Int {
        return marsData.size
    }

    inner class MarsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //        companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_no_photo_vector)
            .fallback(R.drawable.ic_no_photo_vector)
            .centerCrop()
//        }

        @SuppressLint("SetTextI18n")
        fun bind(marsModel: MarsModel) = with(binding) {
            Glide.with(itemView.context)
                .load(marsModel.img_src)
                .apply(imageOption)
                .into(imageView)

            imageView.load(url) {
//                lifecycle(itemView.context)
                error(R.drawable.ic_load_error_vector)
                placeholder(R.drawable.ic_no_photo_vector)
            }
            datePhoto.text = marsModel.earth_date
        }
    }
}
