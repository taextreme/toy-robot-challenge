// Record class representing a coordinate pair (X, Y).
public record Position(int x, int y) {

    // Calculate Next Position based on the direction.
    public Position move(Direction direction) {
        return new Position(this.x + direction.getDx(), this.y + direction.getDy());
    }
}