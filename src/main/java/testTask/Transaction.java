package testTask;

import org.apache.log4j.Logger;

/**
 * Created by Nastya on 10.08.2017.
 */

/**
 * Transaction – a single transaction, that creates (“emits”) some coins or moves them between accounts.
 */
public class Transaction {
    /**
     * It means the system creates new “coins” and puts them to the destination account of the transaction (“to” property)
     */
    public static final int EMMISION = 0;
    /**
     * It means that within this transaction one account transfers money to some other account
     */
    public static final int TRANSFER = 1;
    private Integer id;
    private Integer type;
    private String from;
    private String to;
    private Integer amount;
    private String signature;

    private static final Logger logger = Logger.getLogger(Transaction.class);

    /**
     * Default constructor, without parameters
     */
    public Transaction() {
    }

    /**
     * The method allows to get the value of the field "id", which has an access modifier = "private"
     *
     * @return Integer - the value of the field "id".
     */
    public Integer getId() {
        return id;
    }

    /**
     * The method allows to set the value of the field "id", which has an access modifier = "private"
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The method allows to get the value of the field "type", which has an access modifier = "private"
     *
     * @return Integer - the value of the field "type".
     */
    public Integer getType() {
        return type;
    }

    /**
     * The method allows to set the value of the field "type", which has an access modifier = "private".
     * It contains the check that given type is within the allowed range.
     *
     * @param type
     * @throws IllegalArgumentException
     */
    public void setType(Integer type) throws IllegalArgumentException {
        if (type == EMMISION || type == TRANSFER) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("You specified an invalid operation type: " + type);
        }
        // If type is “emission”, then “from” property should be set to null.
        if (type == EMMISION) {
            setFrom(null);
        }
    }

    /**
     * The method allows to get the value of the field "from", which has an access modifier = "private"
     *
     * @return String - source account name.
     */
    public String getFrom() {
        return from;
    }

    //Added additional sub check for first check: the value of passed "type" could not be null so that there is no NullPointerException!!!
    /**
     * The method allows to set the value of the field "from", which has an access modifier = "private".
     * It contains the following checks:
     * - if transaction “type” is “emission” – ignore the passed “from” value and set “from” property to null;
     * - if passed “from” account is null, is shorter than 2 characters or longer than 10 characters – throw an exception.
     *
     * @param from
     * @throws IllegalArgumentException
     */
    public void setFrom(String from) throws IllegalArgumentException {
        //if transaction “type” is “emission” – ignore the passed “from” value and set “from” property to null
        if (type == EMMISION && null != type) {
            this.from = null;
            return;
        }
        //if passed “from” account is null, is shorter than 2 characters or longer than 10 characters – throw an exception
        if (from == null || from.length() < 2 || from.length() > 10) {
            throw new IllegalArgumentException("The value of field \"from\" сan not be null or shorter than 2 characters " +
                    "or longer than 10 characters");
        }
        this.from = from;
    }

    /**
     * The method allows to get the value of the field "to", which has an access modifier = "private"
     *
     * @return String - destination account name.
     */
    public String getTo() {
        return to;
    }

    /**
     * The method allows to set the value of the field "to", which has an access modifier = "private"
     * It contains the following checks:
     * - if passed “to” account is null, is shorter than 2 characters or longer than 10 characters – throw an exception;
     * - if “to” account is the same as the “from” account – throw an exception.
     *
     * @param to
     * @throws IllegalArgumentException
     */
    public void setTo(String to) throws IllegalArgumentException {
        if (to == null) {
            throw new IllegalArgumentException("The value of field \"to\" can not be null");
        }
        if (to.length() < 2 || to.length() > 10) {
            throw new IllegalArgumentException("The value of field \"to\" can not be shorter than 2 characters or longer than 10 characters");
        } else this.to = to;
    }

    /**
     * The method allows to get the value of the field "amount", which has an access modifier = "private"
     *
     * @return Integer - transaction amount.
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * The method allows to set the value of the field "amount", which has an access modifier = "private"
     * It contains the following checks:
     * - if amount is less than zero – throw an exception.
     *
     * @param amount
     * @throws IllegalArgumentException
     */
    public void setAmount(Integer amount) throws IllegalArgumentException {
        if (amount >= 0) {
            this.amount = amount;
        } else {
            throw new IllegalArgumentException("The amount of the transaction can not take negative values");
        }
    }

    /**
     * The method allows to get the value of the field "signature", which has an access modifier = "private"
     *
     * @return String - the value of the field "signature".
     */
    public String getSignature() {
        return signature;
    }

    /**
     * The method allows to set the value of the field "signature", which has an access modifier = "private"
     * It contains the following checks:
     * - if passed signature’s length is not equal to 32 characters – throw an exception.
     *
     * @param signature
     * @throws IllegalArgumentException
     */
    public void setSignature(String signature) throws IllegalArgumentException {
        if (signature.length() != 32) {
            throw new IllegalArgumentException("Signature’s length is not equal to 32 characters");
        } else this.signature = signature;
    }

    @Override
    public String toString() {
        return "\nTransaction{" +
                "id=" + id +
                ", type=" + type +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                ", signature='" + signature + '\'' +
                '}';
    }
}
