package name.isergius.finance.personal.app.components.rxList

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ReactiveViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract var currentItem: T?
}