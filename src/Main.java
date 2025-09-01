public record Main(Robot robot, Table table) {

    public static void main(String[] args) {
        Table table = new Table(5, 5);
        Robot robot = new Robot();
        Main app = new Main(robot, table);

    }

}