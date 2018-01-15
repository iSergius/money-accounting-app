package name.isergius.finance.personal.app.components.rxRecordList

import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.item_record.view.*
import name.isergius.finance.personal.app.R
import name.isergius.finance.personal.app.components.rxList.ReactiveViewHolder
import name.isergius.finance.personal.app.components.rxList.ReactiveViewHolderFactory
import name.isergius.finance.personal.app.model.Record


/**
 * @author Sergey Kondratyev
 */
class ReactiveRecordViewHolder(itemView: View) : ReactiveViewHolder<Record>(itemView) {

    private val TAG: String = this.javaClass.canonicalName

    private val amount: TextView = itemView.item_record_amount_lbl
    private val description: TextView = itemView.item_record_description_lbl
    private val edit: ImageButton = itemView.item_record_edit_btn

    override var currentItem: Record? = null
        set(currentItem) {
            Log.d(TAG, "set(currentItem: $currentItem)")
            field = currentItem
            amount.text = currentItem!!.amount.toString()
            amount.setTextColor(ContextCompat.getColor(itemView.context, currentItem.type.color))
            description.text = currentItem.description
            edit.setOnClickListener { }

        }

    class Factory : ReactiveViewHolderFactory<Record> {

        override fun createViewAndHolder(parent: ViewGroup, pViewType: Int): ReactiveViewHolderFactory.ViewAndHolder<Record> {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false)
            return ReactiveViewHolderFactory.ViewAndHolder(
                    view,
                    ReactiveRecordViewHolder(view)
            )
        }

    }
}
