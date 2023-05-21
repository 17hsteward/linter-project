package filesToTest.adapterPattern;

public class AnotherAdapter implements Duck{
    public Turkey adaptee;
    private String name;
    public void makeSound(){
        adaptee.gobble();
    }
}
