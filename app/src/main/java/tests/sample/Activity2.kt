package tests.sample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import tt.uc.tests.*

class Activity2 : TestableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ViewUnit(baseContext))

        showFragment(Fragment1(), 111)
    }
}

class Fragment1 : TestableFragment() {

    lateinit var vButton: ViewUnit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroupUnit?, savedInstanceState: Bundle?): ViewUnit? {

        vButton = ViewUnit(container!!.context)

        return FrameLayoutUnit(container.context)
    }

    override fun onViewCreated(view: ViewUnit, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vButton.setOnClickListener {
            startActivity(Intent(view.context, Activity1::class.java))
        }
    }
}
