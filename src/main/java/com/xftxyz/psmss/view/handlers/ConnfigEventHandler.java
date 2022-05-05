package com.xftxyz.psmss.view.handlers;

import java.util.Optional;

import com.xftxyz.psmss.controller.service.ProcessManagementService;
import com.xftxyz.psmss.controller.settings.XFSettings;
import com.xftxyz.psmss.model.bean.ProcessStatus;
import com.xftxyz.psmss.view.utils.DialogUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ConnfigEventHandler implements EventHandler<ActionEvent> {

    private ProcessManagementService processManagementService;
    private ProcessStatus processStatus;

    public ConnfigEventHandler(ProcessManagementService pms, ProcessStatus ps) {
        processManagementService = pms;
        processStatus = ps;
    }

    @Override
    public void handle(ActionEvent event) {
        String name = processStatus.toString();
        int sValue = 10;
        switch (processStatus) {
            case IDLE:
                sValue = XFSettings.DEFAULT_IDLE_QUEUE_CAPACITY;
                break;
            case READY:
                sValue = XFSettings.DEFAULT_READY_QUEUE_CAPACITY;
                break;
            case BLOCKED:
                sValue = XFSettings.DEFAULT_BLOCKED_QUEUE_CAPACITY;
                break;
            default:
                break;
        }

        Optional<String> result = DialogUtil.showInputDialog("配置" + name + "进程", null, "请输入队列容量",
                String.valueOf(sValue));
        if (result.isPresent()) {
            try {
                int num = Integer.parseInt(result.get());
                if (num > 0) {
                    processManagementService.setQueueCapacity(processStatus, num);
                } else {
                    DialogUtil.showErrorDialog("配置" + name + "进程", "非法输入", "请输入正整数");
                    return;
                }
            } catch (NumberFormatException e) {
                DialogUtil.showErrorDialog("配置" + name + "进程", "非法输入", "请输入数字（正整数）");
                return;
            }
            // System.out.println("你的输入为: " + result.get());
        }
    }

}
