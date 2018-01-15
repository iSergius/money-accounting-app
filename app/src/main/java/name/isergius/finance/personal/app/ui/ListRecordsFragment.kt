package name.isergius.finance.personal.app.ui

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
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers

import name.isergius.finance.personal.app.R
import name.isergius.finance.personal.app.components.rxList.ReactiveRecyclerAdapter
import name.isergius.finance.personal.app.components.rxRecordList.ReactiveRecordViewHolder
import name.isergius.finance.personal.app.data.RecordsInteractorMemory
import name.isergius.finance.personal.app.model.Record

/**
 * @author Sergey kondratyev
 */
class ListRecordsFragment : Fragment() {

    private val TAG: String = this.javaClass.canonicalName

    private var recordsInteractor: RecordsInteractorMemory = RecordsInteractorMemory()
    private var mAdapter: ReactiveRecyclerAdapter<Record>
            = ReactiveRecyclerAdapter(ReactiveRecordViewHolder.Factory())
    private var mListener: OnFragmentInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_records, container, false)
        val listRecords = view.findViewById<RecyclerView>(R.id.list_records)
        listRecords.layoutManager = LinearLayoutManager(container?.context)
        listRecords.adapter = mAdapter
        recordsInteractor.getData(10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mAdapter.data)
        RxView.clicks(view.findViewById<ImageButton>(R.id.add_record_btn))
                .map { Log.d(TAG, "onClick add_record_btn") }
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
