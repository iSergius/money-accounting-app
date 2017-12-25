package name.isergius.finance.personal.app

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author Sergey Kondratyev
 */
class ItemHolderSupport(val layout: Int, val parent: ViewGroup?, val holderBuilder: (View) -> RecyclerView.ViewHolder) {

    fun build(): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(layout, parent, false)
        return holderBuilder(view!!)
    }
}