package testTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Block Tree – a tree of Block objects with a single root. Every Block has a single ancestor and unlimited number of descendants.
 */
public class BlockTree {
    //The field in which is stored the reference to the value of root block.id in BlockTree;
    private Integer rootBlockId = null;
    //The  field in witch is stored the reference to the value of top block.id in BlockTree;
    private Integer topBlockId = null;
    //Length of the maximum branch of BlockTree;
    private int blockChainLenght = 0;
    private List<Block> blockChain = new ArrayList<>();

    //Data structure for storing blocks. As a Value of this map is stored node that contains a block inside.
    // As a Key of this map is stored block.id.
    private Map<Integer, Node> nodes = new HashMap<>();

    /**
     * Default constructor, without parameters
     */
    public BlockTree() {
    }

    /**
     * The method allows to get the value of the field "rootBlockId", which has an access modifier = "private"
     *
     * @return Integer - the value of the field "rootBlockId".
     */
    public Integer getRootBlockId() {
        return rootBlockId;
    }

    /**
     * The method allows to set the value of the field "rootBlockId", which has an access modifier = "private"
     *
     * @param rootBlockId
     */
    public void setRootBlockId(Integer rootBlockId) {
        this.rootBlockId = rootBlockId;
    }

    /**
     * The method allows to get the value of the field "topBlockId", which has an access modifier = "private"
     *
     * @return Integer - the value of the field "topBlockId".
     */
    public Integer getTopBlockId() {
        return topBlockId;
    }

    /**
     * The method allows to set the value of the field "topBlockId", which has an access modifier = "private"
     *
     * @param topBlockId
     */
    public void setTopBlockId(Integer topBlockId) {
        this.topBlockId = topBlockId;
    }

    /**
     * The method allows to get the value of the field "blockChainLenght", which has an access modifier = "private"
     *
     * @return int - the value of the field "blockChainLenght".
     */
    public int getBlockChainLenght() {
        return blockChainLenght;
    }

    /**
     * The method allows to set the value of the field "blockChainLenght", which has an access modifier = "private"
     *
     * @param blockChainLenght
     */
    public void setBlockChainLenght(int blockChainLenght) {
        this.blockChainLenght = blockChainLenght;
    }

    /**
     * The method allows to get the longest chain of Blocks in the Block Tree
     *
     * @return
     */
    public List<Block> getBlockChain() {
        createBlockChain();
        return blockChain;
    }

    /**
     * The method define the longest chain of Blocks in the Block Tree
     */
    public void createBlockChain() {
        List<Block> blocks = new ArrayList<>();
        //number of levels in the longest chain of Blocks in the Block Tree
        int numberOfLevels = getNodeByBlockId(getTopBlockId()).level;
        int nextBlockId = getTopBlockId();

        if (numberOfLevels > 1) {
            for (int i = 1; i < numberOfLevels; i++) {
                Node node = getNodeByBlockId(nextBlockId);
                blocks.add(node.getBlock());
                nextBlockId = node.getParentBlockId();
            }
        }

        Node node = getNodeByBlockId(nextBlockId);
        blocks.add(node.getBlock());
        blockChain = blocks;
    }

    /**
     * The method allows to get node of BlockTree by passed block.id
     *
     * @param blockId
     * @return the current copy of the class "Node"
     */
    public Node getNodeByBlockId(Integer blockId) {
        return nodes.get(blockId);
    }

    public Block getBlockByBlockId(Integer blockId) {
        return nodes.get(blockId).getBlock();
    }


    /**
     * The method add block to the node
     *
     * @param parentBlockId
     * @param block
     * @return created node.
     */
    public Node createNode(Integer parentBlockId, Block block) {
        Node node = new Node();

        if (parentBlockId != null) {
            node.setParentBlockId(parentBlockId);
            //By transfered parentBlockId get parentNode;
            Node parentNode = getNodeByBlockId(parentBlockId);
            //In the created node set the value of field "level". The value of field "level" obtained by increased
            //the value of this field of parent node;
            node.setLevel(parentNode.getLevel() + 1);
        }
        //In the created node set the block;
        node.setBlock(block);
        //Add created node into map of nodes;
        nodes.put(block.getId(), node);

        //Если уровень вложенности оказывается глубже, то перезатираем значение поля "blockChainLenght" + изменяем значение поля "topBlockId";
        //Compare the value of level of created node with value of blockChainLenght; If it is more, then change the value
        // of blockChainLenght and topBlockId;
        if (node.getLevel() > blockChainLenght) {
            blockChainLenght = node.getLevel();
            topBlockId = node.getBlock().getId();
        }
        return node;
    }

    /**
     * Node - secondary structure for building BlockTree;
     */
    class Node {
        //Variable for storing the level of BlockTree nesting
        private int level = 1;
        private Integer parentBlockId;
        private Block block;

        /**
         * Default constructor, without parameters
         */
        public Node() {
        }

        /**
         * The method allows to get the value of the field "level", which has an access modifier = "private"
         *
         * @return int - the value of the field "level".
         */
        public int getLevel() {
            return level;
        }

        /**
         * The method allows to set the value of the field "level", which has an access modifier = "private"
         *
         * @param level
         */
        public void setLevel(int level) {
            this.level = level;
        }

        /**
         * The method allows to get the value of the field "parentBlockId", which has an access modifier = "private"
         *
         * @return int - the value of the field "parentBlockId".
         */
        public Integer getParentBlockId() {
            return parentBlockId;
        }

        /**
         * The method allows to set the value of the field "parentBlockId", which has an access modifier = "private"
         *
         * @param parentBlockId
         */
        public void setParentBlockId(Integer parentBlockId) {
            this.parentBlockId = parentBlockId;
        }

        /**
         * The method allows to get a block that is inside the current copy of the class "Block"
         *
         * @return the current copy of the class "Block".
         */
        public Block getBlock() {
            return block;
        }

        /**
         * The method allows to set a block that is inside the current copy of the class "Block"
         *
         * @param block
         */
        public void setBlock(Block block) {
            this.block = block;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "level=" + level +
                    ", parentBlockId=" + parentBlockId +
                    ", block=" + block +
                    '}';
        }
    }
}

