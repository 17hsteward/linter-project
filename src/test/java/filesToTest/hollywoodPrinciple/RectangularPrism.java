package filesToTest.hollywoodPrinciple;

public class RectangularPrism extends RectangularPrismNoViolation{
    public RectangularPrism(int length, int width, int height){
        super(length,width,height);
    }
    @Override
    public int calculateSurfaceArea() {
        int area = (super.calculateFaceArea()+length*height+width*height);
        return area*area;
    }
}
