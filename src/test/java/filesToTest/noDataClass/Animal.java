package filesToTest.noDataClass;

public class Animal{

    public String name;
    public boolean isDead;

    public Animal(String name){
        this.name = name;
        this.isDead = this.isDead();
    }

    private boolean isDead(){
        if(1+1==2){
            return false;
        }
        return true;
    }
}