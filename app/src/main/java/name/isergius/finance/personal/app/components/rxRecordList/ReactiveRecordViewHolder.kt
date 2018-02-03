package name.isergius.finance.personal.app.components.rxRecordList

import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import kotlinx.android.synthetic.main.item_record.view.*
import name.isergius.finance.personal.app.R
import name.isergius.finance.personal.app.components.rxList.ReactiveViewHolder
import name.isergius.finance.personal.app.components.rxList.ReactiveViewHolderFactory
import name.isergius.finance.personal.app.model.Record


/**
 * @author Sergey Kondratyev
 */
class ReactiveRecordViewHolder(itemView: View) : ReactiveViewHolder<Record>(itemView) {
    private val TAG: String = this.javaClass.simpleName

    private val amount: TextView = itemView.item_record_amount_lbl

    private val description: TextView = itemView.item_record_description_lbl
    private val edit: ImageButton = itemView.item_record_edit_btn
    override var events: Observable<Int> = RxView.clicks(edit)
            .map { edit.id }
    override var currentItem: Record? = null
        set(@NonNull value) {
//            Log.d(TAG, "set(currentItem: $value)")
            field = value
            amount.text = value!!.amount.toString()
            amount.setTextColor(ContextCompat.getColor(itemView.context, value.type.color))
            description.text = value.description
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
