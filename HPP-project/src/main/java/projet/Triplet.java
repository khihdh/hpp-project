package projet;


/**
 * 
 * a data structure containing 3 values.
 *
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public class Triplet<T1, T2,T3> {

	private T1 val1;
    private T2 val2;
    private T3 val3;

    public Triplet(T1 v1, T2 v2, T3 v3) {
        this.val1 = v1;
        this.val2 = v2;
        this.val3 = v3;
    }

    public T1 getVal1() {
        return this.val1;
    }

    public T2 getVal2() {
        return this.val2;
    }
    
    public T3 getVal3() {
    	return this.val3;
    }
    
    public void setVal1(T1 v1) {
    	this.val1 = v1 ;
    }
    
    public void setVal2(T2 v2) {
    	this.val2 = v2 ;
    }
    
    public void setVal3(T3 v3) {
    	this.val3 = v3 ;
    }
}
