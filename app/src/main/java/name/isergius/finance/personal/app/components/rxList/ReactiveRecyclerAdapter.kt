package name.isergius.finance.personal.app.components.rxList

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * @author Sergey Kondratyev
 */
class ReactiveRecyclerAdapter<T>(private val viewHolderFactory: ReactiveViewHolderFactory<T>)
    : RecyclerView.Adapter<ReactiveViewHolder<T>>() {

    private val TAG: String = this.javaClass.canonicalName

    private val mViewClickSubject = PublishSubject.create<T>()
    val viewClickedObservable: Observable<in T>
        get() = mViewClickSubject

    private var sData: Subscription? = null
    private var currentList: MutableList<T> = arrayListOf()
    var data: Subscriber<List<T>> = object : Subscriber<List<T>> {
        override fun onNext(t: List<T>) {
            Log.d(TAG, "onNext " + t)
            val size = currentList.size - 1
            currentList.addAll(t)
            notifyItemRangeInserted(size, t.size)
        }

        override fun onComplete() {}
        override fun onError(t: Throwable?) {}
        override fun onSubscribe(s: Subscription?) {
            Log.d(TAG, "onSubscribe")
            sData = s
            s?.request(1)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, pViewType: Int): ReactiveViewHolder<T> {
        Log.d(TAG, "onCreateViewHolder($pViewType)")
        val viewAndHolder = viewHolderFactory.createViewAndHolder(parent, pViewType)
        val viewHolder = viewAndHolder.viewHolder

        RxView.clicks(viewAndHolder.view)
                .takeUntil(RxView.detaches(parent))
                .map { viewHolder.currentItem!! }
                .subscribe(mViewClickSubject)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ReactiveViewHolder<T>, position: Int) {
        Log.d(TAG, "onBindViewHolder($position)")
        val item = currentList[position]
        holder.currentItem = item
        if (position == currentList.size - 1) {
            sData?.request(1)
        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount(): ${currentList.size}")
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        Log.d(TAG, "getItemViewType($position): ${super.getItemViewType(position)}")
        return super.getItemViewType(position)
    }


}