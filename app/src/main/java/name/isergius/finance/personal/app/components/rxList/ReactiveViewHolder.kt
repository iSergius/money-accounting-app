package name.isergius.finance.personal.app.components.rxList

import android.support.v7.widget.RecyclerView
import android.view.View
import io.reactivex.Observable

abstract class ReactiveViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract var currentItem: T?
    abstract var events: Observable<Int>
}