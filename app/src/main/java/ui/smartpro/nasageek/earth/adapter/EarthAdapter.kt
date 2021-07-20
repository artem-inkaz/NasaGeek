package ui.smartpro.nasageek.earth.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.style.BackgroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.ImageView
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.earth_view_holder.*
import kotlinx.android.synthetic.main.earth_view_holder.view.*
import kotlinx.android.synthetic.main.main_activity.*
import ui.smartpro.nasageek.R
import ui.smartpro.nasageek.databinding.EarthViewHolderBinding
import ui.smartpro.nasageek.earth.api.EarthModel
import ui.smartpro.nasageek.interfaces.ItemTouchHelperAdapter
import ui.smartpro.nasageek.interfaces.ItemTouchHelperViewHolder
import ui.smartpro.nasageek.interfaces.OnItemViewClickListener
import ui.smartpro.nasageek.interfaces.OnStartDragListener
import java.util.*

// Для вывода просто списком
class EarthAdapter(
    private var earthclickListener: OnItemViewClickListener,
    private val dragListener: OnStartDragListener,
) :
    RecyclerView.Adapter<EarthAdapter.EarthListViewHolder>(), ItemTouchHelperAdapter {

    private var earthData: MutableList<EarthModel> = mutableListOf()
    private var earthData2= earthData as List<EarthModel>
    private lateinit var binding: EarthViewHolderBinding

    private var isFlag = false

    private var isExpanded = false

    private var url: String? = null

    //    fun bindMovie(data: List<MarsModel>) {
    fun setMars(data: MutableList<EarthModel>) {
        earthData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): EarthAdapter.EarthListViewHolder {
        binding =
            EarthViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EarthListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: EarthAdapter.EarthListViewHolder, position: Int) {
       holder.bind(earthData[position])

        holder.itemView.animation = loadAnimation(holder.itemView.context, R.anim.anim_recycler_one)

        holder.itemView.setOnClickListener {
            earthclickListener.onItemViewClick(earthData[position])
        }
    }

    override fun getItemCount(): Int {
        return earthData.size
    }
        // варианты
//    override fun onItemMove(fromPosition: Int, toPosition: Int) {
//        Collections.swap(earthData, fromPosition, toPosition)
////        notifyItemMoved(fromPosition, toPosition)
//
//    }
        // варианты
//    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
//        //Log.v("", "Log position" + fromPosition + " " + toPosition);
//        if (fromPosition < earthData.size && toPosition < earthData.size) {
//            if (fromPosition < toPosition) {
//                for (i in fromPosition until toPosition) {
//                    Collections.swap(earthData, i, i + 1)
//                }
//            } else {
//                for (i in fromPosition downTo toPosition + 1) {
//                    Collections.swap(earthData, i, i - 1)
//                    earthData.add(toPosition, i)
//                }
//            }
//
//            notifyItemMoved(fromPosition, toPosition)
//        }
//        return true
//    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(earthData, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(earthData, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }


    override fun onItemDismiss(position: Int) {
        earthData.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    inner class EarthListViewHolder(view: View) : RecyclerView.ViewHolder(view),
        ItemTouchHelperViewHolder {

        //        companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_no_photo_vector)
            .fallback(R.drawable.ic_no_photo_vector)
            .centerCrop()
//        }

        @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
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

//            earthTextView.text= earthModel.identifier.toString()
            // Добавили раскраску Spannable
            earthTextView.text= earthModel.identifier.let { spanText(it.toString()) }

            addItemImageView.setOnClickListener {
//                appendItem()
               addItem()
//                appendItem2()
            }

            removeItemImageView.setOnClickListener { removeItem() }

            moveItemUp.apply {
                setOnClickListener { moveUp() }
                visibility = if (layoutPosition > 1) View.VISIBLE else View.INVISIBLE
            }
            moveItemDown.apply {
                setOnClickListener { moveDown() }
                visibility = if (layoutPosition < earthData.size-1 ) View.VISIBLE else View.INVISIBLE
            }

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

            earthImageView.setOnClickListener {
                if (groupEarth.visibility == View.VISIBLE) {
                    groupEarth.visibility = View.GONE
                    isFlag= false
                } else if (groupEarth.visibility == View.GONE) {
                    groupEarth.visibility = View.VISIBLE
                    isFlag = true
                }
            }

            dragHandleImageView.setOnTouchListener { _, event ->
                if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(this@EarthListViewHolder)
                }
                false
            }
        }

        // добавление элемента
        fun appendItem() {
            earthData.add(generateItem())
            //без анимации
            notifyDataSetChanged()
        }

        fun appendItem2() {
            earthData.add(generateItem())
            notifyItemInserted(itemCount - 1)
        }

        private fun addItem() {
            earthData.add(layoutPosition, generateItem())
            notifyItemInserted(layoutPosition)
        }
        val now = Date()
        private fun generateItem() = EarthModel(100500, "", "$now", "")

        //удаление элемента
        private fun removeItem() {
            earthData.removeAt(layoutPosition)
            //без анимации
//            notifyDataSetChanged()
            notifyItemRemoved(layoutPosition)
        }

        private fun moveUp() {
            layoutPosition.takeIf { it > 1 }?.also { currentPosition ->
                earthData.removeAt(currentPosition).apply {
                    earthData.add(currentPosition - 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }

        private fun moveDown() {
            layoutPosition.takeIf { it < earthData.size - 1 }?.also { currentPosition ->
                earthData.removeAt(currentPosition).apply {
                    earthData.add(currentPosition + 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }

        private fun toggleText() {
            earthData[layoutPosition] = earthData[layoutPosition]
            notifyItemChanged(layoutPosition)
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(Color.WHITE)
        }
    }

    private fun spanText(text: String): Spannable {
        val spannable = text.toSpannable()
        spannable.setSpan(
                BackgroundColorSpan(Color.WHITE),
                0, 14,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                8, spannable.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }
}
