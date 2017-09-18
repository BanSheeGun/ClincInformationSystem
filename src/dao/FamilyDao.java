package dao;

import java.util.*;
import entity.*;

public interface FamilyDao {
	List<Patient> queryFamilyMembers(int fid);
	Family queryFamily(int fid);
	Family queryFamily(String add);
	Family createFamily(String add);
	boolean deleteFamily(int fid);
	boolean updateFamily(Family f);
	List<Family> queryFamilyList(int start);
	int queryFamilyNumbers();
}
