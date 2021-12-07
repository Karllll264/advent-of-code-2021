package aoc.day5;

import lombok.experimental.UtilityClass;

@UtilityClass
class Utils {

    static Line prepareLine(String line) {
        String[] points = line.split(" -> ");
        Point startPoint = new Point(points[0].split(",")[0], points[0].split(",")[1]);
        Point endPoint = new Point(points[1].split(",")[0], points[1].split(",")[1]);
        return new Line(startPoint, endPoint);
    }

    static void handleHorizontal(Line line, int[][] ocean) {
        if(line.getStartPoint().getY() < line.getEndPoint().getY()) {
            for(int y = line.getStartPoint().getY(); y <= line.getEndPoint().getY(); y++) {
                ocean[line.getStartPoint().getX()][y]++;
            }
        } else {
            for(int y = line.getStartPoint().getY(); y >= line.getEndPoint().getY(); y--) {
                ocean[line.getStartPoint().getX()][y]++;
            }
        }
    }

    static void handleVertical(Line line, int[][] ocean) {
        if(line.getStartPoint().getX() < line.getEndPoint().getX()) {
            for(int x = line.getStartPoint().getX(); x <= line.getEndPoint().getX(); x++) {
                ocean[x][line.getStartPoint().getY()]++;
            }
        } else {
            for(int x = line.getStartPoint().getX(); x >= line.getEndPoint().getX(); x--) {
                ocean[x][line.getStartPoint().getY()]++;
            }
        }
    }

    static int calculateOverlappingPoints(int [][]ocean) {
        int danger = 0;
        for (int[] rows : ocean) {
            for (int cellValue : rows) {
                if (cellValue > 1) {
                    danger++;
                }
            }
        }
        return danger;
    }

}
