package filesToTest.adapterPattern;

public class Adapter implements Duck{
    public Adaptee adaptee;
    private String name;
    public void adapt(){
        adaptee.write();
    }
}
