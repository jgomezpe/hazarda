package hazarda.integer;

public class Constant implements Random{
    protected int v;
    
    public Constant(int v) { this.v = v; }
    
    @Override
    public int next() { return v; }
}