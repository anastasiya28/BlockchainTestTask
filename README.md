## Demo project

### Overview

The project is to implement a very primitive model of blockchain currency, just like Bitcoin, but with many simplifications.

#### Some useful terms:

* Transaction – a single transaction, that creates (“emits”) some coins or moves them between accounts;

* Block – a list of transactions;

* Block Tree – a tree of Block objects with a single root. Every Block has a single ancestor and unlimited number of descendants;

* Block Chain – the longest chain of Block objects within a Block Tree.
