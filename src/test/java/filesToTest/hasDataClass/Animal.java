package filesToTest.hasDataClass;

public class Animal {
    private String name;
    private String id;

    public Animal(String id, String name){
        this.id = id;
        this.name = name;
    }

    public void changeName(String name){
        this.name = name;
    }

    public String getID(){
        return this.id;
    }

}
