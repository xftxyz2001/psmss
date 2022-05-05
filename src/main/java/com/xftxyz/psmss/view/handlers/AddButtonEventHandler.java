package com.xftxyz.psmss.view.handlers;

import com.xftxyz.psmss.controller.service.ProcessManagementService;
import com.xftxyz.psmss.model.bean.PCB;
import com.xftxyz.psmss.view.ui.ProcessAddStage;
import com.xftxyz.psmss.view.utils.DialogUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddButtonEventHandler implements EventHandler<ActionEvent> {

    private ProcessAddStage processAddStage;
    private ProcessManagementService processManagementService;

    public AddButtonEventHandler(ProcessAddStage processAddStage) {
        this.processAddStage = processAddStage;
        this.processManagementService = processAddStage.getProcessManagementService();
    }

    @Override
    public void handle(ActionEvent event) {
        // 获取并检测空值
        String sName = processAddStage.getTfName().getText();
        if (sName == null || sName.isEmpty()) {
            // processAddStage.getTfName().setStyle("-fx-border-color: red");
            DialogUtil.showWarningDialog("提示", null, "请输入进程名称");
            return;
        }

        String sPriority = processAddStage.getTfPriority().getText();
        if (sPriority == null || sPriority.isEmpty()) {
            DialogUtil.showWarningDialog("提示", null, "请输入进程优先级");
            return;
        }

        String sArriveTime = processAddStage.getTfArriveTime().getText();
        if (sArriveTime == null || sArriveTime.isEmpty()) {
            DialogUtil.showWarningDialog("提示", null, "请输入进程到达时间");
            return;
        }

        String sTotalTime = processAddStage.getTfTotalTime().getText();
        if (sTotalTime == null || sTotalTime.isEmpty()) {
            DialogUtil.showWarningDialog("提示", null, "请输入进程运行时间");
            return;
        }

        boolean bBlock = processAddStage.getCbBlock().isSelected();

        // 非法值检验
        int name = 0;
        int priority = 0;
        int arriveTime = 0;
        int totalTime = 0;
        try {

            name = Integer.parseInt(sName);
            priority = Integer.parseInt(sPriority);
            arriveTime = Integer.parseInt(sArriveTime);
            totalTime = Integer.parseInt(sTotalTime);
            if (name >= 0 && priority >= 0 && arriveTime >= 0 && totalTime >= 0) {
                PCB pcb = new PCB(name, priority, arriveTime, totalTime, bBlock);
                if (bBlock) {
                    pcb.getBlockTimeProperty().set((int) (Math.random() * 3 + 2));
                }
                processManagementService.addProcess(pcb);
                // processAddStage.close();
            } else {
                DialogUtil.showWarningDialog("提示", "请输入正确的进程信息", "数值必须为自然数");
            }

            // PCB pcbTemp = new PCB(name, priority, arriveTime, totalTime, bBlock);
            // processManagementService.addProcess(pcbTemp);
        } catch (NumberFormatException e) {
            DialogUtil.showWarningDialog("提示", "请输入数字", e.getMessage());
            return;
        }

    }

}
