package tests.sample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import tt.uc.tests.*

class Activity3 : TestableActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ViewUnit(baseContext))

        showFragment(FragmentWithUIContainer(), 111)
    }
}

class FragmentWithUIContainer : TestableFragment() {

    var ui = UI()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroupUnit?, savedInstanceState: Bundle?): ViewUnit? {
        ui.createView(container!!.context)

        return ui.itemView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ui.vButton.setOnClickListener {
            startActivity(Intent(activity!!.baseContext, Activity1::class.java))
        }
    }

    open class UI : UIContainerUnit() {

        lateinit var vButton: ViewUnit

        override fun createView(context: ContextUnit): ViewUnit {
            vButton = ViewUnit(context) //something made by Anko
            itemView = FrameLayoutUnit(context)

            assert(false) //this should not be called in the tests and this class should be extended to create mocks for all view units
            return itemView
        }

    }
}