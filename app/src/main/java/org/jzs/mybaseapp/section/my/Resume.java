package org.jzs.mybaseapp.section.my;

import android.content.pm.PackageManager;

import org.jzs.mybaseapp.Applications;
import org.jzs.mybaseapp.section.my.entity.Experience;
import org.jzs.mybaseapp.section.my.entity.PersonInfo;
import org.jzs.mybaseapp.section.my.entity.Project;

/**
 * Created by Jzs on 2017/9/14 0014.
 */

public class Resume {

    public static PersonInfo getinfo() {
        //基本信息
        PersonInfo personInfo = new PersonInfo();
        personInfo.name = "蒋智森";
        personInfo.location = "厦门";
        personInfo.tel = "";
        personInfo.email = "";
        personInfo.school = "江西科技学院";
        personInfo.education = "本科";
        personInfo.major = "软件工程";
        personInfo.work_experience = "6年";
        //个人职业规划
        personInfo.planning = "未来3年的规划\n" +
                "前2年对基础底层知识进一步加深学习，让自己技术能力得到提升。学习更多方向的知识，有机会可往团队管理方面进行发展或安卓高级工程师。";
        //自我简介
        personInfo.self_evaluation = "能很好得与人沟通，具有团队合作精神；对负责的工作努力完成，力争在最短时间内将目标达成；对Android以及软件开发持有兴趣，能在短时间内学习及适应新的工作内容。";
        personInfo.interest = "";

        /**
         * 工作经验
         */

        //易会
        Experience ehui = new Experience();
        ehui.company = "厦门易会移动科技有限公司";
        ehui.workTime = "2014.5-2016.1";
        ehui.position = "安卓工程师";
        ehui.introduction = "易会为会议信息与社交服务的一款产品。\n" +
                "在公司主要负责Android项目的编写、维护与需求修改。\n" +
                "为华为网络大会、华为云计算机大会、华为分析师大会等为会议定制的软件。";

        //项目
//        Project has2016 = new Project();
//        has2016.packageName = "com.ehui.has.activity";
//        ehui.projectList.add(has2016);

        Project im = new Project();
        im.packageName = "com.ehui.im";
        ehui.projectList.add(im);

//        Project hdb = new Project();
//        hdb.packageName = "com.ehui.hdb";
//        ehui.projectList.add(hdb);

        personInfo.experience.add(ehui);


        //卓讯
        Experience zhuoxun = new Experience();
        zhuoxun.company = "厦门卓讯信息技术有限公司";
        zhuoxun.workTime = "2016.1-2017.2";
        zhuoxun.position = "安卓工程师";
        zhuoxun.introduction = "厦门卓讯信息技术有限公司是一家全渠道电商软件服务商。\n" +
                "公司主要负责Android项目的编写、接口文档的编写。\n" +
                "有线上产品易工科技等商城类APP。";

        //项目
        Project yigongjishi = new Project();
        yigongjishi.packageName = "com.yigong.repair";
        zhuoxun.projectList.add(yigongjishi);

        Project yigongchezhu = new Project();
        yigongchezhu.packageName = "com.zorasun.yigongchezhu";
        zhuoxun.projectList.add(yigongchezhu);

        personInfo.experience.add(zhuoxun);


        //厦门吉才神金融信息技术服务有限公司
        Experience jinr = new Experience();
        jinr.company = "厦门吉才神金融信息技术服务有限公司";
        jinr.workTime = "2017.2-2017.6";
        jinr.position = "安卓工程师";
        jinr.introduction = "鲸鱼理财是互联网金融理财的一款APP产品。\n" +
                "公司主要负责Android项目的编写、项目的重构。";

        Project jingyubao = new Project();
        jingyubao.packageName = "com.jinr.core";
        jinr.projectList.add(jingyubao);

        personInfo.experience.add(jinr);


        //厦门几度网络科技有限公司
        Experience jidu = new Experience();
        jidu.company = "厦门几度网络科技有限公司";
        jidu.workTime = "2017.7-";
        jidu.position = "安卓工程师";
        jidu.introduction = "在公司主要负责Android项目的重构、PHP工具的编写、项目后台的编写。";


        Project goomax = new Project();
        goomax.packageName = "com.jidu.goomax";
        jidu.projectList.add(goomax);

        Project fubang = new Project();
        fubang.packageName = "com.fubang.health";
        jidu.projectList.add(fubang);

        personInfo.experience.add(jidu);

        //个人
        Experience geren = new Experience();
        geren.company = "其他";
        geren.workTime = "-";
        geren.position = "";
        geren.introduction = "个人平时做的其他 APP 项目";


        Project base = new Project();
        base.packageName = "org.jzs.mybaseapp";
        geren.projectList.add(base);

        Project mycustomview = new Project();
        mycustomview.packageName = "com.pfh.mycustomview";
        geren.projectList.add(mycustomview);

        personInfo.experience.add(geren);


        //专业技能
        personInfo.skills.add("熟练使用Android Studio和Eclipse开发工具");
        personInfo.skills.add("悉知各大Android应用商店上架流程");
        personInfo.skills.add("熟练使用网络请求库OkHttp、Retrofit、Android-Async-Http");
        personInfo.skills.add("熟练使用图片加载库Universal-Image-Loader、Picasso、Glide");
        personInfo.skills.add("适配Android6.0权限管理");
        personInfo.skills.add("根据项目需求进行的自定义控件。");
        personInfo.skills.add("熟悉主流第三方SDK如：\n" +
                "阿里百川、百度地图、友盟、支付宝支付、微信分享及支付、微博分享等");
        personInfo.skills.add("熟练使用AndroidMaterial Design风格控件RecyclerView、CardView、TabLayout、DrawerLayout 、NavigationView等");
        personInfo.skills.add("使用多个GitHub上的开源项目，如：EventBus、ijkplayer、butterknife、PhotoView等。");


        return personInfo;
    }
}
