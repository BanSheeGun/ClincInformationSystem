import dao.impl.*;
import entity.Family;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Family f = null;
		FamilyDao4MySQL s = new FamilyDao4MySQL();
		f = s.queryFamily(1);
		System.out.println(f.getAddress());
		f = s.createFamily("XiAn");
		System.out.println(f.getAddress());
	}

}
