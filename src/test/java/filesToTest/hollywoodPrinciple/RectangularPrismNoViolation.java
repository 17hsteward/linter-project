package filesToTest.hollywoodPrinciple;

public class RectangularPrismNoViolation extends ShapeClass {

    public RectangularPrismNoViolation(int length, int width,int height){
        super(length,width,height);
    }
    @Override
    public int calculateSurfaceArea() {
        int area = (width*length+height*length+height*width);
        return area*area;
    }
}
