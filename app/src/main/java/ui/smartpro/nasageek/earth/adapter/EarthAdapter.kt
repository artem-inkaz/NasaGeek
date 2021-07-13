package ui.smartpro.nasageek.earth.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.earth_view_holder.*
import kotlinx.android.synthetic.main.main_activity.*
import ui.smartpro.nasageek.R
import ui.smartpro.nasageek.databinding.EarthViewHolderBinding
import ui.smartpro.nasageek.earth.api.EarthApiDto
import ui.smartpro.nasageek.earth.api.EarthModel
import ui.smartpro.nasageek.interfaces.OnItemViewClickListener

// Для вывода просто списком
class EarthAdapter(
        private var earthclickListener: OnItemViewClickListener
) :
    RecyclerView.Adapter<EarthAdapter.EarthListViewHolder>() {

    private var earthData: List<EarthModel> = listOf()
    private lateinit var binding: EarthViewHolderBinding

    private var isExpanded = false

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

        holder.itemView.setOnClickListener {
            earthclickListener.onItemViewClick(earthData[position])
        }
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

            imageView.setOnClickListener {
                isExpanded = !isExpanded
                TransitionManager.beginDelayedTransition(
                        itemView as ViewGroup, TransitionSet()
                        .addTransition(ChangeBounds())
                        .addTransition(ChangeImageTransform())
                )

                val params: ViewGroup.LayoutParams = imageView.layoutParams
                params.height =
                        if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
                imageView.layoutParams = params
                imageView.scaleType =
                        if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
            }
        }
    }
}
