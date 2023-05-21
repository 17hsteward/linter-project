package filesToTest.adapterPattern;

public class AbstractAdapter extends AbstractTarget {
    private AbstractAdaptee abstractAdaptee;
    public void call(){
        abstractAdaptee.speak();
    }
}
