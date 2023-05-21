package filesToTest.hollywoodPrinciple;

public class CubeNoViolation implements Shape{
    private final int length;
    private final int width;

    public CubeNoViolation(int length, int width){
        this.length = length;
        this.width = width;
    }
    @Override
    public int calculateSurfaceArea() {
        int area = length*width;
        return area*area;
    }
}
