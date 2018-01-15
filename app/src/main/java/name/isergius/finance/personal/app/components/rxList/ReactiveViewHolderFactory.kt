package name.isergius.finance.personal.app.components.rxList

import android.view.View
import android.view.ViewGroup

/**
 * @author Sergey Kondratyev
 */
interface ReactiveViewHolderFactory<T> {
    class ViewAndHolder<T>(val view: View, val viewHolder: ReactiveViewHolder<T>)

    fun createViewAndHolder(parent: ViewGroup, pViewType: Int): ViewAndHolder<T>
}