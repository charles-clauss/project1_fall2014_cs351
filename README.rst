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
- Client code will be provided by Joel?

World Graph
-----------
- Needs to be read once and stored
- Parse color data to get height values
- Determine location of assigned nest
- (extra) analyze terrain for strong choke points

A* Graph Traversal
------------------
Plan on implementing the graph system as follows:
- Class for Node, Graph 
  graph stores nodes in a balanced search tree? log(n) operations?
- Class for AStar, the heuristic, 
  uses PriorityQueue (heap), again, log(n) operations?

Ant Controller
------------------
- Thread for each ant
- Objects spawned by a factory
- Initially just need to be able to find and gather food


Notes
------------
- IMplement GUI methods - jtable, resizeable world, draw on the picture? 
- waypoints for Astar or altered heuristic
- Event handler can be a priority queue
- add methods to coordinate class to handle immovable objects

