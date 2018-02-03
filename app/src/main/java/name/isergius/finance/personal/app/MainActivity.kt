package name.isergius.finance.personal.app


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import name.isergius.finance.personal.app.ui.ListRecordsFragment


class MainActivity : AppCompatActivity(), ListRecordsFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            val ftx = supportFragmentManager.beginTransaction()
            val listRecordsFragment = ListRecordsFragment.newInstance()
            ftx.replace(R.id.container, listRecordsFragment, "ListRecordsFragment")
            ftx.commit()
        }
    }

}
