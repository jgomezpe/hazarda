package hazarda.integer;

public class FromReal implements Random{
    protected hazarda.real.Random real;
    
    public FromReal(hazarda.real.Random real) { this.real = real; }
    
    @Override
    public int next() { return (int)(real.next()+0.5); }
}
