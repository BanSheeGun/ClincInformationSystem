package dao;
import java.util.*;
import entity.*;

public interface DentistDao {
	boolean deleteDentistById(int dentistId);
	Dentist getDentistById(int dentistId);
	Dentist updateDentistInfo(Dentist dentist);
	Dentist createDentistInfo(Dentist dentist);
	List<Dentist> getDentist(int start);
	int getDentistNumbers();
}
