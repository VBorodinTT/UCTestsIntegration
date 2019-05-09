package tests.sample

import android.os.Bundle
import tt.uc.tests.*

class Activity5 : TestableActivity() {

    lateinit var vRecView: RecyclerViewUnit<ItemUIHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(112)

        vRecView = findView(111)
        vRecView.adapter = RAdapter()
    }

    inner class RAdapter : RecyclerViewAdapterUnit<ItemUIHolder>() {
        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: ItemUIHolder, position: Int) {
            holder.vButton.setOnClickListener {
                toast("page $position")
            }
        }

        override fun getItemViewType(position: Int): Int {
            return 1
        }

        override fun onCreateViewHolder(parent: ViewGroupUnit, viewType: Int): ItemUIHolder {
            return ItemUIHolder(inflate(parent.context, 345))
        }
    }

    class ItemUIHolder(item: ViewUnit) : RecyclerViewHolderUnit(item) {
        val vButton = itemView.findView<TextViewUnit>(111)
    }

}