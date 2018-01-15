package name.isergius.finance.personal.app.model


import name.isergius.finance.personal.app.R
import java.util.*

/**
 * @author Sergey Kondratyev
 */
data class Record(val amount: MoneyAmount,
                  val description: String,
                  val type: Type,
                  val creation: Date = Date()) {

    enum class Type(val color: Int) {
        EXPENSE(R.color.expenseColor),
        INCOME(R.color.incomeColor)
    }

}
