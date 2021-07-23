package ui.smartpro.nasageek.interfaces

import ui.smartpro.nasageek.earth.api.EarthModel
import ui.smartpro.nasageek.mars.api.MarsModel

interface OnItemViewClickListener {
    fun onItemViewClick(earthModel: EarthModel)
    fun onItemViewClick(marsModel: MarsModel)
}