import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Main{
	public static void main(String[] args) {
		Main main = new Main();
		ArrayList<String> list = new ArrayList<String>();
		int i = 31;
		main.onReceive(list, i);
	}

	private MyClass liste1 = new MyClass();
	private MyClass liste2 = new MyClass(4, 3, 2, 1, 0);
	private MyClass liste3 = new MyClass();
	private boolean bool = false;

	private void sendFlag(int i) {
		System.out.println("########### sendFlag ###########");
		System.out.println("this.bool : " + this.bool);
		System.out.println("this.liste1.isFalse() : " + this.liste1.isFalse());
		System.out.println("this.liste2.isFalse() : " + this.liste2.isFalse());
		System.out.println("this.liste3.isFalse() : " + this.liste3.isFalse());
		System.out.println("i : " + i);

		if (!this.bool && this.liste2.isFalse() && this.liste3.isFalse() && this.liste1.isFalse() && i == 31) {
			System.out.println("Flag trouvé");
		}
	}

	public void onReceive(ArrayList<String>  f, int r) {
		System.out.println("########### onReceive ###########");
		System.out.println("ArrayList : " + f);
		System.out.println("Int : " + r);
		boolean z = false;
		int intExtra = r;
		if (intExtra < 512) {
			ArrayList<String> stringArrayListExtra = f;
			if (stringArrayListExtra.size() < 1024) {
				if (stringArrayListExtra.size() < 2) {
					boolean z2 = this.bool;
					if (stringArrayListExtra.size() != 0) {
						z = true;
					}
					this.bool = z2 | z;
					sendFlag(intExtra);
					return;
				}
				String str = stringArrayListExtra.get(0);
				String str2 = stringArrayListExtra.get(1);
				Optional<Integer> empty = Optional.empty();
				if (str.equals("A")) {
					empty = this.liste2.MyClass();
				} else if (str.equals("B")) {
					empty = this.liste3.MyClass();
				} else if (str.equals("C")) {
					empty = this.liste1.MyClass();
				}
				if (!empty.isPresent()) {
					this.bool |= true;
					return;
				}
				if (str2.equals("A")) {
					this.liste2.add_list_and_update_bool(empty.get());
				} else if (str2.equals("B")) {
					this.liste3.add_list_and_update_bool(empty.get());
				} else if (str2.equals("C")) {
					this.liste1.add_list_and_update_bool(empty.get());
				}
				ArrayList arrayList = new ArrayList(stringArrayListExtra.subList(2, stringArrayListExtra.size()));
				onReceive(arrayList, intExtra + 1);
			}
		}
	}
}
