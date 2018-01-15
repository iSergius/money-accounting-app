package name.isergius.finance.personal.app

import android.app.Application
import android.util.Log
import name.isergius.finance.personal.app.data.RecordsInteractorMemory
import name.isergius.finance.personal.app.domain.RecordsInteractor

/**
 * @author Sergey Kondratyev
 */
class App : Application() {

    private val TAG: String = this.javaClass.canonicalName

    fun getRecordsInteractor(): RecordsInteractor {
        Log.v(TAG, "getRecordsInteractor()")
        return RecordsInteractorMemory()
    }

}