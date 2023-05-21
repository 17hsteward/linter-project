package filesToTest.hollywoodPrinciple;

public class Number {
    private final int amount;
    private Multiplier multiplier;
    public Number(Multiplier multiplier){
        this.multiplier = multiplier;
        this.amount = 2;
    }
    public void multiply(){
        multiplier.multiply();
    }
    public int getAmount(){
        return this.amount;
    }
}
