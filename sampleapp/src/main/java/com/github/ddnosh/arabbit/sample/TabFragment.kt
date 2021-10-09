package com.github.ddnosh.arabbit.sample

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.github.ddnosh.arabbit.sample.base.BaseFragment
import com.github.ddnosh.arabbit.sample.databinding.FragmentTabBinding
import com.github.ddnosh.arabbit.sample.function.error.ExceptionFragment
import com.github.ddnosh.arabbit.sample.jetpack.binding.BindingFragment
import com.github.ddnosh.arabbit.sample.jetpack.coroutine.CoroutineFragment
import com.github.ddnosh.arabbit.sample.jetpack.livedata.LiveDataActivity
import com.github.ddnosh.arabbit.sample.jetpack.navigation.NavigationFragment
import com.github.ddnosh.arabbit.sample.jetpack.room_paging.RoomFragment
import com.github.ddnosh.arabbit.sample.jetpack.viewmodel.LoginActivity
import com.github.ddnosh.arabbit.sample.module.image.GlideFragment
import com.github.ddnosh.arabbit.sample.module.ioc.dagger2.Dagger2Fragment
import com.github.ddnosh.arabbit.sample.module.network.NetworkFragment
import com.github.ddnosh.arabbit.sample.module.rxbus.RxBusFragment
import com.github.ddnosh.arabbit.sample.ui.dialog.DialogFragment
import com.github.ddnosh.arabbit.sample.ui.multiplestatusview.MultipleViewStateFragment
import com.github.ddnosh.arabbit.sample.ui.toolbar.ToolBarFragment
import com.github.ddnosh.arabbit.ui.adapter.CommonAdapter
import com.github.ddnosh.arabbit.ui.adapter.CommonViewHolder
import com.github.ddnosh.arabbit.util.LogUtil

class TabFragment : BaseFragment() {

    private val binding by binding<FragmentTabBinding>()
    override fun attachViewBinding(viewContainer: ViewGroup?): ViewBinding {
        return binding
    }

    private var jetPackNameList = arrayListOf(
        "1.ViewModel",
        "2.LiveData",
        "3.Coroutine",
        "4.Binding",
        "5.Navigation",
        "6.Room"
    )
    private var moduleNameList = arrayListOf("1.RxBus", "2.Image", "3.Network", "4.Dagger2")
    private var functionNameList = arrayListOf("1.Error")
    private var uiNameList = arrayListOf("1.Dialog", "2.MultipleStatusView", "3.ToolBar")

    companion object {
        fun newInstance(type: Int): TabFragment {
            val args = Bundle()
            args.putInt("type", type)
            val fragment = TabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val contentViewLayoutID: Int = R.layout.fragment_tab

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        val type = requireArguments().getInt("type", 0)

        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = object :
            CommonAdapter<String>(requireActivity(), R.layout.sample_item, selectNameList(type)) {
            override fun convert(holder: CommonViewHolder, name: String) {
                holder.setText(R.id.sample_text, name)
                holder.setOnClickListener(R.id.item_root) {
                    LogUtil.d(TAG, "onItemClick:$name")
                    navigate(name)
                }
            }
        }
    }

    private fun selectNameList(type: Int): MutableList<String> {
        return when (type) {
            0 -> jetPackNameList
            1 -> moduleNameList
            2 -> functionNameList
            3 -> uiNameList
            else -> arrayListOf("Something Wrong...")
        }
    }

    private fun navigate(name: String) {
        when (name) {
            // for JetPack
            "1.ViewModel" -> readyGo(LoginActivity::class.java)
            "2.LiveData" -> readyGo(LiveDataActivity::class.java)
            "3.Coroutine" -> readyGo(CoroutineFragment::class.java)
            "4.Binding" -> readyGo(BindingFragment::class.java)
            "5.Navigation" -> readyGo(NavigationFragment::class.java)
            "6.Room" -> readyGo(RoomFragment::class.java)
            // for module
            "1.RxBus" -> readyGo(RxBusFragment::class.java)
            "2.Image" -> readyGo(GlideFragment::class.java)
            "3.Network" -> readyGo(NetworkFragment::class.java)
            "4.Dagger2" -> readyGo(Dagger2Fragment::class.java)
            // for function
            "1.Error" -> readyGo(ExceptionFragment::class.java)
            // for UI
            "1.Dialog" -> readyGo(DialogFragment::class.java)
            "2.MultipleStatusView" -> readyGo(MultipleViewStateFragment::class.java)
            "3.ToolBar" -> readyGo(ToolBarFragment::class.java)
        }
    }
}
