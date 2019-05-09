package tests.sample

import android.os.Bundle
import tt.uc.tests.ContextUnit
import tt.uc.tests.TestableActivity
import tt.uc.tests.UIContainerUnit
import tt.uc.tests.ViewUnit

//example of activity
open class Activity1 : TestableActivity() {

    var ui = Activity1UI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.createView(baseContext))

        ui.vButton.setOnClickListener {
            finish()
        }
    }
}

open class Activity1UI : UIContainerUnit() {

    open lateinit var vButton: ViewUnit

    override fun createView(context: ContextUnit): ViewUnit {
        itemView = ViewUnit(context) //имитация создания UI через Анко
        return itemView
    }
}