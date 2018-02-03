package name.isergius.finance.personal.app.components.rxList

import android.support.annotation.IdRes
import android.view.View
import android.view.ViewGroup

/**
 * @author Sergey Kondratyev
 */
interface ReactiveViewHolderFactory<T> {
    class ViewAndHolder<T>(val view: View, val viewHolder: ReactiveViewHolder<T>)
    data class Event<out T>(@IdRes val viewId: Int, val item: T)

    fun createViewAndHolder(parent: ViewGroup, pViewType: Int): ViewAndHolder<T>
}