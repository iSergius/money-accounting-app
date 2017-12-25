package name.isergius.finance.personal.app

import io.reactivex.Flowable

/**
 * @author Sergey Kondratyev
 */
interface DataList<out T, C> {
    fun size(): Int
    operator fun get(index: Int): Record
    fun getConfig(): Flowable<C>
}