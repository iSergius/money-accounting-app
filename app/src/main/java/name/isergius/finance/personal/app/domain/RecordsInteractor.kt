package name.isergius.finance.personal.app.domain

import io.reactivex.Flowable
import name.isergius.finance.personal.app.model.Record

/**
 * @author Sergey Kondratyev
 */
interface RecordsInteractor {
    fun getData(count: Int): Flowable<List<Record>>
}