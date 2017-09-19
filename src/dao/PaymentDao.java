package dao;
import entity.*;

public interface PaymentDao {
	Payment queryPayment(int pid);
	Payment createPayment(Payment pay);
	Payment updatePayment(int pid, int iid);
}
