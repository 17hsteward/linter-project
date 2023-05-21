package filesToTest.hollywoodPrinciple;

public abstract class ShapeClass {
     int height;
     int length;
     int width;

    public ShapeClass(int length,int width,int height){
        this.length = length;
        this.width = width;
        this.height = height;
    }
    public int calculateFaceArea(){
        return length*width;
    }
    abstract int calculateSurfaceArea();

}
