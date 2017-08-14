package testTask;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Block – a list of transactions.
 */
public class Block {
    private Integer id;
    private List<Transaction> transactions = new ArrayList<>();

    private static final Logger logger = Logger.getLogger(Block.class);

    /**
     * Default constructor, without parameters
     */
    public Block() {
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
     * The method allows to get the list of transactions that relate to a particular block
     *
     * @return List<Transaction> - the list of transactions.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    //Added additional check: check the value of all passed fields, they could not be null so that there is no NullPointerException!!!

    /**
     * The method validate passed transaction. It should perform the following checks:
     * - check that transaction signature is valid.
     *
     * @param trx
     * @return true – if the passed transaction is valid; false – otherwise.
     */
    public boolean validateTransaction(Transaction trx) {
        String data = " ";
        Boolean isValid;

        data += (trx.getId() != null) ? trx.getId().toString() : " ";
        data += (trx.getType() != null) ? ":" + trx.getType().toString() : ":" + " ";
        data += (trx.getFrom() != null) ? ":" + trx.getFrom().toString() : ":" + " ";
        data += (trx.getTo() != null) ? ":" + trx.getTo().toString() : ":" + " ";
        data += (trx.getAmount() != null) ? ":" + trx.getAmount().toString() : ":" + " ";

        System.out.println(data);
        System.out.println(md5Hash(data));

        if (trx.getSignature().equals(md5Hash(data))) {
            logger.info("Valid signature goes here");
            isValid = true;
        } else {
            logger.info("Invalid signature goes here");
            isValid = false;
        }
        return isValid;
    }

    /**
     * The method add transaction to a list of transactions.
     * It should perform the following checks  and if the transaction doesn’t pass at least one check, it should be
     * simply ignored without throwing any exceptions:
     * - validate the transaction using a validateTransaction method;
     * - check if the number of existing transactions in block is less than 10;
     * - check if transaction with transaction.id doesn’t already exist in the list of transactions in this block
     *
     * @param trx
     */
    public void addTransaction(Transaction trx) {
        //validate the transaction using a validateTransaction method;
        if (!validateTransaction(trx)) return;
        //check if the number of existing transactions in block is less than 10;
        if (transactions.size() >= 10) return;
        //check if transaction with transaction.id doesn’t already exist in the list of transactions in this block
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(trx.getId())) return;
        }
        transactions.add(trx);
    }

    /**
     * The internal method that, on the basis of the passed parameters, allows to get an hashed signature
     *
     * @param data
     * @return String - signature.
     */
    private String md5Hash(String data) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(data.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
        BigInteger bigInt = new BigInteger(1, digest);
        return bigInt.toString(16);
    }

    @Override
    public String toString() {
        return "\nBlock{" +
                "id=" + id +
                ", transactions=" + transactions +
                '}';
    }
}
