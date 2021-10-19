package ru.stqa.pft.sandbox;

public class PointProgram {
  public static void main(String[] args) {
    Point p1 = new Point(1,1,4,5);
    System.out.println("Расстояние от точки ("+p1.x1+","+p1.y1+") до точки ("+p1.x2+","+p1.y2+")="+p1.distance());
  }

}
