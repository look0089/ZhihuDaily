package org.jzs.mybaseapp.section.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.widget.ImageView
import android.widget.Toast
import com.tencent.map.geolocation.TencentLocation
import org.jzs.mybaseapp.common.system.AppConfig
import org.jzs.mybaseapp.common.system.Constant
import org.jzs.mybaseapp.common.system.SharedPreferencesUtil
import org.jzs.mybaseapp.common.utils.AppLog
import org.jzs.mybaseapp.common.utils.TencentLocationUtils
import org.jzs.mybaseapp.common.utils.ToastUtils
import org.jzs.mybaseapp.common.widget.WebActivity
import org.jzs.mybaseapp.section.main.entity.WeatherEntity
import org.jzs.mybaseapp.section.my.MyActivity
import org.jzs.mybaseapp.section.my.ScanActivity
import org.jzs.retrofit.MyRequestUtils
import org.jzs.retrofit.MySubscriber
import org.jzs.retrofit.TransformUtils
import org.jzs.skutils.permission.PermissionListener
import org.jzs.skutils.permission.PermissionUtils
import org.jzs.zhihudaily.R
import org.jzs.zhihudaily.databinding.ActivityMainBinding

/**
 * @author Jzs created 2017/8/2
 */

class MainActivity : AppCompatActivity() {
    private var isNight: Boolean = false
    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getLocation()
        initView()
    }

    private fun initView() {
        setSupportActionBar(mBinding.mainBarLayout!!.toolbar)
        mBinding.mainBarLayout!!.tvWeather
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        //设置DrawerLayout
        val toggle = ActionBarDrawerToggle(this, mBinding.drawerLayout, mBinding.mainBarLayout?.toolbar, R.string
                .navigation_drawer_open, R.string.navigation_drawer_close)
        mBinding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        //设置点击事件
        mBinding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
                R.id.nav_favorite -> {

                }
                R.id.nav_scan -> {
                    PermissionUtils.requestCamera(this@MainActivity, PermissionListener {
                        startActivity(Intent(this@MainActivity, ScanActivity::class.java))
                    })
                }
                R.id.night -> {
                    isNight = SharedPreferencesUtil.getBoolean("night", false)
                    if (isNight) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        SharedPreferencesUtil.saveBoolean("night", false)
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        SharedPreferencesUtil.saveBoolean("night", true)
                    }
                    recreate()
                }
                R.id.nav_CV -> {
                    val op = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity)
                    ActivityCompat.startActivity(this, Intent(this, MyActivity::class.java), op.toBundle())
                }
                R.id.nav_github -> {
                    val intent = Intent()
                    intent.setClass(this, WebActivity::class.java)
                    intent.putExtra("url", Constant.GitHub_Url)
                    intent.putExtra("name", "GitHub")
                    startActivity(intent)
                }
                else -> {
                }
            }
            mBinding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        mBinding.mainBarLayout?.setOnWeatherClick { v -> getLocation() }

        //设置头像
        val headerView = mBinding.navView.getHeaderView(0)
        val ivHead = headerView?.findViewById<ImageView>(R.id.iv_head)
        ivHead?.setOnClickListener { Toast.makeText(this, "head", Toast.LENGTH_SHORT).show() }

        //tabLayout和viewpager绑定
        mBinding.mainBarLayout!!.mainVpContainer!!.adapter = ViewPagerAdapter(supportFragmentManager)
        mBinding.mainBarLayout!!.toolbarTab.setupWithViewPager(mBinding.mainBarLayout?.mainVpContainer)
    }

    private fun getLocation() {
        mBinding.mainBarLayout!!.tvWeather.text = "获取天气信息…"
        PermissionUtils.requestLocation(this@MainActivity, PermissionListener {
            TencentLocationUtils.getInstance().initLocation(this, object : TencentLocationUtils.MyLocationListener {
                override fun onSuccess(location: TencentLocation) {
                    getWeather(location.city)
                }

                override fun onFail(error: Int) {
                    ToastUtils.showSnackbar(mBinding.navView, "获取地址失败，请重新尝试")
                }
            })
        })
    }

    private fun getWeather(city: String) {
        MyRequestUtils.getCommonRequestServices(AppConfig.HEFENG_BASE_URL, MainService::class.java)
                .getNowWeather(city, Constant.HF_KEY)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : MySubscriber<WeatherEntity>(this) {
                    override fun taskSuccess(entity: WeatherEntity) {
                        val heWeather5entity = entity.HeWeather5[0]
                        val now = heWeather5entity.now
                        mBinding.mainBarLayout!!.tvWeather.text = city + ":" + now.cond.txt + " " + now.tmp + "℃"
                    }

                    override fun taskFailure(entity: WeatherEntity?) {
                        mBinding.mainBarLayout!!.tvWeather.text = "获取失败,点击重试"
                    }
                })
    }

}