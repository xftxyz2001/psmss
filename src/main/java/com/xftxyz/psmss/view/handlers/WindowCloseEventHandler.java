package com.xftxyz.psmss.view.handlers;

import java.util.Optional;

import com.xftxyz.psmss.view.utils.DialogUtil;

import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

public class WindowCloseEventHandler implements EventHandler<WindowEvent> {

    @Override
    public void handle(WindowEvent event) {
        Optional<ButtonType> result = DialogUtil.showConfirmDialog("退出", "你确定要退出吗？", null);
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
        else {
        event.consume();
        }
        
    }

}
