package assignment2;

//import javafx.geometry.Pos;

public class World {
    private Caterpillar caterpillar;
    private Position currFoodPos;
    private Region region;
    private ActionQueue actions;
    private TargetQueue food;
    private GameState state;

    public World(TargetQueue food, ActionQueue actions) {
        this.food = food;
        this.actions = actions;
        this.region = new Region(0, 0, 15, 15);
        this.caterpillar = new Caterpillar();
        this.currFoodPos = food.dequeue();
        this.state = GameState.MOVE;
    }

    public GameState getState() {
        return state;
    }

    public Caterpillar getCaterpillar() {
        return caterpillar;
    }

    public Position getFood() {
        return currFoodPos;
    }

    public boolean isRunning() {
        return this.getState() == GameState.MOVE || this.getState() == GameState.EAT;
    }

    public void step() {
        //System.out.println(this.caterpillar.getHead().xCoord + " " + this.caterpillar.getHead().yCoord);
        //System.out.println(this.actions.storage.peekFirst());
        //System.out.println(this.state);
        //System.out.println("-----------------------------------------------");
        if (this.actions.isEmpty()) {
            this.state = GameState.NO_MORE_ACTION;
        }

        if (!(this.isRunning())) {
            return;
        }

        Direction nextDirection = this.actions.dequeue();
        Position head = this.caterpillar.getHead();
        Position newHead = new Position(head);


        if (nextDirection.equals(Direction.NORTH)) {
            newHead.moveNorth();
        } else if (nextDirection.equals(Direction.SOUTH)) {
            newHead.moveSouth();
        } else if (nextDirection.equals(Direction.EAST)) {
            newHead.moveEast();
        } else { // WEST
            newHead.moveWest();
        }

        // CASE 1: OUT OF THE MAP
        if (!this.region.contains(newHead)) {
            this.state = GameState.WALL_COLLISION;
            // CASE 2: SELF-COLLISION
        } else if (this.caterpillar.selfCollision(newHead)) {
            this.state = GameState.SELF_COLLISION;
            // CASE 3: FOOD
        } else if (this.currFoodPos.equals(newHead)) {
            head = newHead;
            this.caterpillar.eat(head);
            if (this.food.isEmpty()) {
                this.state = GameState.DONE;
            } else {
                this.currFoodPos = this.food.dequeue();
                this.state = GameState.EAT;
            }
            // CASE 4: MOVE
        } else {
            head = newHead;
            this.caterpillar.move(head);
            this.state = GameState.MOVE;
        }
    }
}
