package name.isergius.finance.personal.app

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.item_record.view.*
import java.math.BigDecimal

/**
 * @author Sergey kondratyev
 */
class ListRecordsFragment : Fragment() {


    private var toastService: ToastService = ToastService()
    private val items = listOf(
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
            Record(MoneyAmount(BigDecimal("9243504.00"), "US"), "Payment for the project", Record.Type.INCOME)
    )
    private val adapter: Adapter = Adapter(toastService, RecordsData(items))
    private var mListener: OnFragmentInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_records, container, false)
        view.findViewById<RecyclerView>(R.id.list_records).adapter = adapter
        view.findViewById<RecyclerView>(R.id.list_records).layoutManager = LinearLayoutManager(container?.context)
        view.findViewById<ImageButton>(R.id.add_record_btn).setOnClickListener { toastService.notify("click add") }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            toastService.attach(context)
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    class Adapter(private val toastService: ToastService,
                  private val data: Data<Record>)
        : RecyclerView.Adapter<ItemHolder>() {

        override fun getItemCount(): Int = data.size()

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            val record = data[position]
            holder.amount.text = record.amount.toString()
            holder.amount.setTextColor(ContextCompat.getColor(holder.itemView.context, record.type.color))
            holder.description.text = record.description
            holder.edit.setOnClickListener { toastService.notify("click edit") }
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemHolder {
            return if (viewType == 0) {
                val view = LayoutInflater.from(parent?.context)
                        .inflate(R.layout.item_record, parent, false)
                ItemHolder(view)
            } else {
                val view = LayoutInflater.from(parent?.context)
                        .inflate(R.layout.item_record, parent, false)
                ItemHolder(view)
            }
        }

        override fun getItemViewType(position: Int): Int {
            return if (data.loading(position)) 1 else 0
        }
    }

    class ItemHolder(v: View) : RecyclerView.ViewHolder(v) {
        val amount: TextView = v.item_record_amount_lbl
        val description: TextView = v.item_record_description_lbl
        val edit: ImageButton = v.item_record_edit_btn
    }

    class ToastService {
        private var context: Context? = null
        fun attach(context: Context) {
            this.context = context
        }

        fun notify(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    interface Data<out T> {
        fun size(): Int
        operator fun get(index: Int): T
        fun loading(index: Int): Boolean
    }

    class RecordsData(private val list: List<Record>)
        : Data<Record> {
        override fun loading(index: Int): Boolean = index == size() - 1

        override fun size(): Int = list.size

        override fun get(index: Int): Record = list[index]
    }

    /**
     * The interface is callback
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment.
         * @return A new instance of fragment ListRecordsFragment.
         */
        fun newInstance(): ListRecordsFragment = ListRecordsFragment()
    }
}// Required empty public constructor
