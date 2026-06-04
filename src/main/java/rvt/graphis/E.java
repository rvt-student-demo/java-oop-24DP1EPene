package rvt.graphis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E {
    private final List<C> cObjects = new ArrayList<>();

    public void addC(C c) {
        if (c == null || cObjects.contains(c)) {
            return;
        }

        cObjects.add(c);
        c.addE(this);
    }

    public List<C> getCObjects() {
        return Collections.unmodifiableList(cObjects);
    }
}
