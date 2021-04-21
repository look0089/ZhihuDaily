package org.jzs.mybaseapp.section

import android.databinding.DataBindingUtil
import android.os.Bundle
import org.jzs.mybaseapp.common.base.BaseActivity
import org.jzs.mybaseapp.common.base.BaseEntity
import org.jzs.mybaseapp.common.widget.BindingAdapter.BaseBindingAdapter
import org.jzs.mybaseapp.common.widget.BindingAdapter.BaseBindingVH
import org.jzs.mybaseapp.section.tucao.TucaoService
import org.jzs.mybaseapp.section.tucao.entity.Bangumi
import org.jzs.retrofit.MyRequestUtils
import org.jzs.retrofit.MySubscriber
import org.jzs.retrofit.TransformUtils
import org.jzs.skutils.recycle.RVUtils
import org.jzs.zhihudaily.R
import org.jzs.zhihudaily.databinding.ActivityListBinding
import org.jzs.zhihudaily.databinding.ItemListBinding

/**
 * list模板
 */

class ListActivity : BaseActivity() {

    lateinit var mBinding: ActivityListBinding

    var mList = arrayListOf<BaseEntity>()
    lateinit var mAdapter: BaseBindingAdapter<BaseEntity, ItemListBinding>
    var mPage = 1
    var isMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        initView()
    }

    fun initView() {


        //设置刷新控件
        mBinding.refreshLayout.setOnRefreshListener({ refreshlayout ->
            mList.clear()
            mPage = 1
//            getData()
            refreshlayout.finishRefresh()
        })
        //滑到最後刷新
        RVUtils.setOnLastItemVisibleListener(mBinding.rv, {
            if (isMore) {
                mPage++
//                getData()
            }
        })

        mAdapter = object : BaseBindingAdapter<BaseEntity, ItemListBinding>(this, mList, R.layout.item_list) {
            override fun onBindViewHolder(holder: BaseBindingVH<ItemListBinding>, position: Int) {
                //★super一定不要删除
                super.onBindViewHolder(holder, position)
                val binding = holder.getBinding()
//                binding!!.tvCheck.setOnClickListener {
//                }
            }
        }
        mBinding.rv.adapter = mAdapter

        mAdapter.itemPresenter = SingleItemPresenter()
    }


    /**
     * ★ Item点击事件P
     */
    inner class SingleItemPresenter {
        fun onItemClick(data: BaseEntity, position: Int) {
        }

        fun onBuyClick(data: BaseEntity) {
        }
    }


    fun getData() {
//        http://www.tucao.tv/api_v2/list.php?type=json&apikey=25tids8f1ew1821ed&tid=19&page=1&pagesize=100
        MyRequestUtils.getRequestServices(TucaoService::class.java)
                .getBangumiList("json", "25tids8f1ew1821ed", "19", "1", "100")
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : MySubscriber<Bangumi>(this) {
                    override fun taskSuccess(entity: Bangumi) {
//                        mList.addAll(entity.list)
//                        if (entity.list.size < 20) {
//                            //滑到最後刷新
//                            isMore = false
//                        }
                        mAdapter.notifyDataSetChanged()
                    }
                })
    }

}
