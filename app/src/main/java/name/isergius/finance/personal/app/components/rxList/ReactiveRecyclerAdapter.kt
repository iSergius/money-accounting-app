package name.isergius.finance.personal.app.components.rxList

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.subjects.PublishSubject
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * @author Sergey Kondratyev
 */
class ReactiveRecyclerAdapter<T>(private val viewHolderFactory: ReactiveViewHolderFactory<T>)
    : RecyclerView.Adapter<ReactiveViewHolder<T>>() {

    private val TAG: String = javaClass.simpleName

    private val mViewClickSubject = PublishSubject.create<ReactiveViewHolderFactory.Event<T>>()
    private var sData: Subscription? = null
    private var currentList: MutableList<T> = arrayListOf()

    val viewClickedObservable: Observable<in ReactiveViewHolderFactory.Event<T>>
        get() = mViewClickSubject
    var disposable: Disposable? = null
    var subscriber: Subscriber<List<T>> = object : Subscriber<List<T>> {
        override fun onNext(t: List<T>) {
//            Log.d(TAG, "onNext " + t)
            val size = currentList.size - 1
            currentList.addAll(t)
            notifyItemRangeInserted(size, t.size)
        }
        override fun onComplete() {}
        override fun onError(t: Throwable?) {}
        override fun onSubscribe(s: Subscription) {
//            Log.d(TAG, "onSubscribe")
            sData = s
            disposable = Disposables.fromSubscription(s)
            s.request(1)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, pViewType: Int): ReactiveViewHolder<T> {
//        Log.d(TAG, "onCreateViewHolder($pViewType)")
        val viewAndHolder = viewHolderFactory.createViewAndHolder(parent, pViewType)
        val viewHolder = viewAndHolder.viewHolder
        viewHolder.events
                .takeUntil(RxView.detaches(parent)) //TODO try move this code to viewHolder
                .map { ReactiveViewHolderFactory.Event(it, viewHolder.currentItem!!) }
                .subscribe(mViewClickSubject)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ReactiveViewHolder<T>, position: Int) {
//        Log.d(TAG, "onBindViewHolder($position)")
        val item = currentList[position]
        holder.currentItem = item
        if (position == currentList.size - 1) {
            sData?.request(1)
        }
    }

    override fun getItemCount(): Int {
//        Log.d(TAG, "getItemCount(): ${currentList.size}")
        return currentList.size
    }


}