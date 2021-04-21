package org.jzs.mybaseapp.section.zhihu

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jzs.mybaseapp.common.base.BaseEntity
import org.jzs.mybaseapp.common.base.BaseFragment
import org.jzs.mybaseapp.common.system.AppConfig
import org.jzs.mybaseapp.common.system.Constant
import org.jzs.mybaseapp.common.utils.CommonUtils
import org.jzs.mybaseapp.common.utils.RVUtils
import org.jzs.mybaseapp.common.utils.TimeUtils
import org.jzs.mybaseapp.common.widget.BindingAdapter.BaseBindingAdapter
import org.jzs.mybaseapp.common.widget.BindingAdapter.BaseBindingVH
import org.jzs.mybaseapp.section.zhihu.entity.DailyNews
import org.jzs.mybaseapp.section.zhihu.entity.NewsDetail
import org.jzs.retrofit.MyRequestUtils
import org.jzs.retrofit.MySubscriber
import org.jzs.retrofit.TransformUtils
import org.jzs.skutils.utils.glide.GlideUtils
import org.jzs.zhihudaily.R
import org.jzs.zhihudaily.databinding.FragmentZhihuListBinding
import org.jzs.zhihudaily.databinding.ItemZhihuBinding
import java.util.*

class ZhihuFragment : BaseFragment() {
    var mList = arrayListOf<NewsDetail>()
    lateinit var mAdapter: BaseBindingAdapter<NewsDetail, ItemZhihuBinding>

    private var mCurrentDate: Date? = null
    private val mPage = 1
    private var mDataBinding: FragmentZhihuListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_zhihu_list, container, false)
        initView()
        initDate()
        return mDataBinding!!.root
    }

    private fun initView() {
        //        //悬浮按钮控件
        //        FloatingActionButton fab = (FloatingActionButton) mRootview.findViewById(R.id.fab);
        //        fab.setOnClickListener(view -> {
        //            final Snackbar snackbar = Snackbar.make(view, "Snackbar比toast更好用!", Snackbar
        //                    .LENGTH_SHORT);
        //            snackbar.show();
        //            snackbar.setAction("确定", new View.OnClickListener() {
        //                @Override
        //                public void onClick(View view) {
        //                    snackbar.dismiss();
        //                }
        //            });
        //        });

        //设置刷新控件
        mDataBinding!!.refreshLayout.setOnRefreshListener { refreshlayout ->
            mCurrentDate = Date(System.currentTimeMillis())
            mList.clear()
            getNewList()
            refreshlayout.finishRefresh()
        }
        RVUtils.setOnLastItemVisibleListener(mDataBinding!!.rvMain) { getFixNews() }

        //设置每日标题栏分割
        mDataBinding!!.rvMain.addItemDecoration(RVUtils.getZhiHuDailyNewsDecoration(context, CommonUtils.convertDipToPx(context, 20.0), object : DailyNewsStickHeader {

            override fun getTitle(position: Int): String {
                if (position < 0 || position >= mList.size) {
                    return ""
                }

                if (position == 0) {
                    return "今日新闻"
                }

                return if (mList[position] is BaseEntity) {
                    mList[position].headerTitle
                } else ""
            }

            override fun getHeaderColor(position: Int): Int {
                return resources.getColor(R.color.halftransparent)
            }

            override fun isShowTitle(position: Int): Boolean {
                if (position == 0) {
                    return true
                }
                return if (mList[position] is BaseEntity) {
                    mList[position].showTitle
                } else false
            }
        }))


        mAdapter = object : BaseBindingAdapter<NewsDetail, ItemZhihuBinding>(activity, mList, R.layout.item_zhihu) {
            override fun onBindViewHolder(holder: BaseBindingVH<ItemZhihuBinding>, position: Int) {
                //★super一定不要删除
                super.onBindViewHolder(holder, position)
                val binding = holder.binding
                var item = mList[position]
                binding.tvTitle.text = item.title
                if (!CommonUtils.listIsEmpty(item.images)) {
                    GlideUtils.setImage(item.images[0], binding.ivMain)
                }
                binding.cardview.setOnClickListener {
                    val intent = Intent()
                    intent.setClass(activity!!, NewsDetailActivity::class.java)
                    intent.putExtra(Constant.EXTRA.EXTRA_ITEM, item)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, binding.ivMain, "transition")
                    startActivity(intent, options.toBundle())
                }
            }
        }

        mDataBinding!!.rvMain.adapter = mAdapter
    }


    fun initDate() {
        mCurrentDate = Date(System.currentTimeMillis())
        getNewList()
    }

    /**
     * 今天消息
     */
    fun getNewList() {
        MyRequestUtils.getCommonRequestServices(AppConfig.ZHIHU_BASE_URL, ZhihuService::class.java)
                .latestNews
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : MySubscriber<DailyNews>(activity) {
                    override fun taskSuccess(entity: DailyNews) {
                        mList.addAll(entity.stories)
                        for (bean in mList) {
                            bean.showTitle = true
                            bean.headerTitle = "今日新闻"
                        }
                        mAdapter.notifyDataSetChanged()
                    }
                })

    }


    /**
     * 过往消息
     */
    fun getFixNews() {
        mCurrentDate!!.time = mCurrentDate!!.time - 1000 * 60 * 60 * mPage * 24
        val date = TimeUtils.formatDate(mCurrentDate, "yyyyMMdd")
        MyRequestUtils.getCommonRequestServices(AppConfig.ZHIHU_BASE_URL, ZhihuService::class.java)
                .getFixDateNews(date)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : MySubscriber<DailyNews>(activity) {
                    override fun taskSuccess(entity: DailyNews) {

                        for (bean in entity.stories) {
                            bean.showTitle = true
                            bean.headerTitle = TimeUtils.getDateCnDescForZhiHu(mCurrentDate)
                            mList.add(bean)
                        }
                        mAdapter.notifyDataSetChanged()
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
