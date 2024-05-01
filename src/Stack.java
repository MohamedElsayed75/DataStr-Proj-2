public class Stack {
    private int count = -1;

    Node top = null;
    public void push(String v){
        Node newNode = new Node(v);
        newNode.next=top;
        top = newNode;
        count++;
    }
    public String pop(){
        String x = top.value;
        top = top.next;
        count--;
        return x;
    }
    public int size(){
        return count+1;
    }
}
