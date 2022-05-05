package com.xftxyz.psmss.model.bean;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class PCB {
    // public int name; // =进程标识符=
    // public ProcessStatus status; // 进程状态
    // public int priority; // =进程优先级=
    // public int arriveTime; // =到达时间=
    // public int totalTime; // =进程总时间=
    // public int runTime; // 已运行时间，以时间片为单位，当减至 0 时该进程终止

    // public boolean block; // =是否会出现时间等待=
    // public int blockTime = 0; // 挂起时间
    // public boolean hasBlocked = false; // 是否挂起过

    private SimpleIntegerProperty nameProperty;// =进程标识符=
    private SimpleObjectProperty<ProcessStatus> statusProperty;// 进程状态
    private SimpleIntegerProperty priorityProperty;// =进程优先级=
    private SimpleIntegerProperty arriveTimeProperty;// =到达时间=
    private SimpleIntegerProperty totalTimeProperty;// =进程总时间=
    private SimpleIntegerProperty runTimeProperty;// 已运行时间，以时间片为单位，当减至 0 时该进程终止
    private SimpleDoubleProperty runTimePercentProperty;// 已运行时间百分比

    private SimpleBooleanProperty blockProperty;// =是否会出现时间等待=
    private SimpleIntegerProperty blockTimeProperty;// 挂起时间
    private SimpleBooleanProperty hasBlockedProperty;// 是否挂起过

    public PCB(int name, int priority, int arriveTime, int totalTime, boolean block) {
        // 初始化属性
        this.nameProperty = new SimpleIntegerProperty(name);
        this.priorityProperty = new SimpleIntegerProperty(priority);
        this.arriveTimeProperty = new SimpleIntegerProperty(arriveTime);
        this.totalTimeProperty = new SimpleIntegerProperty(totalTime);
        this.blockProperty = new SimpleBooleanProperty(block);

        this.statusProperty = new SimpleObjectProperty<ProcessStatus>(ProcessStatus.IDLE);
        this.runTimeProperty = new SimpleIntegerProperty(0);

        this.blockTimeProperty = new SimpleIntegerProperty(0);
        this.hasBlockedProperty = new SimpleBooleanProperty(false);

        this.runTimePercentProperty = new SimpleDoubleProperty(0);
        this.runTimePercentProperty
                .bind(new SimpleDoubleProperty(1).multiply(this.runTimeProperty).divide(this.totalTimeProperty));
        // this.name = name;
        // this.status = ProcessStatus.IDLE;
        // this.priority = priority;
        // this.arriveTime = arriveTime;
        // this.totalTime = totalTime;
        // this.runTime = 0;
        // this.block = block;
        // this.progressBar = new ProgressBar();
        // this.progressBar.setProgress(0);
    }

    public SimpleIntegerProperty getNameProperty() {
        return nameProperty;
    }

    // public void setNameProperty(SimpleIntegerProperty nameProperty) {
    // this.nameProperty = nameProperty;
    // }

    public SimpleObjectProperty<ProcessStatus> getStatusProperty() {
        return statusProperty;
    }

    // public void setStatusProperty(SimpleObjectProperty<ProcessStatus>
    // statusProperty) {
    // this.statusProperty = statusProperty;
    // }

    public SimpleIntegerProperty getPriorityProperty() {
        return priorityProperty;
    }

    // public void setPriorityProperty(SimpleIntegerProperty priorityProperty) {
    // this.priorityProperty = priorityProperty;
    // }

    public SimpleIntegerProperty getArriveTimeProperty() {
        return arriveTimeProperty;
    }

    // public void setArriveTimeProperty(SimpleIntegerProperty arriveTimeProperty) {
    // this.arriveTimeProperty = arriveTimeProperty;
    // }

    public SimpleIntegerProperty getTotalTimeProperty() {
        return totalTimeProperty;
    }

    // public void setTotalTimeProperty(SimpleIntegerProperty totalTimeProperty) {
    // this.totalTimeProperty = totalTimeProperty;
    // }

    public SimpleIntegerProperty getRunTimeProperty() {
        return runTimeProperty;
    }

    // public void setRunTimeProperty(SimpleIntegerProperty runTimeProperty) {
    // this.runTimeProperty = runTimeProperty;
    // }

    public SimpleDoubleProperty getRunTimePercentProperty() {
        return runTimePercentProperty;
    }

    // public void setRunTimePercentProperty(SimpleDoubleProperty
    // runTimePercentProperty) {
    // this.runTimePercentProperty = runTimePercentProperty;
    // }

    public SimpleBooleanProperty getBlockProperty() {
        return blockProperty;
    }

    // public void setBlockProperty(SimpleBooleanProperty blockProperty) {
    // this.blockProperty = blockProperty;
    // }

    public SimpleIntegerProperty getBlockTimeProperty() {
        return blockTimeProperty;
    }

    // public void setBlockTimeProperty(SimpleIntegerProperty blockTimeProperty) {
    // this.blockTimeProperty = blockTimeProperty;
    // }

    public SimpleBooleanProperty getHasBlockedProperty() {
        return hasBlockedProperty;
    }

    // public void setHasBlockedProperty(SimpleBooleanProperty hasBlockedProperty) {
    // this.hasBlockedProperty = hasBlockedProperty;
    // }

    public String saveString() {
        // int name, int priority, int arriveTime, int totalTime, boolean block
        return this.nameProperty.get() + " " + this.priorityProperty.get() + " " + this.arriveTimeProperty.get() + " "
                + this.totalTimeProperty.get() + " " + this.blockProperty.get();
    }

    // @Override
    // public int hashCode() {
    // final int prime = 31;
    // int result = 1;
    // result = prime * result + ((nameProperty == null) ? 0 :
    // nameProperty.hashCode());
    // return result;
    // }

    // @Override
    // public boolean equals(Object obj) {
    // if (this == obj)
    // return true;
    // if (obj == null)
    // return false;
    // if (getClass() != obj.getClass())
    // return false;
    // PCB other = (PCB) obj;
    // if (nameProperty == null) {
    // if (other.nameProperty != null)
    // return false;
    // } else if (!nameProperty.equals(other.nameProperty))
    // return false;
    // return true;
    // }

}
