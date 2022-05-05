package com.xftxyz.psmss.view.handlers.xedit;

import java.util.Optional;

import com.xftxyz.psmss.controller.service.ProcessManagementService;
import com.xftxyz.psmss.model.bean.PCB;
import com.xftxyz.psmss.view.utils.DialogUtil;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class QuickAddEventHandler implements EventHandler<ActionEvent> {

    private ProcessManagementService processManagementService;

    public QuickAddEventHandler(ProcessManagementService processManagementService) {
        this.processManagementService = processManagementService;
    }

    @Override
    public void handle(ActionEvent event) {
        Optional<String> result = DialogUtil.showInputDialog("快速添加进程", null, "请输入进程数目", "10");
        if (result.isPresent()) {
            try {
                int num = Integer.parseInt(result.get());
                if (num > 0) {
                    PCB pcbTemp;
                    ObservableList<PCB> queue = processManagementService.getAllProcess().getQueue();
                    int startNum = maxName(queue) + 1;
                    int lastArriveTime = 0;
                    for (int i = startNum; i < startNum + num; i++) {
                        int name = i;
                        int priority = (int) (Math.random() * 10);
                        int arriveTime = (int) (Math.random() * 3) + lastArriveTime + 1;
                        int totalTime = (int) (Math.random() * 20) + 1;
                        boolean block = false;
                        pcbTemp = new PCB(name, priority, arriveTime, totalTime, block);
                        processManagementService.addProcess(pcbTemp);
                    }
                } else {
                    DialogUtil.showErrorDialog("快速添加进程", "非法输入", "请输入正整数");
                    return;
                }
            } catch (NumberFormatException e) {
                DialogUtil.showErrorDialog("快速添加进程", "非法输入", "请输入数字（正整数）");
                return;
            }
            // System.out.println("你的输入为: " + result.get());
        }
    }

    private int maxName(ObservableList<PCB> queue) {
        if (queue == null || queue.size() == 0) {
            return 0;
        }
        int maxName = 0;
        for (PCB pcb : queue) {
            int name = pcb.getNameProperty().get();
            if (name > maxName) {
                maxName = name;
            }
        }
        return maxName;
    }
}