package hazarda.real;

public class Constant implements Random{
    protected double v;
    
    public Constant(double v) { this.v = v; }
    
    @Override
    public double next() { return v; }
}