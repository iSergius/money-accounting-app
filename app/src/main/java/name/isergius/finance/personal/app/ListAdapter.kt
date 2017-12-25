package name.isergius.finance.personal.app

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author Sergey Kondratyev
 */
class ListAdapter(private val holderSupports: Map<Int, ItemHolderSupport>,
                  private val itemHolderDataList: DataList<ItemHolderData, ConfigList>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = this.javaClass.canonicalName

    init {
        Log.d(TAG, "init")
        itemHolderDataList.getConfig()
                .doOnNext { Log.d(TAG, "init $it") }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d(TAG, "notifyDataSetChanged()")
                    notifyDataSetChanged()
                }
        Log.d(TAG, "is init")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        Log.d(TAG, "onBindViewHolder()")
        (holder as UpdateViewHolder).bindViews(itemHolderDataList[position])
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount()")
        return itemHolderDataList.size()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder()")
        return holderSupports[viewType]!!.build()
    }
}