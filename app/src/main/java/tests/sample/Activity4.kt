package tests.sample

import android.os.Bundle
import android.view.View
import tt.uc.tests.*

class Activity4 : TestableActivity() {

    lateinit var vViewPager: ViewPagerUnit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ViewUnit(baseContext))

        vViewPager = findView(111)
        vViewPager.adapter = PAdapter()
    }

    inner class PAdapter : PagerAdapterUnit() {
        override fun isViewFromObject(view: View, obj: Any) = true

        override fun getCount() = 4

        override fun instantiateItem(container: ViewGroupUnit, position: Int): Any {
            val item = inflate<FrameLayoutUnit>(container.context, 123)

            val button = item.findView<TextViewUnit>(3)
            button.setOnClickListener {
                toast("page $position")
            }

            container.addView(item)

            return ItemUIHolder(button)
        }
    }

    class ItemUIHolder(val button: TextViewUnit)

}