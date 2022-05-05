package com.xftxyz.psmss.model.bean;

// 进程状态
public enum ProcessStatus {
    // 空闲（新）
    IDLE("空闲"),
    // 就绪
    READY("就绪"),
    // 运行
    RUNNING("运行"),
    // 阻塞
    BLOCKED("阻塞"),
    // 完成
    FINISHED("完成");

    private String name;

    private ProcessStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
