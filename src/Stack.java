public class Stack {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("a");
        stack.push("a");
        stack.push("a");
        stack.push("a");
        System.out.println(stack.size());

    }
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
