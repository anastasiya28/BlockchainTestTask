package testTask;

import org.apache.log4j.Logger;

public class EntryPoint {
    private static final Logger logger = Logger.getLogger(EntryPoint.class);


    public static void main(String[] args) {
        BlockChain blockChain = new BlockChain();

        // create 100 coins and transfer them to Bob
        Transaction trx1 = new Transaction();
        trx1.setId(1);
        trx1.setType(Transaction.EMMISION);
        trx1.setTo("bob");
        trx1.setAmount(100);
        trx1.setSignature("cf910ac09c14fad23cf65c1952a56016");

        Block block1 = new Block();
        block1.setId(1);
        block1.addTransaction(trx1);
        blockChain.addBlock(null, block1);

        //bob transfers 50 coins to alice
        Transaction trx2 = new Transaction();
        trx2.setId(2);
        trx2.setType(Transaction.TRANSFER);
        trx2.setFrom("bob");
        trx2.setTo("alice");
        trx2.setAmount(50);
        trx2.setSignature("aefeb4338ba9f9a2e6a8e05f92a22926");

        Block block2 = new Block();
        block2.setId(2);
        block2.addTransaction(trx2);
        blockChain.addBlock(1, block2);

        Transaction trx3 = new Transaction();
        trx3.setId(3);
        trx3.setType(Transaction.EMMISION);
        trx3.setTo("bob");
        trx3.setAmount(100);
        trx3.setSignature("c0cc6f0f3e9c374369fa3ad274cc8576");

        Block block3 = new Block();
        block3.setId(3);
        block3.addTransaction(trx3);
        blockChain.addBlock(1, block3);


        Transaction trx4 = new Transaction();
        trx4.setId(4);
        trx4.setType(Transaction.EMMISION);
        trx4.setTo("bob");
        trx4.setAmount(100);
        trx4.setSignature("96d1c242d1fd6dbd2d78ee4bd6302b6d");

        Block block4 = new Block();
        block4.setId(4);
        block4.addTransaction(trx4);
        blockChain.addBlock(2, block4);


        Transaction trx5 = new Transaction();
        trx5.setId(5);
        trx5.setType(Transaction.EMMISION);
        trx5.setTo("bob");
        trx5.setAmount(1000);
        trx5.setSignature("861632bb003efc6837d700f37f00099a");

        Block block5 = new Block();
        block5.setId(5);
        block5.addTransaction(trx5);

        blockChain.addBlock(4, block5);


        logger.info("Balance of Alice: " + blockChain.getBalance("alice"));
        logger.info("Balance of Bob: " + blockChain.getBalance("bob"));

    }
}
