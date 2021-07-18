package ui.smartpro.nasageek.interfaces
// для анимации с RecyclerView
interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}