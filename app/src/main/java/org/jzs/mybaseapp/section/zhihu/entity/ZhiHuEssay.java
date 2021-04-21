package org.jzs.mybaseapp.section.zhihu.entity;

import org.jzs.mybaseapp.common.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by susion on 17/3/28.
 */
public class ZhiHuEssay extends BaseEntity {
    public String id;
    public int type;
    public String ga_prefix;
    public String title;
    public List<String> images;
    public Date date;
}
