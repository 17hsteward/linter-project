package filesToTest.hollywoodPrinciple;

public class NumberNoViolation {
    private final int amount;
    private MultiplierNoViolation multiplier;
    public NumberNoViolation(MultiplierNoViolation multiplier){
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
