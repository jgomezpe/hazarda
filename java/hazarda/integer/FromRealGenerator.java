package hazarda.integer;

public class FromRealGenerator implements Random{
	protected hazarda.real.Random real;
	
	public FromRealGenerator(hazarda.real.Random real) { this.real = real; }

	@Override
	public int next() { return (int)Math.floor(real.next()+0.5); }
}