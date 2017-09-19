package dao;
import entity.*;
public interface InvoiceDao {
	Invoice queryInvoice(int iid);
	Invoice createInvoice(Payment p);
}
