package name.isergius.finance.personal.app.data

import io.reactivex.Flowable
import io.reactivex.rxkotlin.toFlowable
import name.isergius.finance.personal.app.domain.RecordsInteractor
import name.isergius.finance.personal.app.model.MoneyAmount
import name.isergius.finance.personal.app.model.Record
import java.math.BigDecimal

/**
 * @author Sergey Kondratyev
 */
class RecordsInteractorMemory : RecordsInteractor {
    private val TAG: String = this.javaClass.canonicalName

    val items = listOf(
            Record(MoneyAmount(BigDecimal("702.16"), "US"), "Ticket", Record.Type.EXPENSE),
            Record(MoneyAmount(BigDecimal("9230.00"), "US"), "Gifts", Record.Type.EXPENSE),
            Record(MoneyAmount(BigDecimal("167.14"), "US"), "Hotel", Record.Type.EXPENSE),
            Record(MoneyAmount(BigDecimal("20.00"), "US"), "Taxi", Record.Type.EXPENSE),
            Record(MoneyAmount(BigDecimal("1567.00"), "US"), "Ticket", Record.Type.EXPENSE),
            Record(MoneyAmount(BigDecimal("3234567.00"), "US"), "Bonus", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("12999.00"), "US"), "Gifts", Record.Type.EXPENSE),
            Record(MoneyAmount(BigDecimal("15.25"), "US"), "Breakfast", Record.Type.EXPENSE),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME),
            Record(MoneyAmount(BigDecimal("702.16"), "US"), "Ticket", Record.Type.EXPENSE),
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME)
    )

    override fun getData(count: Int): Flowable<List<Record>> {
        return items.toFlowable()
                .buffer(count)
    }
}
