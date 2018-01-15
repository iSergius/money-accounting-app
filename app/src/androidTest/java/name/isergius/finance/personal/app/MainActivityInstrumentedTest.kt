package name.isergius.finance.personal.app

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import name.isergius.finance.personal.app.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Sergey Kondratyev
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun containList() {
        onView(withId(R.id.list_records))
                .check(matches(isDisplayed()))
    }

    @Test
    fun containAddButton() {
        onView(withId(R.id.add_record_btn))
                .check(matches(isDisplayed()))
    }


}