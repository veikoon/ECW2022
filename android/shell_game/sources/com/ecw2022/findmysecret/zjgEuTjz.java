package com.ecw2022.findmysecret;

import java.util.Optional;
import java.util.Stack;
/* loaded from: classes.dex */
public class zjgEuTjz {
    private boolean rdfIDxTI = false;
    private Stack<Integer> LUdIprHu = new Stack<>();

    public zjgEuTjz() {
    }

    public zjgEuTjz(Integer... numArr) {
        for (Integer num : numArr) {
            this.LUdIprHu.push(num);
        }
    }

    public boolean NZBagfGX() {
        return !this.rdfIDxTI;
    }

    public Optional<Integer> zjgEuTjz() {
        this.rdfIDxTI |= this.LUdIprHu.size() == 0;
        if (this.LUdIprHu.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(this.LUdIprHu.pop());
    }

    public void LWnTPoVy(Integer num) {
        if (!this.LUdIprHu.empty()) {
            int intValue = this.LUdIprHu.firstElement().intValue();
            this.rdfIDxTI = (intValue <= num.intValue()) | this.rdfIDxTI;
        }
        this.LUdIprHu.push(num);
    }
}
