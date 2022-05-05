package com.xftxyz.psmss.view.ui;

import javafx.scene.text.Font;

public interface UIData {
    String primaryStageTitle = "单处理机进程调度系统";
    // int sceneWidth = 800;
    // int sceneHeight = 600;
    double SCENE_WIDTH = 1000;
    double SCENE_HEIGHT = 600;

    double processAddStageWidth = 400;
    double processAddStageHeight = 300;

    Font lblfont = Font.font(18);

    String processAddStageTitle = "添加进程";

    String documentTitle = "文档";
    String documentContent = "暂时还没有文档呢，自己摸索一下吧！";

    String aboutTitle = "关于";
    String version = "版本号: 1.0.0";
    String aboutContent = "作者: xftxyz\n" + "邮箱: 2581011320@qq.com\n" + "姓名: 庞晓宇\n" + "学号: 2020118100";

}
