package name.isergius.finance.personal.app

import android.app.Application
import android.util.Log

/**
 * @author Sergey Kondratyev
 */
class App : Application() {

    private val TAG: String = this.javaClass.canonicalName

    private var recordsInteractor: RecordsInteractor = RecordsInteractorMemory()

    fun getRecordsInteractor(): RecordsInteractor {
        Log.v(TAG, "getRecordsInteractor()")
        return recordsInteractor
    }

}