package assignment2;

//import java.util.Arrays;

public class ActionQueue extends MyQueue<Direction>{
    private MyDoublyLinkedList<Direction> directions;

    public ActionQueue() {
        this.directions = new MyDoublyLinkedList<>();
    }

    @Override
    public void clear() {
        super.clear();
        this.directions.clear();
    }

    public boolean isDirection(char c) {
        return c == 'N' || c == 'S' || c == 'W' || c == 'E';
    }

    public boolean isDirection(String s) {
        return s.equals("N") || s.equals("S") || s.equals("W") || s.equals("E");
    }

    public void loadFromEncodedString(String encoded) {
        if (isDirection(encoded.charAt(0))) {
            switch (encoded.charAt(0)) {
                case ('N'):
                    this.enqueue(Direction.NORTH);
                    break;
                case ('S'):
                    this.enqueue(Direction.SOUTH);
                    break;
                case ('W'):
                    this.enqueue(Direction.WEST);
                    break;
                case ('E'):
                    this.enqueue(Direction.EAST);
                    break;
            }

            if(encoded.length() > 1) {
                loadFromEncodedString(encoded.substring(1));
            }

        } else {
            if (!isDigit(String.valueOf(encoded.charAt(0)))) {
                throw new IllegalArgumentException("Syntax Error");
            } else if (!(encoded.contains("[") && encoded.contains("]"))) {
                throw new IllegalArgumentException("Syntax Error");
            }

            MyStack<Character> brackets = new MyStack<>();
            boolean betweenBrackets = false;
            StringBuilder k = new StringBuilder();
            int open = -1;

            for (int i = 0; i < encoded.length(); i++) {
                char c = encoded.charAt(i);

                if (isDigit(String.valueOf(c)) && !betweenBrackets) {
                    k.append(encoded.charAt(i));

                } else if (c == '[') {
                    brackets.push(c);
                    if (!betweenBrackets) {
                        open = i;
                    }
                    betweenBrackets = true;

                } else if (c == ']') {
                    if (brackets.isEmpty()) {
                        throw new IllegalArgumentException("Syntax Error");
                    }

                    brackets.pop();

                    if (brackets.isEmpty() && i - open > 1) {
                        betweenBrackets = false;
                        //repeats j to k times
                        for (int j = 0; j < Integer.parseInt(String.valueOf(k)); j++) {
                            loadFromEncodedString(encoded.substring(open + 1, i));
                        }
                        k = new StringBuilder();
                    } else if (brackets.isEmpty() && i - open <= 1) {
                        throw new IllegalArgumentException("Syntax Error");
                    }

                } else if (!((isDirection(c) || isDigit(String.valueOf(c))) && betweenBrackets)) {
                    throw new IllegalArgumentException("Syntax Error");
                }

            }


        }
    }
}

