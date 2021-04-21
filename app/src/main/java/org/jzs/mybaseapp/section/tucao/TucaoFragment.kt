package org.jzs.mybaseapp.section.tucao

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jzs.mybaseapp.common.base.BaseFragment
import org.jzs.mybaseapp.common.system.AppConfig
import org.jzs.mybaseapp.common.system.Constant
import org.jzs.mybaseapp.common.utils.RVUtils
import org.jzs.mybaseapp.common.widget.BindingAdapter.BaseBindingAdapter
import org.jzs.mybaseapp.common.widget.BindingAdapter.BaseBindingVH
import org.jzs.mybaseapp.section.tucao.entity.Bangumi
import org.jzs.mybaseapp.section.tucao.entity.VideoEntity
import org.jzs.mybaseapp.section.tucao.gsy.PlayActivity
import org.jzs.retrofit.MyRequestUtils
import org.jzs.retrofit.MySubscriber
import org.jzs.retrofit.TransformUtils
import org.jzs.skutils.utils.glide.GlideUtils
import org.jzs.zhihudaily.R
import org.jzs.zhihudaily.databinding.FragmentTucaoListBinding
import org.jzs.zhihudaily.databinding.ItemZhihuBinding

class TucaoFragment : BaseFragment() {
    private var mBinding: FragmentTucaoListBinding? = null

    var mList = arrayListOf<VideoEntity>()
    lateinit var mAdapter: BaseBindingAdapter<VideoEntity, ItemZhihuBinding>

    private var mPage = 1
    private val mPageSize = 10
    var isMore = true


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tucao_list, container, false)
        initView()
        initDate()
        return mBinding!!.root
    }

    private fun initView() {

        //设置刷新控件R
        mBinding!!.refreshLayout.setOnRefreshListener { refreshlayout ->
//            mList.clear()
//            mPage = 1
//            getList()
//            refreshlayout.finishRefresh()
        }
        RVUtils.setOnLastItemVisibleListener(mBinding!!.rvMain) {
//            mPage++
//            getList()
        }


        mAdapter = object : BaseBindingAdapter<VideoEntity, ItemZhihuBinding>(activity, mList, R.layout.item_zhihu) {
            override fun onBindViewHolder(holder: BaseBindingVH<ItemZhihuBinding>, position: Int) {
                //★super一定不要删除
                super.onBindViewHolder(holder, position)
                val binding = holder.binding
                var item = mList[position]
                GlideUtils.setImage(item.image, binding.ivMain)
                binding.tvTitle.text = item.title
                binding.cardview.setOnClickListener {
                    val intent = Intent()
                    intent.setClass(activity!!, PlayActivity::class.java)
                    intent.putExtra(Constant.EXTRA.EXTRA_ITEM, item.url)
                    startActivity(intent)
                }
            }
        }

        mBinding!!.rvMain.setAdapter(mAdapter)

    }


    fun initDate() {
        getTvList()
    }

    /**
     * 新番列表
     * tid=11
     */
    fun getList() {
        MyRequestUtils.getCommonRequestServices(AppConfig.TUCAO_BASE_URL, TucaoService::class.java)
                .getBangumiList("json", "25tids8f1ew1821ed", "19", mPage.toString(), mPageSize.toString())
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : MySubscriber<Bangumi>(activity) {
                    override fun taskSuccess(entity: Bangumi) {

                    }
                })
    }

    fun getTvList() {
        val list = arrayListOf<VideoEntity>()
        val hktv = VideoEntity()
        hktv.url = "rtmp://live.hkstv.hk.lxdns.com/live/hks"
        hktv.image = "http://www.zhiboba.org/zbbimg/xianggangweishi.png"
        hktv.title = "香港卫视"
        list.add(hktv)

        val 珠海 = VideoEntity()
        珠海.url = "rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp"
        珠海.image = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fd.lanrentuku.com%2Fdown%2Fpng%2F0904%2FM-v-Player%2FM-v-Player_13.png&refer=http%3A%2F%2Fd.lanrentuku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621501122&t=e7d3e39c2ddfa10294a3bca6e92c811a"
        珠海.title = "珠海过澳门大厅摄像头监控"
        list.add(珠海)

        val CCTV1 = VideoEntity()
        CCTV1.url = "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8"
        CCTV1.image = "https://dss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2648681633,2245521422&fm=58"
        CCTV1.title = "CCTV1"
        list.add(CCTV1)

        val CCTV3 = VideoEntity()
        CCTV3.url = "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8"
        CCTV3.image = "https://bkimg.cdn.bcebos.com/pic/b64543a98226cffc91c7e8beb6014a90f703eac9?x-bce-process=image/resize,m_lfit,w_268,limit_1/format,f_jpg"
        CCTV3.title = "CCTV3"
        list.add(CCTV3)

        val CCTV5 = VideoEntity()
        CCTV5.url = "http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8"
        CCTV5.image = "https://bkimg.cdn.bcebos.com/pic/b64543a98226cffc91c7e8beb6014a90f703eac9?x-bce-process=image/resize,m_lfit,w_268,limit_1/format,f_jpg"
        CCTV5.title = "CCTV5"
        list.add(CCTV5)


        val 公司1 = VideoEntity()
        公司1.url = "rtsp://admin:xmqt5231000@192.168.100.248:554"
        公司1.image = "https://bkimg.cdn.bcebos.com/pic/b64543a98226cffc91c7e8beb6014a90f703eac9?x-bce-process=image/resize,m_lfit,w_268,limit_1/format,f_jpg"
        公司1.title = "公司1"
        list.add(公司1)

        mList.clear()
        mList.addAll(list)
    }
}
