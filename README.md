Implementation of the class of Directed Graph.

Methods:
addVertex() - adds a new vertex without any arcs or smth // Result type - void
addArc() - adds a new directed arc from vertex called ("vertexName1") to vertex called  ("vertexName2),
   but not vice versa // Result type - void
deleteVerex() - deletes a vertex with all mentionings about it // Ressult type - void
deletArc() - just deletes arc // Ressult type - void
renameVertex() - rename vertex // Ressult type - void
reweight() - arc's weight changing // Ressult type - void
getOutputArcs() - returns a list of outgoing arcs // Ressult type - List<Pair<String,Integer>>
getInputArcs() - returns a list of ingoing arcs // Ressult type - List<Pair<String,Integer>>
