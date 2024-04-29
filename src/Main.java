import java.io.*;

// TODO: Homemade diy stack (DONE)
// TODO: prefix evaluation (DONE)
public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Mohamed.DESKTOP-6JCBAS3\\Desktop\\projects\\data structures\\proj-2\\src\\test.xml");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        StringBuilder result = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (line.contains("<operator value=") || line.contains("<atom value=")) {
                int start = line.indexOf("value=") + 7; // 7 is length of "value=\""
                int end = line.lastIndexOf("\"");
                String value = line.substring(start, end);
                result.append(value).append(" ");

            }
        }


        String out = result.toString().trim();

        String[] prefix = out.split(" ");

        System.out.println("prefix in xml file:");
        for (String s : prefix){
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println("Valid prefix: " + PrefixIsValid(prefix));

        if (!PrefixIsValid(prefix)){
            System.out.println("Expression is not valid");
        } else {
            System.out.println("infix:");
            String infix = PrefixToInfix(prefix);
            System.out.println(infix);
            System.out.println("value: " + EvaluatePrefix(prefix));
        }


    }


    public static boolean PrefixIsValid(String[] prefix) {
        Stack stack = new Stack();
        for (int i = prefix.length-1; i>=0 ; i--){
            String s = prefix[i];
            if (CheckType(s) == 0){
                stack.push(s);
            } else if (s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-")) {
                if (stack.size()<2) return false;
                stack.pop();
                stack.pop();
                stack.push("1"); //place holder
            } else {
                return false;
            }
        }
        return stack.size() == 1 ; //there should be one operand left
    }

    public static String PrefixToInfix(String[] prefix){
        Stack stack = new Stack();

        for (int i = prefix.length-1 ; i>=0 ; i--){
            if (CheckType(prefix[i]) == 0){
                stack.push(prefix[i]);
            } else if (CheckType(prefix[i]) == 1) {
                String operator = prefix[i];
                String a = stack.pop();
                String b = stack.pop();
                stack.push("("+a+" "+operator+" "+b+")");
            }
        }

        return stack.pop();
    }


    public static double EvaluatePrefix(String[] prefix){
        Stack stack = new Stack();
        for (int i = prefix.length-1 ; i>=0; i--){
            if (CheckType(prefix[i]) == 0){
                stack.push(prefix[i]);
            } else {
                String a = stack.pop();
                String b = stack.pop();
                String operator = prefix[i];

                switch (operator){
                    case "+":
                        stack.push(String.valueOf(Double.parseDouble(a) + Double.parseDouble(b)));
                        break;
                    case "-":
                        stack.push(String.valueOf(Double.parseDouble(a) - Double.parseDouble(b)));
                        break;
                    case "*":
                        stack.push(String.valueOf(Double.parseDouble(a) * Double.parseDouble(b)));
                        break;
                    case "/":
                        stack.push(String.valueOf(Double.parseDouble(a) / Double.parseDouble(b)));
                        break;
                }
            }
        }

        return Double.parseDouble(stack.pop()) ;

    }



    // 0 -> Atom    1 -> operator     2 -> otherwise
    public static int CheckType(String s){
        if (s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-")){
            return 1;
        } else {
            try {
                Integer.parseInt(s);
            } catch (Exception e) {
                return 2;
            }
        }
        return 0;
    }
}