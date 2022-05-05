package com.xftxyz.psmss.model.comparator;

import java.util.Comparator;

import com.xftxyz.psmss.model.bean.PCB;

public class XFComparator {
    public static final Comparator<PCB> ArrivalTimeComparator = new Comparator<PCB>() {
        @Override
        public int compare(PCB o1, PCB o2) {
            return o1.getArriveTimeProperty().get() - o2.getArriveTimeProperty().get();
        }
    };

    public static final Comparator<PCB> PriorityComparator = new Comparator<PCB>() {
        @Override
        public int compare(PCB o1, PCB o2) {
            return o1.getPriorityProperty().get() - o2.getPriorityProperty().get();
        }
    };

    public static final Comparator<PCB> BlockTimeComparator = new Comparator<PCB>() {

        @Override
        public int compare(PCB o1, PCB o2) {
            return o1.getBlockTimeProperty().get() - o2.getBlockTimeProperty().get();
        }

    };

    // public static final Comparator<PCB> PriorityComparator = new
    // Comparator<PCB>() {
    // @Override
    // public int compare(PCB o1, PCB o2) {
    // return o1.getPriorityProperty().get() - o2.getPriorityProperty().get();
    // }
    // };

}
