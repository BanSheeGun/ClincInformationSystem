package dao;
import java.util.*;
import entity.*;

public interface DentistDao {
	boolean deleteDentistById(int dentistId);
	Dentist getDentistById(int dentistId);
	boolean updateDentistInfo(Dentist dentist);
	boolean createDentistInfo(Dentist dentist);
	List<Dentist> getDentist(int start);
	int getDentistNumbers();
}
