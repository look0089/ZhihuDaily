package org.jzs.mybaseapp.section.zhihu;

/**
 * Created by susion on 17/3/9.
 */
public interface DailyNewsStickHeader {

    String getTitle(int position);

    int getHeaderColor(int position);

    boolean isShowTitle(int position);
}
