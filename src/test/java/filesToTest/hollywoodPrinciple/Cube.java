package filesToTest.hollywoodPrinciple;

public class Cube implements Shape {
    @Override
    public int calculateSurfaceArea() {
        return Shape.calculateFaceArea()*Shape.calculateFaceArea();
    }
}
