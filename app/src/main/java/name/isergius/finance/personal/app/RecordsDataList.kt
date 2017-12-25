package name.isergius.finance.personal.app

import android.util.Log
import io.reactivex.Flowable

/**
 * @author Sergey Kondratyev
 */
class RecordsDataList(private val dataFlow: Flowable<Record>,
                      private val offsetConfig: Flowable<Int>,
                      private val pageItemsCount: Int = 10)
    : DataList<Record, ConfigList> {
    private val part: Flowable<List<Record>> = sign()
    private val data: List<Record> = data()
    private var offset: Int = 0

    val TAG: String = javaClass.canonicalName

    private fun sign(): Flowable<List<Record>> {
        return offsetConfig.flatMap {
            dataFlow.skip(it.toLong())
                    .take(pageItemsCount.toLong())
        }.buffer(pageItemsCount)
                .doOnNext { Log.d(TAG, "sign() $it") }
                .publish()
    }

    private fun data(): List<Record> {
        Log.d(TAG, "data()")
        return dataFlow.toList()
                .blockingGet()
    }

    override fun size(): Int {
        Log.v(TAG, "size():")
        val size = offset + pageItemsCount
        Log.v(TAG, "size(): $size")
        return size
    }

    override fun get(index: Int): Record {
        Log.v(TAG, "get(index $index)")
        return part.blockingLast()[index]
    }

    override fun getConfig(): Flowable<ConfigList> {
        Log.d(TAG, "getConfig()")
        part.subscribe()
        return Flowable.fromPublisher(offsetConfig)
                .doOnNext {
                    offset = it
                    Log.d(TAG, "getConfig() $it")
                }
                .map { ConfigList(it) }
    }
}
