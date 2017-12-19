package name.isergius.finance.personal.app


import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ListRecordsFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ftx = supportFragmentManager.beginTransaction()
        val listRecordsFragment = ListRecordsFragment.newInstance()
        ftx.replace(R.id.container, listRecordsFragment, "ListRecordsFragment")
        ftx.commit()
    }

}
