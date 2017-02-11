package datastructure;

import java.math.BigDecimal;

/**
 * Project Name: AlgoDsProject
 * Created By: Jin LI
 * Email:jin.li@vub.be  homtingli@gmail.com
 * By IntelliJ IDEA 2016.3
 * Date: 04/Dec/2016 Time: 11:18
 *
 * @param <T> the type parameter
 */
public class Graph<T extends Comparable> implements Comparable{
    /**
     * The type Node.
     */
    public class Node implements Comparable<Node> {
        /**
         * The Info.
         */
        private T label;
        /**
         * The Edges.
         */
        private LinkedList<Edge> edges;
        //private Vector<Edge> edges;
        /**
         * The Visited.
         */
        private boolean visited;

        /**
         * Instantiates a new Node.
         *
         * @param label the label
         */
        public Node(T label){
            this.label = label;
            visited = false;
            //edges = new Vector<>();
            edges = new LinkedList<>();
        }

        /**
         * Add edge.
         *
         * @param e the e
         */
        public void addEdge(Edge e) {
            edges.addLast(e);
        }

        public void removeEdge(Edge e) {
            edges.removeObject(e);
        }

        /**
         * Compare two node same or not.
         *
         * @param n the n
         * @return Zero means equal.
         */
        public int compareTo(Node n){
            return n.label.compareTo(label);
        }

        /**
         * Get label name.
         *
         * @return the label name.
         */
        private T getLabel(){
            return this.label;
        }

        /**
         * Gets all edges.
         *
         * @return the edges vector.
         */
        private Vector<Node> getEdges() {
            Vector<Node> res = new Vector<>();
            for (int i=0;i<edges.size();i++) {
                Edge edge = this.edges.get(i);
                Node node = edge.getToNode();
                res.addLast(node);
            }
            return res;
        }

        /**
         * Gets edges by weight.
         *
         * @param w the weight
         * @return the edges by wight
         */
        private Vector<Node> getEdgesByWeight(double w) {
            Vector<Node> res = new Vector<>();
            for (int i=0;i<edges.size();i++) {
                Edge edge = this.edges.get(i);
                if (compareDouble(edge.getWeight(),w)==0) {
                //if (edge.getWeight()==(w)) {
                    Node node = edge.getToNode();
                    res.addLast(node);
                }
            }
            return res;
        }

        /**
         * Is node visited.
         *
         * @return the boolean
         */
        private boolean isVisited() {
            return this.visited;
        }

        /**
         * Sets node visited.
         *
         * @param visited the visited
         */
        private void setVisited(boolean visited) {
            this.visited = visited;
        }
    }

    /**
     * Compare two double value.
     *
     * @param d1 value 1
     * @param d2 value 2
     *
     * @return comparable
     */
    private int compareDouble(double d1, double d2) {
        return (new BigDecimal(d1)).compareTo(new BigDecimal(d2));
    }

    /**
     * The type Edge.
     */
    private class Edge implements Comparable<Edge>{
        /**
         * The next node.
         */
        private Node toNode;
        /**
         * The Weight of edge.
         */
        private double weight;

        /**
         * Instantiates a new Edge.
         *
         * @param to     the to
         * @param weight the weight
         */
        public Edge(Node to, double weight){
            toNode = to;
            this.weight = weight;
        }

        /**
         * Compare two edge same or not.
         * two edges are equal if they point
         * to the same node.
         * this assumes that the edges are
         * starting from the same node !!!
         *
         * @param e the e
         * @return the int
         */
        public int compareTo(Edge e) {
            return e.toNode.compareTo(toNode);
        }

        /**
         * Gets edge's weight.
         *
         * @return the weight
         */
        public double getWeight() {
            return this.weight;
        }

        /**
         * Gets next node of an edge.
         *
         * @return the to node
         */
        public Node getToNode() {
            return this.toNode;
        }
    }

    /**
     * The Nodes vector.
     */
    private LinkedList<Node> nodes;

    /**
     * Instantiates a new Graph.
     */
    public Graph(){
        nodes = new LinkedList<>();
    }

    /**
     * Add node for the graph.
     *
     * @param label the label
     */
    public void addNode(T label){
        if (!nodes.find(new Node(label))) {
            nodes.addSorted(new Node(label));
        }
    }

    /**
     * Remove node.
     *
     * @param label the label
     */
    public void removeNode(T label) {
        if (nodes.find(new Node(label))) {
            nodes.removeObject(new Node(label));
            Node temp = new Node(label);
            for (int i=0;i<nodes.size();i++) {
                nodes.get(i).removeEdge(new Edge(temp,1));
            }

        }
    }

    /**
     * Find node in the graph.
     *
     * @param nodeLabel the node label
     * @return the node
     */
    private Node findNode(T nodeLabel){
        return nodes.contains(new Node(nodeLabel));
    }

    /**
     * Add edge for two nodes. We use undirected graph.
     * If the edge exist, update the weight.
     *
     * @param nodeLabel1 the node label 1
     * @param nodeLabel2 the node label 2
     * @param weight     the weight
     */
    public void addEdge(T nodeLabel1, T nodeLabel2, double weight) {
        Node n1 = findNode(nodeLabel1);
        Node n2 = findNode(nodeLabel2);
        if (n1==null || n2==null) {
            return;
        }
        for (int i=0;i<n1.edges.size();i++) {
            Edge e = n1.edges.get(i);
            if (e.getToNode().compareTo(n2)==0) {
                e.weight = weight;
                return;
            }
        }
//        if (n1.edges.find(new Edge(n2,weight))) {
//            System.out.println(""+nodeLabel1+nodeLabel2);
//            return;
//        }
        n1.addEdge(new Edge(n2, weight));
    }

    /**
     * Remove edge.
     *
     * @param node1 the node 1
     * @param node2 the node 2
     */
    public void removeEdge(T node1, T node2) {
        Node n1 = findNode(node1);
        Node n2 = findNode(node2);
        if (n1==null || n2==null) {
            return;
        }
        n1.removeEdge(new Edge(n2,1));
    }

    /**
     * Init status.
     */
    private void initStatus() {
        this.initStatus(null);
    }

    /**
     * Init status.
     *
     * @param list the filter list
     */
    private void initStatus(LinkedList<T> list) {
        for (int i=0; i<nodes.size(); i++){
            Node n = nodes.get(i);
            n.setVisited(false);
        }
        if (list!=null) {
            for (int i=0;i<list.size();i++) {
                findNode(list.get(i)).setVisited(true);
            }
        }
    }

    /**
     * Find path exits or not.
     *
     * @param startNode the start node label
     * @param endNode   the end node label
     * @return the boolean
     */
    public boolean findPath(T startNode, T endNode) {
        return this.findPath(startNode,endNode,null);
    }

    /**
     * Find path exits or not.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @param list      the list
     * @return the boolean
     */
    public boolean findPath(T startNode, T endNode, LinkedList<T> list) {
        Node startState = findNode(startNode);
        Node endState = findNode(endNode);
        if (startNode==null || endNode==null) {
            return DsConst.PATH_NO;
        }
        initStatus(list);

        Stack<Node> toDoList = new Stack<>();
        toDoList.push(startState);

        while (!toDoList.empty()) {
            Node current = toDoList.pop();
            if (current.compareTo(endState)==0) {
                return DsConst.PATH_YES;
            }
            else {
                current.visited = true;
                for (int i=0;i<current.edges.size();i++) {
                    Edge e = current.edges.get(i);
                    if (!e.toNode.visited) {
                        e.toNode.visited = true;
                        toDoList.push(e.toNode);
                    }
                }
            }
        }
        return DsConst.PATH_NO;
    }

    /**
     * Is negative circle.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @return the boolean
     */
    public boolean isNegativeCircle(T startNode, T endNode) {
        return this.isNegativeCircle(startNode, endNode,null);
    }

    /**
     * Is negative circle boolean.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @param list      the filter list
     * @return the boolean
     */
    public boolean isNegativeCircle(T startNode, T endNode, LinkedList<T> list) {
        Dictionary<T,Double> dist = new Dictionary<>();
        Dictionary<T,T> path = new Dictionary<>();
        initStatus(list);
        for(int i=0;i<nodes.size();i++){
            if (!nodes.get(i).isVisited()) {
                dist.add(nodes.get(i).getLabel(), (double) DsConst.UNLINK);
                path.add(nodes.get(i).getLabel(), null);
            }

        }
        dist.setValue(startNode, (double) 0);
        return this.BellmanFord(dist,path);
    }

    /**
     * Find all path extend stack.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @return the stack
     */
    public Stack<Vector> findAllPathExtend(T startNode, T endNode) {
        return this.findAllPathExtend(startNode,endNode,null);
    }

    /**
     * Find all path.
     *
     * @param startNode the start node.
     * @param endNode   the end node.
     * @param list      the list
     * @return all path in many stacks.
     */
    public Stack<Vector> findAllPathExtend(T startNode, T endNode,LinkedList<T> list) {
        Stack<Vector> stack = new Stack<>();
        initStatus(list);
        Node start = findNode(startNode);
        Node end = findNode(endNode);
        if (start==null || end==null) {
            return null;
        }
        Vector<Node> path = new Vector<>();

        path.addLast(start);
        start.setVisited(true);
        findPathExtend_helper(start,path,end,stack);
        start.setVisited(false);
        path.removeLast();

        return stack;
    }

    /**
     * Find path extend helper.
     * It helps findPathExtend.
     *
     * @param current the current
     * @param path    the path
     * @param end     the end
     * @param stack   the stack
     */
    private void findPathExtend_helper(Node current,Vector<Node> path,Node end,Stack<Vector> stack) {
        if (current.compareTo(end)==0) {
            Vector<T> t = new Vector<>();
            for(int i=0;i<path.size();i++) {
                t.addLast((path.get(i)).getLabel());
            }
            stack.push(t);
        }
        else {
            for(int i=0;i<current.edges.size();i++) {
                Node temp = current.edges.get(i).getToNode();
                if(!temp.isVisited()) {
                    temp.setVisited(true);
                    path.addLast(temp);
                    findPathExtend_helper(temp,path,end,stack);
                    temp = path.getLast();
                    temp.setVisited(false);
                    path.removeLast();
                }
            }
        }
    }

    /**
     * Gets distance dijkstra.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @return the distance dijkstra
     */
    public double getDistance_Dijkstra(T startNode, T endNode) {
        return this.getDistance_Dijkstra(startNode, endNode, null);
    }

    /**
     * Gets distance dijkstra.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @param list      the filter list
     * @return the distance dijkstra
     */
    public double getDistance_Dijkstra(T startNode, T endNode, LinkedList<T> list) {
        if (startNode.compareTo(endNode)==0) {
            return 0;
        }
        initStatus(list);

        PriorityQueue<T,Double> q = new PriorityQueue<>();;
        Dictionary<T,T> path = new Dictionary<>(); // its father
        Dictionary<T,Double> dist = new Dictionary<>(); // distance MAX

        Dijkstra(startNode, path, dist, q);

        return dist.getValue(endNode);
    }

    /**
     * Gets depth bfs.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @return the depth bfs
     */
    public int getDepth_BFS(T startNode, T endNode) {
        return this.getDepth_BFS(startNode,endNode,null);
    }

    /**
     * Gets depth bfs.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @param list      the list
     * @return the depth bfs
     */
    public int getDepth_BFS(T startNode, T endNode,LinkedList<T> list) {
        if (startNode.compareTo(endNode)==0) {
            return 0;
        }
        initStatus(list);
        Dictionary<T,Integer> d = new Dictionary<>();
        Dictionary<T,T> p = new Dictionary<>();
        for(int i=0;i<nodes.size();i++) {
            if (!nodes.get(i).isVisited()) {
                d.add(nodes.get(i).getLabel(),-1);
                p.add(nodes.get(i).getLabel(),null);
            }
        }
        d.setValue(startNode,0);
        BFS(startNode,endNode,d,p);

        return d.getValue(endNode);
    }

    /**
     * Gets distance bellman ford.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @return the distance bellman ford
     */
    public double getDistance_BellmanFord(T startNode, T endNode) {
        return this.getDistance_BellmanFord(startNode,endNode,null);
    }

    /**
     * Gets distance bellman ford.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @param list      the filter list
     * @return the distance bellman ford
     */
    public double getDistance_BellmanFord(T startNode, T endNode, LinkedList<T> list) {
        if (startNode.compareTo(endNode)==0) {
            return 0;
        }
        initStatus(list);

        Dictionary<T,T> path = new Dictionary<>(); // its father
        Dictionary<T,Double> dist = new Dictionary<>(); // distance MAX
        for(int i=0;i<nodes.size();i++){
            if (!nodes.get(i).isVisited()) {
                dist.add(nodes.get(i).getLabel(), (double) DsConst.UNLINK);
                path.add(nodes.get(i).getLabel(), null);
            }

        }
        dist.setValue(startNode, (double) 0);

        if(BellmanFord(dist,path)==DsConst.NO_NEGATIVE_CIRCLE) {
            return dist.getValue(endNode);
        } else {
            return DsConst.NOT_FOUND;
        }
    }

    /**
     * Gets stack.
     *
     * @param start the start
     * @param end   the end
     * @param path  the path
     * @return the stack
     */
    private Stack<T> getStack(T start,T end,Dictionary<T,T> path) {
        Stack<T> s = new Stack<>();
        while (true) {
            s.push(end);
            if (path.getValue(end)==null) {
                return null;
            }
            end =  path.getValue(end);
            if (end.compareTo(start) == 0) {
                s.push(end);
                break;
            }
        }
        return s;
    }

    /**
     * Gets adjacent point except special nodes.
     *
     * @param nodeLabel  the node label
     * @param exceptList the exceptlist
     * @return the adjacent point
     */
    public Vector<T> getAdjacentNode(T nodeLabel, LinkedList<T> exceptList) {
        initStatus(exceptList);
        Node node = findNode(nodeLabel);
        if (node==null) {
            return null;
        }
        Vector<T> vector = new Vector<>();
        int edgeNumber = node.getEdges().size();
        for (int i=0;i<edgeNumber;i++) {
            Node n = (node.edges.get(i)).getToNode();
            if (!n.isVisited()){
                vector.addLast(n.getLabel());
            }
        }
        return vector;
    }

    /**
     * Gets adjacent point of special weights.
     *
     * @param nodeLabel the node label
     * @param weights   the weights
     * @return the adjacent point
     */
    public Vector<T> getAdjacentNode(T nodeLabel, int ... weights) {
        Node node = findNode(nodeLabel);
        if (node==null) {
            return null;
        }
        Vector<Node> vector = null;
        Vector<T> adjacentVector = new Vector<>();
        if (weights.length==0) {
            vector = node.getEdges();
        }
        else {
            for (int w : weights) {
                if (vector==null) {
                    vector = node.getEdgesByWeight(w);
                }else {
                    vector.addVector(node.getEdgesByWeight(w));
                }
            }
        }
        if (vector==null) {
            return null;
        }
        for (int i = 0; i < vector.size(); i++) {
            adjacentVector.addLast((vector.get(i)).getLabel());
        }
        return adjacentVector;
    }

    /**
     * Gets short path.
     *
     * @param startNode the node 1
     * @param endNode   the node 2
     * @return the short path
     */
    public Stack<T> getShortPath_Dijkstra(T startNode, T endNode) {
        return this.getShortPath_Dijkstra(startNode,endNode,null);
    }

    /**
     * Gets short path.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @param lists     the lists
     * @return the short path in a stack
     */
    public Stack<T> getShortPath_Dijkstra(T startNode, T endNode,LinkedList<T> lists) {
        if (findNode(startNode)==null || findNode(endNode)==null) {
            return null;
        }
        PriorityQueue<T,Double> q = new PriorityQueue<>();
        Dictionary<T,T> path = new Dictionary<>(); // its father
        Dictionary<T,Double> dist = new Dictionary<>(); // distance MAX
        initStatus(lists);
        Dijkstra(startNode, path, dist, q);

        return getStack(startNode,endNode,path);
    }

    /**
     * Gets short path bfs.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @return the short path bfs
     */
    public Stack<T> getShortPath_BFS(T startNode, T endNode) {
        return getShortPath_BFS(startNode,endNode,null);
    }

    /**
     * Gets short path bfs.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @param list      the list
     * @return the short path bfs
     */
    public Stack<T> getShortPath_BFS(T startNode, T endNode, LinkedList<T> list) {
        initStatus(list);
        Dictionary<T,Integer> d = new Dictionary<>();
        Dictionary<T,T> p = new Dictionary<>();
        for(int i=0;i<nodes.size();i++) {
            if (!nodes.get(i).isVisited()) {
                d.add(nodes.get(i).getLabel(),-1);
                p.add(nodes.get(i).getLabel(),null);
            }
        }
        d.setValue(startNode,0);
        BFS(startNode,endNode,d,p);

        return getStack(startNode,endNode,p);
    }

    /**
     * Gets short path bellman ford.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @return the short path bellman ford
     */
    public Stack<T> getShortPath_BellmanFord(T startNode,T endNode) {
        return getShortPath_BellmanFord(startNode,endNode,null);
    }

    /**
     * Gets short path bellman ford.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @param list      the list
     * @return the short path bellman ford
     */
    public Stack<T> getShortPath_BellmanFord(T startNode, T endNode, LinkedList<T> list) {
        initStatus(list);
        Dictionary<T,T> path = new Dictionary<>(); // its father
        Dictionary<T,Double> dist = new Dictionary<>(); // distance MAX
        for(int i=0;i<nodes.size();i++){
            if (!nodes.get(i).isVisited()) {
                dist.add(nodes.get(i).getLabel(), (double) DsConst.UNLINK);
                path.add(nodes.get(i).getLabel(), null);
            }

        }
        dist.setValue(startNode, (double) 0);

        if (BellmanFord(dist, path)==DsConst.NO_NEGATIVE_CIRCLE) {
            return getStack(startNode, endNode, path);
        }
        else return null;
    }

    /**
     * Dijkstra algorithm.
     *
     * @param node the start node
     * @param path the path to store next node
     * @param dist the dist to store distance
     * @param q    the q to store edge
     */
    private void Dijkstra(T node, Dictionary<T,T> path,
                          Dictionary<T,Double> dist,PriorityQueue<T,Double> q) {
        for (int i=0;i<nodes.size();i++) {
            if (!(nodes.get(i)).isVisited()) {
                T label = (nodes.get(i)).getLabel();
                path.add(label, null);
                dist.add(label, (double) DsConst.UNLINK);
            }
        }

        dist.setValue(node, (double) 0);
        q.push(node,dist.getValue(node));

        while (!q.isEmpty()) {
            T tempNode = q.top();
            q.pop();
            if (findNode(tempNode).isVisited()) {
                continue;
            }
            findNode(tempNode).setVisited(true);
            int i=0;
            while (i<findNode(tempNode).edges.size()) {
                Edge e = findNode(tempNode).edges.get(i);
                Node p = e.getToNode();
                if (!p.isVisited()) {
                    T v = p.getLabel();
                    if (dist.getValue(v) > dist.getValue(tempNode) + e.getWeight()) {
                        dist.setValue(v, dist.getValue(tempNode) + e.getWeight());
                        path.setValue(v, tempNode); // v 's father is tempNode
                        q.push(v, dist.getValue(v));
                    }
                }
                i++;
            }
        }
    }

    /**
     * Bellman ford boolean.
     *
     * @param dist the dist
     * @param path the path
     * @return the boolean
     */
    private boolean BellmanFord(Dictionary<T,Double> dist,
                            Dictionary<T,T> path) {
        // <u,v> d[u] + w(u,v) < d[v]
        for(int k=0;k<nodes.size();k++) {
            for(int i=0;i<nodes.size();i++) {
                T u = nodes.get(i).getLabel();
                for (int j = 0; j < nodes.get(i).edges.size(); j++) {
                    T v = nodes.get(i).edges.get(j).getToNode().getLabel();
                    if (u.compareTo(v) != 0 && !findNode(u).isVisited() && !findNode(v).isVisited()) {
                        if (dist.getValue(v) > dist.getValue(u) + nodes.get(i).edges.get(j).getWeight()) {
                            dist.setValue(v, dist.getValue(u) + nodes.get(i).edges.get(j).getWeight());
                            path.setValue(v, u);
                        }
                    }
                }
            }
        }


        for(int i=0;i<nodes.size();i++) {
            T u = nodes.get(i).getLabel();
            for (int j=0;j<nodes.get(i).edges.size();j++) {
                T v = nodes.get(i).edges.get(j).getToNode().getLabel();
                if (u.compareTo(v)!=0 && !findNode(u).isVisited() && !findNode(v).isVisited()) {
                    if (dist.getValue(v)>dist.getValue(u)+ nodes.get(i).edges.get(j).getWeight()) {
                        System.out.println("negative cycle error");
                        return DsConst.FIND_NEGATIVE_CIRCLE;
                    }
                }
            }
        }
        return DsConst.NO_NEGATIVE_CIRCLE;
    }

    /**
     * Bfs.
     *
     * @param startNode the start node
     * @param endNode   the end node
     * @param d         the d
     * @param p         the p
     */
    private void BFS(T startNode, T endNode,Dictionary<T,Integer> d, Dictionary<T,T> p) {
        if (findNode(startNode)==null && findNode(endNode)==null) {
            return;
        }
        Queue<T> q = new Queue<>();
        q.push(startNode);
        while (!q.empty()) {
            T current = q.pop();
            if (current.compareTo(endNode)==0) {
                return;
            }
            findNode(current).setVisited(true);
            for(int i=0;i<findNode(current).edges.size();i++) {
                Edge e = findNode(current).edges.get(i);
                if (!e.getToNode().isVisited()) {
                    e.getToNode().setVisited(true);
                    p.setValue(e.getToNode().getLabel(),current);
                    d.setValue(e.getToNode().getLabel(),1+d.getValue(p.getValue(e.getToNode().getLabel())));
                    q.push(e.getToNode().getLabel());
                }
            }
        }
    }

    /**
     * DFS algorithm.
     */
    public void DFS() {
        initStatus();

        for (int i=0;i<nodes.size();i++) {
            Node current = nodes.get(i);
            if (!current.isVisited()){
                DFS(current);
            }
        }
    }

    /**
     * DFS algorithm.
     *
     * @param current the current
     */
    private void DFS(Node current) {
        current.setVisited(true);
        for(int i=0;i<current.edges.size();i++) {
            Edge e = current.edges.get(i);
            Node next = e.toNode;
            if (!next.isVisited()) {
                DFS(next);
            }
        }
        System.out.println(current.getLabel());
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        String s= "Graph Nodes: {";
        for(int i=0;i<nodes.size();i++) {
            s += (nodes.get(i)).getLabel();
            s += i==nodes.size()-1?"":", ";
        }
        s += "}";
        return s;
    }

    /**
     * Print.
     */
    public void print() {
        for(int i=0;i<nodes.size();i++) {
            Vector edge = (nodes.get(i)).getEdges();
            for(int j=0;j<edge.size();j++) {
                Edge e = (nodes.get(i)).edges.get(j);
                Node n = e.getToNode();
                System.out.println((nodes.get(i)).getLabel()+"--->"+ n.getLabel()+"  W: "+e.getWeight());
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    /**
     * Intersection graph.
     *
     * @param tt the tt
     * @return the graph
     */
    public Graph<T> intersection(Graph<T> tt) {
        Graph<T> graph = new Graph<>();
        for (int i=0;i<this.nodes.size();i++) {
            Node node = this.nodes.get(i);
            if (tt.findNode(node.getLabel())!=null) {
                graph.addNode(node.getLabel());
            }
        }

        for(int i=0;i<graph.nodes.size();i++) {
            Node node = graph.nodes.get(i);
            Node Gnode1 = this.findNode(node.getLabel());
            Node Gnode2 = tt.findNode(node.getLabel());

            for(int j=0;j<Gnode1.edges.size();j++) {
                for(int k=0;k<Gnode2.edges.size();k++) {
                    Edge e = Gnode1.edges.get(j);
                    Edge e2 = Gnode2.edges.get(k);
                    if (e.toNode.compareTo(e2.toNode)==0) {
                        graph.addEdge(node.getLabel(),e.toNode.getLabel(),1);
                    }
                }
            }
        }
        return graph;
    }

}
