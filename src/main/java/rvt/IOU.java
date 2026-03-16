package rvt;

import java.util.HashMap;

public class IOU {
    private HashMap<String, Double> debts = new HashMap<>();
    IOU() {}

    void setSum(String toWhom, Double amount) {
        debts.put(toWhom, amount);
    }

    double howMuchDoIOweTo(String toWhom) {
        Double dept = debts.get(toWhom);
        return dept == null ? 0 : dept;
    }
}
