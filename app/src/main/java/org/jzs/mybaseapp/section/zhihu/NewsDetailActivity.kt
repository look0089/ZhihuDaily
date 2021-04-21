package org.jzs.mybaseapp.section.zhihu

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.widget.NestedScrollView
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.ImageView
import okhttp3.ResponseBody
import org.jzs.mybaseapp.common.base.BaseActivity
import org.jzs.mybaseapp.common.system.AppConfig
import org.jzs.mybaseapp.common.system.Constant
import org.jzs.mybaseapp.common.utils.CommonUtils
import org.jzs.mybaseapp.common.utils.StringUtils
import org.jzs.mybaseapp.section.zhihu.entity.NewsDetail
import org.jzs.retrofit.MyRequestUtils
import org.jzs.retrofit.MySubscriber
import org.jzs.retrofit.TransformUtils
import org.jzs.skutils.utils.glide.GlideUtils
import org.jzs.zhihudaily.R
import rx.Observable
import rx.functions.Func1

class NewsDetailActivity : BaseActivity(), View.OnClickListener {

    private var mWebView: WebView? = null
    private var mSdvNewsHeader: ImageView? = null
    private var mIvPre: ImageView? = null
    private var mIvNext: ImageView? = null
    private var mIvLike: ImageView? = null

    private var mWbParent: NestedScrollView? = null
    private var mAppBarLayout: AppBarLayout? = null
    private var mItem: NewsDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        mItem = intent.getSerializableExtra(Constant.EXTRA.EXTRA_ITEM) as NewsDetail
        initView()
        initData()
    }

    private fun initView() {
        mWbParent = findViewById(R.id.ac_zhi_hu_essay_wv_parent) as NestedScrollView
        mSdvNewsHeader = findViewById(R.id.ac_zhi_hu_essay_sdv_news_header) as ImageView
        mIvPre = findViewById(R.id.ac_zhi_hu_essay_iv_pre) as ImageView
        mIvNext = findViewById(R.id.ac_zhi_hu_essay_iv_next) as ImageView
        mIvLike = findViewById(R.id.ac_zhi_hu_essay_iv_lile) as ImageView
        mAppBarLayout = findViewById(R.id.app_bar_layout) as AppBarLayout

        mIvPre!!.setOnClickListener(this)
        mIvLike!!.setOnClickListener(this)
        mIvNext!!.setOnClickListener(this)

    }


    private fun initData() {
        getNewsDetail()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ac_zhi_hu_essay_iv_pre -> {
            }

            R.id.ac_zhi_hu_essay_iv_next -> {
            }

            R.id.ac_zhi_hu_essay_iv_lile -> {
            }
        }
    }

    private fun getNewsDetail() {
        MyRequestUtils.getCommonRequestServices(AppConfig.ZHIHU_BASE_URL, ZhihuService::class.java)
                .getEssayContent(mItem!!.getId())
                .flatMap(Func1<NewsDetail, Observable<NewsDetail>> { dailyNews ->
                    MyRequestUtils.getCommonRequestServices(AppConfig.ZHIHU_BASE_URL, ZhihuService::class.java)
                            .getEssayCSS(dailyNews.css[0])//load css style
                            .compose(TransformUtils.defaultSchedulers())
                            .map(Func1<ResponseBody, NewsDetail> { responseBody ->
                                val css = CommonUtils.getStringFromInputStream(responseBody.byteStream())
                                val head = StringUtils.getHTMLHead(StringUtils.getCSSStyle(css))
                                dailyNews.htmlStr = StringUtils.getHtmlString(head, dailyNews.body)
                                dailyNews
                            })
                })
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : MySubscriber<NewsDetail>(this) {
                    override fun taskSuccess(entity: NewsDetail) {
                        GlideUtils.setImage(entity.image, mSdvNewsHeader)

                        mWebView = WebView(this@NewsDetailActivity)
                        mWebView!!.loadDataWithBaseURL(null, StringUtils.adjustEsssayHtmlStyle(entity.htmlStr), "text/html", "utf-8", null)
                        val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                        mWbParent!!.addView(mWebView, layoutParams)
                    }
                })
    }
}
