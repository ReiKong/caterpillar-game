package assignment2;

public class Caterpillar extends MyDoublyLinkedList<Position>{
    public Caterpillar() {
        this.addFirst(new Position(7, 7));
    }

    public Position getHead() {
        return this.peekFirst();
    }

    public boolean isAdjacent(Position position) {
        int currX = this.getHead().getX();
        int currY = this.getHead().getY();
        boolean xBound = currX + 1 == position.getX() || currX == position.getX() || currX - 1 == position.getX();
        boolean yBound = currY + 1 == position.getY() || currY == position.getY() || currY - 1 == position.getY();

        return xBound && yBound;
    }

    public void eat(Position food) {
        if (!(isAdjacent(food))) {
            throw new IllegalArgumentException("Input position is not orthogonally adjacent to the current head position.");
        }

        this.addFirst(food);
    }

    public void move(Position input) {
        if (!isAdjacent(input)) {
            throw new IllegalArgumentException("Input position is not orthogonally adjacent to the current head position.");
        }

        this.addFirst(input);
        this.removeLast();
    }

    public boolean selfCollision(Position input) {
        return this.isElementOf(input);
    }
}
