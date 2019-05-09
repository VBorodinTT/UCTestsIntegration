package tests.sample

import android.content.Context
import tt.uc.tests.ViewUnit

class Activity1Mock : Activity1() {

    init {
        ui = object : Activity1UI() {
            override fun createView(context: Context): ViewUnit {
                vButton = ViewUnit(context)
                itemView = ViewUnit(context)
                return itemView
            }
        }
    }

}
