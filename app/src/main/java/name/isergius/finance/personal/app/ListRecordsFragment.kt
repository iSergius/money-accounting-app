package name.isergius.finance.personal.app

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

/**
 * @author Sergey kondratyev
 */
class ListRecordsFragment : Fragment() {

    private val TAG: String = this.javaClass.canonicalName

    private var recordsInteractor: RecordsInteractor? = null
    private var mRecordsDataList: DataList<Record, ConfigList>? = null
    private var adapter: ListAdapter? = null
    private var mListener: OnFragmentInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.v(TAG, "begin onCreateView()")
        val recordItem = ItemHolderSupport(holderBuilder = { RecordItemHolder(it) }, layout = R.layout.item_record, parent = container)
        val downloadItem = ItemHolderSupport(holderBuilder = { DownloadItemHolder(it) }, layout = R.layout.item_loading, parent = container)
        Log.v(TAG, "create items")
        recordsInteractor = (context?.applicationContext as App).getRecordsInteractor()
        val view = inflater.inflate(R.layout.fragment_list_records, container, false)
        Log.v(TAG, "inflated viev: $view")
        val listRecords = view.findViewById<RecyclerView>(R.id.list_records)
        listRecords.layoutManager = LinearLayoutManager(container?.context)
        mRecordsDataList = RecordsDataList(recordsInteractor!!.getAll(), listRecords.getFlowableScroll())
        Log.d(TAG, "mRecordsDataList $mRecordsDataList")
        listRecords.adapter = ListAdapter(mapOf(Pair(0, recordItem), Pair(1, downloadItem)), mRecordsDataList!!)

        view.findViewById<ImageButton>(R.id.add_record_btn).setOnClickListener { Log.d(TAG, "onClick add_record_btn") }
        Log.v(TAG, "before return onCreateView()")
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
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
