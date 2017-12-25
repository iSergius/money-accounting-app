package name.isergius.finance.personal.app

import android.support.v7.widget.RecyclerView
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

/**
 * @author Sergey Kondratyev
 */

fun RecyclerView.getFlowableScroll(): Flowable<Int> {
    return Flowable.create<Int>({ subscriber ->
        val sl = object : RecyclerView.OnScrollListener() {
            var loadData: Boolean = true
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (loadData) loadData = false
                if (subscriber.isCancelled || loadData) {
                    println("!!!!! emit loading with offset: ${recyclerView?.adapter?.itemCount ?: 0}")
                    subscriber.onNext(recyclerView?.adapter?.itemCount ?: 0)
                }
            }
        }
        subscriber.onNext(0)
        println("!!!! start")
        this.addOnScrollListener(sl)
    }, BackpressureStrategy.BUFFER)
            .cache()
}

