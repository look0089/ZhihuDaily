package org.jzs.mybaseapp.section.my

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import org.jzs.mybaseapp.common.base.BaseEntity
import org.jzs.mybaseapp.common.widget.BindingAdapter.BaseBindingAdapter
import org.jzs.mybaseapp.common.widget.BindingAdapter.BaseBindingVH
import org.jzs.mybaseapp.common.widget.MyDialog
import org.jzs.mybaseapp.section.my.entity.Experience
import org.jzs.mybaseapp.section.my.entity.PersonInfo
import org.jzs.mybaseapp.section.my.entity.Project
import org.jzs.zhihudaily.R
import org.jzs.zhihudaily.databinding.ActivityMyBinding
import org.jzs.zhihudaily.databinding.ItemCompanyExpBinding
import org.jzs.zhihudaily.databinding.ItemListBinding


class MyActivity : AppCompatActivity() {

    private var info: PersonInfo = Resume.getinfo()
    lateinit var mAdapter: BaseBindingAdapter<PersonInfo, ItemCompanyExpBinding>
    lateinit var mBinding: ActivityMyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        val slide_right = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_right)
        window.enterTransition = slide_right
        packageManager
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_my)
        iniData()
        initView()
    }

    private fun initView() {
        setSupportActionBar(mBinding!!.toolbar)
        mBinding!!.toolbar.setNavigationOnClickListener { v -> finish() }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mBinding!!.appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset <= -mBinding!!.headLayout.height / 2) {
                mBinding!!.collapsingToolbarLayout.title = "蒋智森-个人简历"
            } else {
                mBinding!!.collapsingToolbarLayout.title = " "
            }
        }

        mAdapter = object : BaseBindingAdapter<PersonInfo, ItemCompanyExpBinding>(this, info.experience, R.layout.item_company_exp) {
            override fun onBindViewHolder(holder: BaseBindingVH<ItemCompanyExpBinding>, position: Int) {
                //★super一定不要删除
                super.onBindViewHolder(holder, position)
                var item = info.experience[position]
                val binding = holder.binding
                binding.tvWorktime.text = item.workTime
                binding.tvCompany.text = item.company
                binding.tvPostion.text = item.position
                binding.tvContent.text = item.introduction
                val ll_project_list = binding.llProjectList
                //添加app view
                for (i in item.projectList.indices) {
                    val project = item.projectList[i]
                    ll_project_list.addView(addView(project))
                }
            }
        }

        mBinding!!.includeCompany!!.rv.adapter = mAdapter
        mBinding!!.includeCompany!!.rv.isNestedScrollingEnabled = false

        mBinding!!.mainNsv.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (oldScrollY > scrollY) {
                //                    fab.animateIn();
            } else {
                //                    fab.animateOut();
            }
        })

    }

    private fun iniData() {
        mBinding!!.info = this.info

        val skills = StringBuffer()
        for (i in info.skills.indices) {
            skills.append(" · " + info.skills.get(i) + "\n")
        }
        mBinding!!.includeSkills!!.tvSkills.text = skills
    }


    /**
     * 添加一个app view
     *
     * @param project
     * @return
     */
    private fun addView(project: Project): View {
        val view = View.inflate(this@MyActivity, R.layout.item_project, null)
        val iv_icon = view.findViewById<View>(R.id.iv_icon) as ImageView
        val tv_name = view.findViewById<View>(R.id.tv_name) as TextView
        try {
            val packageInfo = packageManager!!.getPackageInfo(project.packageName, PackageManager.GET_ACTIVITIES)
            iv_icon.setImageDrawable(packageInfo.applicationInfo.loadIcon(packageManager))
            tv_name.text = packageInfo.applicationInfo.loadLabel(packageManager).toString()
            view.setOnClickListener { v -> openApp(project.packageName) }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return view
    }

    private fun openApp(packageName: String) {

        val myDialog = MyDialog()
        myDialog.showDialog(this, "是否打开APP?")
        myDialog.setCallBack(object : MyDialog.DialogCallBack {
            override fun handle() {
                //                Intent intent = new Intent(Intent.ACTION_MAIN);
                var intent: Intent? = Intent()
                //                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent = packageManager!!.getLaunchIntentForPackage(packageName)
                //                ComponentName cn = new ComponentName(packageName, className);
                //                intent.setComponent(cn);
                startActivity(intent)
            }

            override fun cancelHandle() {

            }
        })
    }
}