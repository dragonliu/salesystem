package model;

import com.fasterxml.jackson.annotation.JsonView;
import javafx.beans.property.*;
import org.apache.log4j.Logger;
import util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiawei.liu on 12/2/15.
 */
public class Transaction {
    private static Logger logger = Logger.getLogger(Transaction.class);

    private IntegerProperty transactionId;
    private ObjectProperty<LocalDate> date;
    private DoubleProperty payment;
    private StringProperty paymentType;
    private IntegerProperty storeCredit;
    private IntegerProperty staffId;
    private TransactionType type;
    private StringProperty info;
    private List<ProductTransaction> productTransactionList;


    public Transaction(Object... params) {
        this.transactionId = new SimpleIntegerProperty((Integer)params[0]);
        this.productTransactionList = (List<ProductTransaction>) params[1];
        this.date = new SimpleObjectProperty<>(LocalDate.parse((String)params[2]));
        this.payment = new SimpleDoubleProperty((double) params[3]);
        this.paymentType = new SimpleStringProperty((String) params[4]);
        this.storeCredit = new SimpleIntegerProperty((Integer)params[5]);
        this.staffId = new SimpleIntegerProperty((Integer) params[6]);
        this.type = (TransactionType) params[7];
        this.info = new SimpleStringProperty((String) params[8]);
    }

    public static class TransactionBuilder {
        private int transactionId = 0;
        private List<ProductTransaction> productTransactionList;
        private String date = null;
        private double payment = 0.0;
        private String paymentType = null;
        private int staffId = 0;
        private int storeCredit = 0;
        private TransactionType type = null;
        private String info = null;

        public TransactionBuilder transactionId(int transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public TransactionBuilder productInfoList(List<ProductTransaction> productTransactionList){
            this.productTransactionList = productTransactionList;
            return this;
        }

        public TransactionBuilder date(String date) {
            this.date = date;
            return this;
        }

        public TransactionBuilder payment(double payment) {

            this.payment = payment;
            return this;
        }

        public TransactionBuilder paymentType(String paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public TransactionBuilder staffId(int staffId) {
            this.staffId = staffId;
            return this;
        }

        public TransactionBuilder type(TransactionType type) {
            this.type = type;
            return this;
        }

        public TransactionBuilder info(String info) {
            this.info = info;
            return this;
        }

        public TransactionBuilder storeCredit(int storeCredit){
            this.storeCredit = storeCredit;
            return this;
        }

        public Transaction build() {
            return new Transaction(transactionId, productTransactionList, date, payment, paymentType, storeCredit, staffId, type, info);
        }
    }

    public int getTransactionId() {
        return transactionId.get();
    }

    public IntegerProperty transactionIdProperty() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId.set(transactionId);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public double getPayment() {
        return payment.get();
    }

    public DoubleProperty paymentProperty() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment.set(payment);
    }

    public String getPaymentType() {
        return paymentType.get();
    }

    public StringProperty paymentTypeProperty() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType.set(paymentType);
    }

    public int getStaffId() {
        return staffId.get();
    }

    public IntegerProperty staffIdProperty() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId.set(staffId);
    }

    public String getInfo() {
        return info.get();
    }

    public StringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info.set(info);
    }
    public TransactionType getType() {
        return type;
    }

    public List<ProductTransaction> getProductTransactionList() {
        return productTransactionList;
    }

    public int getStoreCredit() {
        return storeCredit.get();
    }

    public IntegerProperty storeCreditProperty() {
        return storeCredit;
    }

    public void setStoreCredit(int storeCredit) {
        this.storeCredit.set(storeCredit);
    }

    public List<String> getProductTransactionListRevised(){
        List<String> list = new ArrayList<>();
        for(ProductTransaction productTransaction: this.productTransactionList){
            StringBuffer string = new StringBuffer();
            list.add(string.append("Product ID: ").append(productTransaction.getProductId())
                            .append(" Total Piece: ").append(productTransaction.getTotalNum())
                            .append(" Quantity: ").append(productTransaction.getQuantity())
                            .append(" Sub-Total: ").append(productTransaction.getSubTotal())
                            .toString());
        }
        return list;
    }

    public void setProductTransactionList(List<ProductTransaction> productTransactionList) {
        this.productTransactionList = productTransactionList;
    }


    public enum TransactionType {
        IN("in"), OUT("out"), RETURN("return");
        private String type;

        private TransactionType(String type) {
            this.type = type;
        }

        public static TransactionType getType(String type) {
            for (TransactionType ttype : TransactionType.values()) {
                if (ttype.name().equalsIgnoreCase(type)) {
                    return ttype;
                }
            }
            return null;
        }
    }
}
