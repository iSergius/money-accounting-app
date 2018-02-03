package name.isergius.finance.personal.app.ui

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import name.isergius.finance.personal.app.components.rxList.ReactiveViewHolderFactory
import name.isergius.finance.personal.app.domain.RecordsInteractor
import name.isergius.finance.personal.app.model.Record
import org.reactivestreams.Subscriber

/**
 * @author Sergey Kondratyev
 */
class ListRecordsPresenterImpl(val interactor: RecordsInteractor)
    : ListRecordsPresenter {
    private val TAG: String = javaClass.simpleName

    override fun recordsData(subscriber: Subscriber<List<Record>>) {
        interactor.getData(10)
                .observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(subscriber)
    }

    override fun addButtonClicks(clicks: Observable<Any>): Disposable {
        Log.d(TAG, "addButtonClicks")
        return clicks.subscribe { Log.d(TAG, "addButtonClicks Click") }
    }

    override fun editButtonClicks(clicks: Observable<in ReactiveViewHolderFactory.Event<Record>>): Disposable {
        Log.d(TAG, "editButtonClicks")
        return clicks.subscribe { Log.d(TAG, "editButtonClicks Click $it") }
    }
}