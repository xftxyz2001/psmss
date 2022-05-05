package com.xftxyz.psmss.controller.service;

import java.util.ArrayList;

import com.xftxyz.psmss.model.bean.PCB;
import com.xftxyz.psmss.model.domain.SimpleProcessQueue;

import javafx.scene.control.Label;

public class ProgramStatusBarService {

    private ArrayList<String> alStatus;

    private Label statusBar;

    public ProgramStatusBarService() {
        // initStatusBar();
        statusBar = new Label("就绪");
        alStatus = new ArrayList<String>();
    }

    public Label getStatusBar() {
        return statusBar;
    }

    public ArrayList<String> getAllStatus() {
        return alStatus;
    }

    // public void setAlStatus(ArrayList<String> alStatus) {
    // this.alStatus = alStatus;
    // }

    public void initStatusBar() {
        statusBar.setText("就绪");
        alStatus = new ArrayList<String>();
    }

    // public void update(ProcessManagementService processManagementService) {
    // int currentSystemTime = processManagementService.getCurrentSystemTime();
    // }

    // public void update(ProcessManagementService processManagementService, String
    // change) {

    public void setText(String text) {
        statusBar.setText(text);
    }

    public void update(SimpleProcessQueue allProcess, int currentSystemTime, String change) {
        // IDLE("空闲"),
        // READY("就绪"),
        // RUNNING("运行"),
        // BLOCKED("阻塞"),
        // FINISHED("完成");
        int idleNumber = 0;
        int readyNumber = 0;
        int runningNumber = 0;
        int blockedNumber = 0;
        int finishedNumber = 0;

        for (PCB pcb : allProcess.getQueue()) {
            switch (pcb.getStatusProperty().get()) {
                case IDLE:
                    idleNumber++;
                    break;
                case READY:
                    readyNumber++;
                    break;
                case RUNNING:
                    runningNumber++;
                    break;
                case BLOCKED:
                    blockedNumber++;
                    break;
                case FINISHED:
                    finishedNumber++;
                    break;
                default:
                    break;
            }
        }

        String status = "当前系统时间：" + currentSystemTime + "  新进程：" + idleNumber + "  就绪进程："
                + readyNumber + "  运行进程：" + runningNumber + "  阻塞进程：" + blockedNumber + "  完成进程：" + finishedNumber
                + "  状态：" + change;
        // System.out.println(statusBar.getText());
        statusBar.setText(status);
        // try {
        // Thread.sleep(10);
        // 让显示停留一会儿，防止过快的更新导致前面的状态信息不可见
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }

        // statusBar.fr
        alStatus.add(status);
        // System.out.println("asdfasdfasdasdf");
    }

}
