package com.xftxyz.psmss.view.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.xftxyz.psmss.model.bean.PCB;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileUtil {
    private static FileChooser fileChooser;
    private static ExtensionFilter extensionFilterProcessConfiguration;
    private static ExtensionFilter extensionFilterResult;
    static {
        fileChooser = new FileChooser();
        extensionFilterProcessConfiguration = new ExtensionFilter("进程配置文件", "*.xfdpc");
        extensionFilterResult = new ExtensionFilter("文本文件", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilterProcessConfiguration);
        fileChooser.getExtensionFilters().add(extensionFilterResult);
    }

    public static File showOpenDialog() {
        fileChooser.setTitle("打开文件");
        fileChooser.setSelectedExtensionFilter(extensionFilterProcessConfiguration);
        File file = fileChooser.showOpenDialog(null);
        // System.out.println(file);
        return file;
    }

    public static File showSaveDialog() {
        fileChooser.setTitle("保存文件");
        // fileChooser.getExtensionFilters().add(new ExtensionFilter("进程配置文件",
        // "*.xfdpc"));
        File file = fileChooser.showSaveDialog(null);
        // System.out.println(file);
        return file;
    }

    public static ArrayList<PCB> loadProcessConfigurationFromFile() {
        File file = showOpenDialog();
        if (file == null) {
            return null;
        }
        try (Scanner scanner = new Scanner(file)) {
            int size = scanner.nextInt();
            ArrayList<PCB> processConfiguration = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                // int name, int priority, int arriveTime, int totalTime, boolean block
                int name = scanner.nextInt();
                int priority = scanner.nextInt();
                int arriveTime = scanner.nextInt();
                int totalTime = scanner.nextInt();
                boolean block = scanner.nextBoolean();
                processConfiguration.add(new PCB(name, priority, arriveTime, totalTime, block));
            }
            return processConfiguration;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveProcessConfigurationToFile(ArrayList<PCB> processConfiguration) {
        fileChooser.setSelectedExtensionFilter(extensionFilterProcessConfiguration);
        File file = showSaveDialog();
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                int size = processConfiguration.size();
                writer.println(size);
                for (int i = 0; i < size; i++) {
                    writer.println(processConfiguration.get(i).saveString());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static void saveResult(ArrayList<String> result) {
        fileChooser.setSelectedExtensionFilter(extensionFilterResult);
        File file = showSaveDialog();
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                int size = result.size();
                for (int i = 0; i < size; i++) {
                    writer.println(result.get(i));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
