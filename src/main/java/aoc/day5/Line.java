package aoc.day5;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Line {
    private final Point startPoint;
    private final Point endPoint;

    boolean isVerticalOrHorizontal() {
        return isVertical() || isHorizontal();
    }

    boolean isVertical() {
        return (startPoint.getY() == endPoint.getY());
    }

    boolean isHorizontal() {
        return (startPoint.getX() == endPoint.getX());
    }

    boolean isDiagonal() {
        return !isVerticalOrHorizontal();
    }

}
