package name.isergius.finance.personal.app.ui

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import name.isergius.finance.personal.app.components.rxList.ReactiveViewHolderFactory
import name.isergius.finance.personal.app.model.Record
import org.reactivestreams.Subscriber

/**
 * @author Sergey Kondratyev
 */
interface ListRecordsPresenter {

    fun recordsData(subscriber: Subscriber<List<Record>>)

    fun addButtonClicks(clicks: Observable<Any>): Disposable

    fun editButtonClicks(clicks: Observable<in ReactiveViewHolderFactory.Event<Record>>): Disposable
}