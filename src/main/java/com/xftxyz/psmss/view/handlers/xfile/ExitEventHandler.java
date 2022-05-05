package com.xftxyz.psmss.view.handlers.xfile;

import java.util.Optional;

import com.xftxyz.psmss.view.utils.DialogUtil;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;

public class ExitEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        // System.exit(0);
        // 弹出对话框询问是否确认退出
        // Alert alert = new Alert(AlertType.CONFIRMATION);
        // alert.setTitle("退出");
        // alert.setHeaderText("你确定要退出吗？");
        // alert.setContentText("Are you ok with this?");

        // Optional<ButtonType> result = alert.showAndWait();
        Optional<ButtonType> result = DialogUtil.showConfirmDialog("退出", "你确定要退出吗？", null);
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
        // else {
        // event.consume();
        // }
    }

}
