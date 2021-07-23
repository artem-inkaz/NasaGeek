package ui.smartpro.nasageek.mars.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.style.BackgroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.main_fragment.*
import ui.smartpro.nasageek.R
import ui.smartpro.nasageek.databinding.MarsViewHolderBinding
import ui.smartpro.nasageek.interfaces.OnItemViewClickListener
import ui.smartpro.nasageek.mars.api.MarsModel
import kotlin.coroutines.coroutineContext

// Для вывода просто списком
class MarsAdapter(
        private var marsclickListener: OnItemViewClickListener
) :
    RecyclerView.Adapter<MarsAdapter.MarsListViewHolder>() {

    private var marsData: List<MarsModel> = listOf()
    private lateinit var binding: MarsViewHolderBinding

    private var url: String? = null

    private var isExpanded = false

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

        holder.itemView.setOnClickListener {
            marsclickListener.onItemViewClick(marsData[position])
        }
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
                // Coil
//            imageView.load(url) {
////                lifecycle(itemView.context)
//                error(R.drawable.ic_load_error_vector)
//                placeholder(R.drawable.ic_no_photo_vector)
//            }

//            datePhoto.text = marsModel.earth_date
            // Добавили раскраску Spannable
            datePhoto.text = marsModel.earth_date?.let { spanText(it) }

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

    private fun spanText(text: String): Spannable {
        val spannable = text.toSpannable()
        spannable.setSpan(
                BackgroundColorSpan(Color.RED),
                0, 10,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                8, spannable.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }
}
