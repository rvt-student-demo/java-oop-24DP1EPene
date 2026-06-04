package rvt.graphis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class C extends B implements IC {
    private final List<E> eObjects = new ArrayList<>();

    public void addE(E e) {
        if (e == null || eObjects.contains(e)) {
            return;
        }

        eObjects.add(e);
        e.addC(this);
    }

    public List<E> getEObjects() {
        return Collections.unmodifiableList(eObjects);
    }
}
