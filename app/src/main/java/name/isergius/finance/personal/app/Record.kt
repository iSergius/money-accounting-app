package name.isergius.finance.personal.app

/**
 * @author Sergey Kondratyev
 */
data class Record(val amount: MoneyAmount,
                  val description: String,
                  val type: Type)
    : ItemHolderData() {

    enum class Type(val color: Int) {
        EXPENSE(R.color.expenseColor),
        INCOME(R.color.incomeColor)
    }

}
