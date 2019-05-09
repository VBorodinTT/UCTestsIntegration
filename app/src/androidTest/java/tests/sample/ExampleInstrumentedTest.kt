package tests.sample

import android.content.Intent
import android.os.Looper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import tt.uc.tests.ActivityRouter
import tt.uc.tests.ContextUnit
import tt.uc.tests.TestableActivity
import tt.uc.tests.ViewUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    val mockFabric: (String) -> TestableActivity? = { activityName: String ->

        when (activityName) {
            Activity1::class.java.name -> Activity1Mock()
            else -> null
        }

    }

    @Before
    fun before() {
        if (Looper.myLooper() == null)
            Looper.prepare()
    }


    @Test
    fun EmptyTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val router = ActivityRouter(appContext, { null }, { it })
        router.startActivity(TestableActivity())
        assertEquals(1, router.getStackSize())
    }

    @Test
    fun ActivityWithUIContainer() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val router = ActivityRouter(appContext, mockFabric, { it })
        router.startActivity(Intent(appContext, Activity1::class.java))

        assertTrue(router.peekTopActivity() is Activity1Mock)

        router.getActivity<Activity1>().ui.vButton.performClick()

        assertEquals(0, router.getStackSize())

    }

    @Test
    fun ActivityWithSimpleFragment() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val router = ActivityRouter(appContext, mockFabric, { it })
        router.startActivity(Intent(appContext, Activity2::class.java))

        val f = router.getFragment<Fragment1>(111)
        f.vButton.performClick()

        assertTrue(router.peekTopActivity() is Activity1)
    }

    @Test
    fun ActivityWithUIContainerInFragment() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val router = ActivityRouter(appContext, mockFabric, { f ->
            if (f is FragmentWithUIContainer)
                f.ui = FragmentWithUIContainerUIMock()
            f
        })
        router.startActivity(Intent(appContext, Activity3::class.java))

        val f = router.getFragment<FragmentWithUIContainer>(111)
        f.ui.vButton.performClick()

        assertTrue(router.peekTopActivity() is Activity1)
    }

    class FragmentWithUIContainerUIMock : FragmentWithUIContainer.UI() {
        override fun createView(context: ContextUnit): ViewUnit {
            vButton = ViewUnit(context)
            itemView = ViewUnit(context)
            return itemView
        }
    }

    @Test
    fun ActivityWithViewPager() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val router = ActivityRouter(appContext, mockFabric, { f -> f })
        router.startActivity(Intent(appContext, Activity4::class.java))

        val item = router.getActivity<Activity4>().vViewPager.items[2] as Activity4.ItemUIHolder
        item.button.performClick()
        assertEquals("page 2", router.getActivity<Activity4>().toastList.last())
    }

    @Test
    fun ActivityWithReciclerView() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val router = ActivityRouter(appContext, mockFabric, { f -> f })
        router.startActivity(Intent(appContext, Activity5::class.java))

        val item = router.getActivity<Activity5>().vRecView.holders[5]
        item.vButton.performClick()
        assertEquals("page 5", router.getActivity<Activity5>().toastList.last())
    }
}
