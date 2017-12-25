package name.isergius.finance.personal.app

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author Sergey Kondratyev
 */
abstract class BaseItemHolder<in D>(v: View) : RecyclerView.ViewHolder(v) {
    abstract fun bind(data: D)
}