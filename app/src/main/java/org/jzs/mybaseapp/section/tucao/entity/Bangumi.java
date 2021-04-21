package org.jzs.mybaseapp.section.tucao.entity;


import org.jzs.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jzs on 2017/9/19 0019.
 */

public class Bangumi extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -7648283000283127278L;
    /**
     * code : 200
     * total_count : 10
     * result : [{"hid":"4073976","typeid":"6","create":"1505432264","mukio":"1","typename":"MMD·3D","title":"花海miku","play":"117","description":"花海miku","keywords":"花海","thumb":"http://img.tucao.tv/uploadfile/2017/0915/20170915073626248.jpg","user":"海瑞菌","userid":"158041","part":"1","video":[{"type":"video","file":"http://video.yingtu.co/0/980f9e76-ab25-4f46-b876-21905ed3b9ed.mp4","title":"花海miku"}]},{"hid":"4073829","typeid":"25","create":"1504363046","mukio":"0","typename":"原创·配音","title":"剪纸动画公益广告[讽刺]周已岚(13岁)","play":"11","description":"因为我实在是看不下去如今地球环境的持续恶化和各种污染，所以当我知道了可以做动画广告时，就想：不如我就用动画做一个公益广告吧。于是， ","keywords":"剪纸 公益 动画","thumb":"http://img.tucao.tv/uploadfile/2017/0902/20170902103647225.jpg","user":"定格动画","userid":"147524","part":"1","video":[{"type":"youku","vid":"XMzAwMjgzMTE1Ng","title":"剪纸动画公益广告[讽刺]周已岚(13岁)"}]},{"hid":"4073828","typeid":"25","create":"1504362791","mukio":"0","typename":"原创·配音","title":"儿童原创动画[恐龙]by陈谙衡(5岁) ","play":"8","description":"当别的小朋友在5岁时还在纠结看什么动画的时候，5岁的陈谙衡小导演已经创作出了自己的动画片！2017暑假爸爸特地带他到成都拜师学艺，在老师 ","keywords":"原创动画 恐龙 儿童","thumb":"http://img.tucao.tv/uploadfile/2017/0902/20170902103047735.jpg","user":"定格动画","userid":"147524","part":"1","video":[{"type":"youku","vid":"XMzAwMjY0MTExMg","title":"儿童原创动画[恐龙]by陈谙衡(5岁) "}]},{"hid":"4073746","typeid":"29","create":"1503780717","mukio":"0","typename":"综合·周边·其他","title":"【约会大作战】DATE·A·LIVE 衍生广播 第27期开头小剧场","play":"96","description":"【剧情介绍】\u200b发生在第三卷之后，士道一天遇上了狂三，本还担心那天狂三是否出事，结果一问之下发现狂三每天都在监视(toukui)着自己。\u200b\u2014 ","keywords":"开头","thumb":"http://img.tucao.tv/uploadfile/2017/0827/20170827045049561.png","user":"埃克斯歪贼","userid":"661","part":"1","video":[{"type":"video","file":"https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/46611652_32b6a037f8e208028054e530cdd6f400_559e7b93_3.mp4","title":"【约会大作战】DATE·A·LIVE 衍生广播 第27期开头小剧场"}]},{"hid":"4073622","typeid":"29","create":"1502634769","mukio":"0","typename":"综合·周边·其他","title":"【奇葩科普】服食大便对人体到底有什么影响？【鬼祟来说第一期】","play":"46","description":"资料收集花费时间，但是制作仓促，如果有不对的地方请在评论区留言。这个视频纯粹是无聊中，灵光一闪想出来的，如果喜欢，以后还有更奇葩好 ","keywords":"奇葩 科普 人体","thumb":"http://img.tucao.tv/uploadfile/2017/0813/20170813103230801.png","user":"囬香鬼祟","userid":"358891","part":"1","video":[{"type":"sina","vid":"https://www.bilibili.com/video/av13170866/","title":"【奇葩科普】服食大便对人体到底有什么影响？【鬼祟来说第一期】"}]},{"hid":"4073594","typeid":"6","create":"1502414403","mukio":"14","typename":"MMD·3D","title":"【MMD】❤影武者❤ - ❤桃源恋歌❤ 身娇体柔易推倒的小姐姐们","play":"9615","description":"小姐姐们的桃源恋歌~可爱之余不失一点妩媚？影武者今天首次不限号内测，一起来玩哦！","keywords":"","thumb":"http://img.tucao.tv/uploadfile/2017/0811/20170811091953600.png","user":"Ksss","userid":"381860","part":"1","video":[{"type":"189","vid":"4147111681893495","title":"【MMD】❤影武者❤ - ❤桃源恋歌❤ 身娇体柔易推倒的小姐姐们"}]},{"hid":"4073495","typeid":"6","create":"1501835474","mukio":"0","typename":"MMD·3D","title":"《影武者》极乐净土时装秀","play":"2047","description":"《影武者》极乐净土时装秀","keywords":"武者 极乐 时装秀","thumb":"http://img.tucao.tv/uploadfile/2017/0804/20170804043024496.png","user":"Ksss","userid":"381860","part":1,"video":[{"type":"youku","vid":"XMjkxODU0MjAwOA","title":"《影武者》极乐净土时装秀"}]},{"hid":"4073428","typeid":"25","create":"1501503075","mukio":"0","typename":"原创·配音","title":"【Sexy ASMR】Eva Black 穿丝袜（情色触发视觉ASMR）（预览版）","play":"261","description":"","keywords":"丝袜 视觉","thumb":"http://img.tucao.tv/uploadfile/2017/0731/20170731081028452.jpg","user":"xavier_2012","userid":"388666","part":"1","video":[{"type":"video","file":"","title":"【Sexy ASMR】Eva Black 穿丝袜（情色触发视觉ASMR）（预览版）"}]},{"hid":"4073422","typeid":"29","create":"1501485947","mukio":"0","typename":"综合·周边·其他","title":"蟾蜍养殖技术","play":"13","description":"吉林永发蟾蜍养殖专业培训基地，","keywords":"蟾蜍 养殖技术","thumb":"http://img.tucao.tv/uploadfile/2017/0731/20170731032527579.jpg","user":"蟾狼","userid":"388629","part":"1","video":[{"type":"sina","vid":"http://www.yongfachanchu.com/article/index/id/575","title":"蟾蜍养殖技术"}]},{"hid":"4073345","typeid":"6","create":"1501212476","mukio":"13","typename":"MMD·3D","title":"【Fate MMD】【千本樱】人活着就是为了阿福！！","play":"3912","description":"&rarr;这里是借物表&larr;模型：by すがきれもんhttp: 3d nicovideo jp works td30165场景：by torisutsukihttp: seiga nicovideo jp s ","keywords":"阿福 就是","thumb":"http://img.tucao.tv/uploadfile/2017/0728/20170728112540239.png","user":"Doddy酱","userid":"368237","part":"1","video":[{"type":"189","vid":"8145611670491970","title":"【Fate MMD】【千本樱】人活着就是为了阿福！！"}]}]
     */

    public String code;
    public int total_count;
    public List<ResultEntity> result;

    public static class ResultEntity implements Serializable {
        private static final long serialVersionUID = -6520734790400837997L;
        /**
         * hid : 4073976
         * typeid : 6
         * create : 1505432264
         * mukio : 1
         * typename : MMD·3D
         * title : 花海miku
         * play : 117
         * description : 花海miku
         * keywords : 花海
         * thumb : http://img.tucao.tv/uploadfile/2017/0915/20170915073626248.jpg
         * user : 海瑞菌
         * userid : 158041
         * part : 1
         * video : [{"type":"video","file":"http://video.yingtu.co/0/980f9e76-ab25-4f46-b876-21905ed3b9ed.mp4","title":"花海miku"}]
         */

        public String hid;
        public String typeid;
        public String create;
        public String mukio;
        public String typename;
        public String title;
        public String play;
        public String description;
        public String keywords;
        public String thumb;
        public String user;
        public String userid;
        public String part;
        public List<VideoEntity> video;

        public static class VideoEntity implements Serializable {
            private static final long serialVersionUID = -8836662097048507350L;
            /**
             * type : video
             * file : http://video.yingtu.co/0/980f9e76-ab25-4f46-b876-21905ed3b9ed.mp4
             * title : 花海miku
             */

            public String type;
            public String file;
            public String title;
            public String vid;
        }
    }
}
