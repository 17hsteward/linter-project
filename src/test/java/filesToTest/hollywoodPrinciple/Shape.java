package filesToTest.hollywoodPrinciple;

public interface Shape {
    int length = 15;
    int width = 20;
    static int calculateFaceArea(){
        return length*width;
    }
    int calculateSurfaceArea();

}
