+++++++++++++++++++++++++++++++++++++++++
CS351 Project 1
+++++++++++++++++++++++++++++++++++++++++

Charles Clauss (cclauss@unm.edu)
Aaron Gonzales (agonzales@cs.unm.edu)

The Ant Project
===============

- Socket connection to virtual world
- Generate a graph data structure of the map
- Write an implementation of A* for path optimization
- Task delegation based on ant type
- Decision optimization

Sockets and Threading
---------------------

World Graph
-----------

A* Graph Traversal
------------------
Plan on implementing the graph system as follows:
- Class for Node, Graph 
  graph stores nodes in a balanced search tree? log(n) operations?
- Class for AStar, the heuristic, 
  uses PriorityQueue (heap), again, log(n) operations?

Important Ant Tasks
-------------------

Overall Priority Scheme for AI
------------------------------
