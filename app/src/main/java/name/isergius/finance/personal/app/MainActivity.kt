package name.isergius.finance.personal.app

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_record.view.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private val toastService = ToastService(this)
    private val adapter: Adapter = Adapter(toastService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list_records.adapter = adapter
        list_records.layoutManager = LinearLayoutManager(this)
        adapter.items = listOf(
                Record(MoneyAmount(BigDecimal("702.16"), "US"), "Ticket", Record.Type.EXPENSE),
                Record(MoneyAmount(BigDecimal("9230.00"), "US"), "Gifts", Record.Type.EXPENSE),
                Record(MoneyAmount(BigDecimal("167.14"), "US"), "Hotel", Record.Type.EXPENSE),
                Record(MoneyAmount(BigDecimal("20.00"), "US"), "Taxi", Record.Type.EXPENSE),
                Record(MoneyAmount(BigDecimal("1567.00"), "US"), "Ticket", Record.Type.EXPENSE),
                Record(MoneyAmount(BigDecimal("3234567.00"), "US"), "Bonus", Record.Type.INCOME),
                Record(MoneyAmount(BigDecimal("12999.00"), "US"), "Gifts", Record.Type.EXPENSE),
                Record(MoneyAmount(BigDecimal("15.25"), "US"), "Breakfast", Record.Type.EXPENSE),
                Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME)
        )
        add_record_btn.setOnClickListener { toastService.notify("click add") }
    }

    class Adapter(private val toastService: ToastService)
        : RecyclerView.Adapter<ItemHolder>() {

        var items: List<Record> = emptyList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            val record = items[position]
            holder.amount.text = record.amount.toString()
            holder.amount.setTextColor(ContextCompat.getColor(holder.itemView.context, record.type.color))
            holder.description.text = record.description
            holder.edit.setOnClickListener { toastService.notify("click edit") }
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemHolder {
            val view = LayoutInflater.from(parent?.context)
                    .inflate(R.layout.item_record, parent, false)
            return ItemHolder(view)
        }

    }

    class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {

        val amount: TextView = v.item_record_amount_lbl
        val description: TextView = v.item_record_description_lbl
        val edit: ImageButton = v.item_record_edit_btn
    }

    class ToastService(private val context: Context) {
        fun notify(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}
