package tests.sample

import android.os.Bundle
import tt.uc.tests.TestableActivity

class MainActivity : TestableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
