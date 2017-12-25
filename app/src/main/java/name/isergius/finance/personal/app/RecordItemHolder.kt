package name.isergius.finance.personal.app

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.item_record.view.*

/**
 * @author Sergey Kondratyev
 */
class RecordItemHolder(v: View) : RecyclerView.ViewHolder(v), UpdateViewHolder {

    private val amount: TextView = v.item_record_amount_lbl
    private val description: TextView = v.item_record_description_lbl
    private val edit: ImageButton = v.item_record_edit_btn

    override fun bindViews(data: ItemHolderData) {
        amount.text = (data as Record).amount.toString()
        amount.setTextColor(ContextCompat.getColor(itemView.context, data.type.color))
        description.text = data.description
        edit.setOnClickListener { }
    }
}