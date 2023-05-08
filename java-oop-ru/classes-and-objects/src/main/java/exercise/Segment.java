package exercise;

// BEGIN
class Segment {
    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getBeginPoint() {
        return start;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getMidPoint() {
        int x = (start.getX() + end.getX())/2;
        int y = (start.getY() + end.getY())/2;
        return new Point(x, y);
    }
}
// END
