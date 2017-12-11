package name.isergius.finance.personal.app

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

/**
 * @author Sergey Kondratyev
 */
data class MoneyAmount(val amount: BigDecimal,
                       val currency: String) {
    override fun toString(): String {
        val locale = Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build()
        val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
        return currencyFormatter.format(amount)
    }
}