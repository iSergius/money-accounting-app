package name.isergius.finance.personal.app.util

import android.content.Context
import android.widget.Toast

/**
 * @author Sergey Kondratyev
 */
class ToastService(private var context: Context? = null) {

    fun attach(context: Context) {
        this.context = context
    }

    fun notify(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}