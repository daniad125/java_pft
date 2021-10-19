package ru.stqa.pft.sandbox;

public class PointProgram {
  public static void main(String[] args) {
    Point p1 = new Point(1,1);
    Point p2 = new Point(4,5);
    System.out.println("Расстояние от точки ("+p1.x+","+p1.y+") до точки ("+p2.x+","+p2.y+")="+distance(p1,p2));
  }
  public static double distance (Point p1, Point p2) {
    return Math.sqrt(Math.pow(p1.x- p2.x,2)+ Math.pow(p1.y-p2.y,2));
  }
}
