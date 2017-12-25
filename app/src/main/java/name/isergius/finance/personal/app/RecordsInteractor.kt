package name.isergius.finance.personal.app

import io.reactivex.Flowable

/**
 * @author Sergey Kondratyev
 */
interface RecordsInteractor {
    fun getAll(): Flowable<Record>
}