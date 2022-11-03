import java.util.Optional;
import java.util.Stack;

public class MyClass {
    private Stack<Integer> c_liste = new Stack<>();
    private boolean c_bool = false;

    public MyClass() {
    }

    public MyClass(Integer... numArr) {
        for (Integer push : numArr) {
            this.c_liste.push(push);
        }
    }

    public boolean isFalse() {
        return !this.c_bool;
    }

    public Optional<Integer> MyClass() {
        this.c_bool |= this.c_liste.size() == 0;
        if (this.c_liste.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(this.c_liste.pop());
    }

    public void add_list_and_update_bool(Integer num) {
        if (!this.c_liste.empty()) {
            int intValue = ((Integer) this.c_liste.firstElement()).intValue();
            this.c_bool = (intValue <= num.intValue()) | this.c_bool;
        }
        this.c_liste.push(num);
    }
}
