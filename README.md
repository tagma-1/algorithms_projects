# Algorithms, Part 1 - Course Assignments

This repository keeps track of my completed assignments as part of the [Algorithms, Part 1](https://www.coursera.org/learn/algorithms-part1) course offered by Coursera/Princeton University. In addition to studying Algorithms, this course will allow me to learn Java for the first time. 

### Week 1 - Union-Find

> We illustrate our basic approach to developing and analyzing algorithms by considering the dynamic connectivity problem. We introduce the union−find data type and consider several implementations (quick find, quick union, weighted quick union, and weighted quick union with path compression). Finally, we apply the union−find data type to the percolation problem from physical chemistry.

The assignment for this week is to estimate the value of the percolation threshold of a given grid (*n* x *n*). Sites (i.e. squares on the grid) are opened randomly via Monte Carlo simulation. Percolation is achieved when the top of the grid is connected to the bottom of the grid through opened sites. Sites are connected to one another vertically and horizontally, but not diagonally. When percolation is achieved, the percolation threshold is the number of open sites divided by the total number of grid sites (*n ^ 2*): for example, if a 5 x 5 grid percolates with 15 open sites, the percolation threshold is 0.6. 

Two Java classes have been completed for this assignment:
- Percolation, which keeps track of a grid with dimensions *N*, opens new sites, keeps track of the total number of sites and tests whether percolation has been achieved; and

- PercolationStats, which runs a given number of trials for a grid with dimensions *N* (via the Percolation class) and returns statistical data including the mean percolation threshold, standard deviation and 95% confidence interval.

Note that the assignment does not require students to implement their own union-find data structure - this is provided as course material and is imported into the Percolation class. To run the solutions in this repository, please see [the instructions at this link](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html). 

#### To Do 

Although this assignment has been completed, I have not yet solved the 'backwash problem' that affects the visualisation of the grid. This results as a consequence of using virtual sites. 

Two virtual sites are added in addition to the grid - one at the top and one at the bottom. Each open site on the bottom and top rows are connected to their respective virtual sites. This allows the union-find algorithm to quickly determine whether percolation has been achieved by simply checking whether the bottom virtual site is connected to the top virtual site - rather than checking whether any of the open sites on the bottom row is connected to any of the sites on the top row. 

Unfortunately, this also means that every open site on the bottom row (and, therefore, every open site connected to it) is taken to be connected to the top virtual site once percolation has been achieved by virtue of its connection to the bottom virtual site - even if that site does not form part of the percolation pathway. This results in the following:

![](http://coursera.cs.princeton.edu/algs4/checklists/percolation-backwash.png)

The easiest way to solve this problem is to initialise two union-find objects in the Percolation Class. One object has a bottom virtual site, and the other does not. The first object is used to check whether percolation has been achieved. The second object is used to check whether a given open site connects to the bottom virtual site (see the *isFull()* method) for visualisation purposes. However, a cheaper solution which only uses one union-find object would be ideal. 

### Week 2 - Stacks and Queues

> We consider two fundamental data types for storing collections of objects: the stack and the queue. We implement each using either a singly-linked list or a resizing array. We introduce two advanced Java features—generics and iterators—that simplify client code. Finally, we consider various applications of stacks and queues ranging from parsing arithmetic expressions to simulating queueing systems.

The assignment for this week was to write a generic data type for a deque and a randomised queue. A deque is a double-ended data structure that supports the addition and removal of items from either the front or back end. A randomised queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure.

The performance requirements for each structure were:

- Deque: supports each deque operation (i.e. adding or removing items) in constant worst-case time; uses at most *48n + 192* bytes (where *n* is the number of items in the data structure) of memory and uses space proportional to the number of items currently in the deque; and implements a customer iterator which supports each operation (including construction) in constant worst-case time.

- Randomised queue: supports each operation in constant amortized time; use at most *48n + 192* bytes of memory; and implements an iterator which supports *next()* and *hasNext()* methods in constant worst-case time and construction in linear time.

To meet these requirements I implemented a linked-list for the deque and a re-sizing array for the randomised queue. 

#### Linked List - Deque

The linked list was the ideal solution for the deque because it allows for constant worst-case time operations when adding or removing a new node - offering consistent performance, unlike an array which needs to be re-sized as items are added or removed. In order to achieve double-ended operations, the data structure simply tracks both the first and last nodes in the linked list. Each node - instantiated from a Node sub-class in the Deque - stores a reference to both the previous and subsequent nodes in the linked list. This makes it simple to support operations which add and/or remove new nodes from the back and front of the deque.

#### Resizing Array - Randomised Queue 

A re-sizing array was chosen for the randomised queue as the array's indices were necessary to support the removal of items in constant time by simply generating a random integer (unlike a linked list, where a given item can only be accessed by interating throught the list). The data structure tracks both the number of items the array contains (*N*) and the 'tail' position, which represents the next available index in the array where a value may be added. *N* could not be used for the tail, as the random removal of values decrements *N* but does not shift the tail or the place of existing values until the array is re-sized. 

Once the tail has reached the end of the array, the array is re-sized to *2N* and compressed (that is, null values from random removals are not copied across to the new array). If *N* is less than a quarter of the array's length, the array is re-sized to *0.5N* and compressed. These operations provide constant amortised time performance. 

The iterator works by creating a compressed copy of the array (linear time), shuffling it (this functionality is provided as course material), and iterating through this copy as next() is called. 

#### Permutation

The last part of the assignment was to write  a client program (Permutation) that takes an integer *k* as a command-line argument; reads in a sequence of strings from standard input using StdIn.readString(); and prints exactly k of them, uniformly at random. This was implemented using the randomised queue described above by using the iterator *k* times. 
