package name.isergius.finance.personal.app

/**
 * @author Sergey Kondratyev
 */
data class Record(val amount: MoneyAmount,
                  val description: String,
                  val type: Type) {

    enum class Type(val color: Int) {
        EXPENSE(R.color.expense),
        INCOME(R.color.income)
    }

}
