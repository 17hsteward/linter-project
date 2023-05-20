package filesToTest.hollywoodPrinciple;

public class Square implements Shape {
    @Override
    public int calculateSurfaceArea() {
        return Shape.calculateArea()*Shape.calculateArea();
    }
}
