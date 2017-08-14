package testTask;

import java.util.List;

/**
 * Block Chain – the longest chain of Block objects within a Block Tree
 */
public class BlockChain {
    private BlockTree blockTree = new BlockTree();

    /**
     * Default constructor, without parameters
     */
    public BlockChain() {
    }

    /**
     * The method should return a list of Blocks within the longest chain of Blocks in the Block Tree
     *
     * @return
     */
    public List<Block> getBlockChain() {
        return blockTree.getBlockChain();
    }

    /**
     * The method validate passed block. It should perform the following checks:
     * - the block has at least 1 transaction;
     * - the block with the same id doesn’t exist in the “Block Tree” yet.
     *
     * @param block
     * @return true – if the passed block is valid; false – otherwise.
     */
    public boolean validateBlock(Block block) {
        Boolean isValid;
        if (block.getTransactions().size() == 0) {
            return isValid = false;
        }
        if (blockTree.getBlockByBlockId(block.getId()) != null) {
            return isValid = false;
        }
        return isValid = true;
    }

    /**
     * The method add block to the “Block Tree”. It should perform the following checks:
     * - parentBlockId is null and the root block already exists. As there can be only one root block, we cannot add more blocks like that;
     * - parentBlockId refers to a block that doesn’t exist in the “Block Tree”;
     * - adding “block” to the existing “parentBlockId” block will lead to negative balance on some accounts.
     *
     * @param parentBlockId
     * @param block
     */
    public void addBlock(Integer parentBlockId, Block block) {
        //parentBlockId is null and the root block already exists. As there can be only one root block, we cannot add more blocks like that.
        if (parentBlockId == null) {
            if (blockTree.getRootBlockId() != null) return; //рутовая нода уже существует
            blockTree.createNode(parentBlockId, block); //добавляему рутовую ноду
            return;
        }

        //parentBlockId refers to a block that doesn’t exist in the “Block Tree”;
        BlockTree.Node parentNode = blockTree.getNodeByBlockId(parentBlockId);
        if (parentNode == null) return;

        //adding “block” to the existing “parentBlockId” block will lead to negative balance on some accounts
        List<Transaction> transactions = block.getTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getType() == Transaction.EMMISION) continue;
            int balance = getBalance(transaction.getFrom());
            int amount = transaction.getAmount();
            if (amount > balance) return;
        }

        BlockTree.Node node = blockTree.createNode(parentBlockId, block);
    }

    /**
     * The method allows to get balance of the “account”
     * @param account
     * @return
     * @throws IllegalArgumentException
     */
    public Integer getBalance(String account) throws IllegalArgumentException{
        if(account == null || account.length() < 2 || account.length() > 10){
            throw new IllegalArgumentException("Account can not be null. And it can not be shorter than 2 characters" +
                    " or longer than 100 characters");
        }
        Integer accountBalance = 0;
        List<Block> bloks = getBlockChain();
        for (Block block : bloks) {
            List<Transaction> transactions = block.getTransactions();
            for (Transaction trx : transactions) {
                if (trx.getType() == 0 & trx.getTo().equals(account)) {
                    accountBalance += trx.getAmount();
                }
                if (trx.getType() == 1) {
                    if (trx.getTo().equals(account)) {
                        accountBalance += trx.getAmount();
                    } else {
                        accountBalance -= trx.getAmount();
                    }

                }
            }
        }
        return accountBalance;
    }
}
