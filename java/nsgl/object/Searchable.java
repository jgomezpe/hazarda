package nsgl.object;

public interface Searchable {
    /**
     * Determines if the given object belongs to the collection
     * @param data Data object to be located
     * @return <i>true</i>If the object belongs to the collection, <i>false</i> otherwise
     */
    default boolean contains( Object data ) { return find(data)!=null; } 
    
    Object find(Object data);

    /**
     * Cast an object to Comparable, if possible
     * @param obj Object to be casted to Comparable
     * @return A comparable version of the given object, <i>null</i> otherwise
     */
    static Searchable cast( Object obj ){
	if( obj instanceof Searchable ) return (Searchable)obj;
	if( obj instanceof Iterable) { 
	    @SuppressWarnings("rawtypes")
	    Iterable iter = (Iterable)obj;
	    return new Searchable() {
	        @Override
	        public Object find(Object data) {
	            int i=0;
	            Comparable c = Comparable.cast(data);
	            for( Object x:iter ) {
	        	if( c.eq(x) ) return i;
	        	i++;
	            }
	            return null;
	        }
	    };
	}    
	return null;
    }
}