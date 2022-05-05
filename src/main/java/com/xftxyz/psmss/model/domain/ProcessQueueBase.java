package com.xftxyz.psmss.model.domain;

import com.xftxyz.psmss.model.bean.PCB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class ProcessQueueBase {
    private int capacity; // 队列容量
    // private LinkedList<PCB> queue;
    private ObservableList<PCB> queue;

    public ProcessQueueBase(int cap) {
        this.capacity = cap;
        queue = FXCollections.observableArrayList();
    }

    // 入队
    public boolean enterQueue(PCB pcb) {
        // public void enqueue(PCB pcb) {
        if (queue.size() < capacity) {
            queue.add(pcb);
            return true;
        }
        return false;
    }

    public boolean add(PCB pcb) {
        return enterQueue(pcb);
    }

    // 出队
    public PCB deleteQueue() {
        // public PCB dequeue() {
        if (queue.size() > 0) {
            return queue.remove(0);
        } else {
            return null;
        }
    }

    // 获取队列头元素
    public PCB get() {
        // public PCB getHead() {
        if (queue.size() > 0) {
            return queue.get(0);
        } else {
            return null;
        }
    }

    // 获取队列长度
    public int size() {
        return queue.size();
    }

    // 获取队列容量
    public int capacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
    }

    // 清空队列
    public void clear() {
        queue.clear();
    }

    // 移除队列中的元素
    public void remove(PCB pcb) {
        queue.remove(pcb);
    }

    // 判断元素是否在队列中
    public boolean contains(PCB pcb) {
        int name = pcb.getNameProperty().get();
        for (PCB p : queue) {
            if (p.getNameProperty().get() == name) {
                return true;
            }
        }
        return false;
    }

    public ObservableList<PCB> getQueue() {
        return queue;
    }

}
