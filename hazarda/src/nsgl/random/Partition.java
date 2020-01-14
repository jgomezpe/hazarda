/**
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */
package nsgl.random;

/**
 * <p>Title: Partition</p>
 *
 * <p>Description: Random partition of a set.</p>
 *
 */
public class Partition {
    /**
     * Number of elements in the set being partitioned
     */
    protected int n = 0;

    /**
     * <P>Starting positions of each group.</P>
     * <P>start[k] indicates the initial index of group k</P>
     */
    protected int[] start = new int[] {0, 0};

    /**
     * Random distribution of index
     */
    protected int[] index = null;

    /**
     * Constructor: Default constructor. Useful for inheritance
     */
    protected Partition() {}
    
    /**
     * Creates a set of values of size <i>m</i> from <i>n</i> possible values. If required (<i>n less than m</i>) values are repeated 
     * @param n Group size
     * @param m Number of elements to be generated
     * @param shuffling If the values must be randomly selected
     * @return A set of values of size <i>m</i> from <i>n</i> possible values. If required (<i>n less than m</i>) values are repeated
     */
    public static int[] create(int n, int m, boolean shuffling){
        int[] index = new int[n];
        for (int i = 0; i < n; i++) 
            index[i] = i;
        
        if (shuffling) Shuffle.apply(index);
        
        if( n<m ){
            int[] index2 = new int[m];
            for( int i=0; i<m; i++ ){
                index2[i] = index[i%n];
            }
            index = index2;
        }
        return index;
    }

    /**
     * Creates a partition on <i>n</i> natural numbers [0,n)
     * @param n Size of the set being partitioned
     * @param m Number of groups defining the partition
     * @param shuffling If the set of integers should be shuffled before partitioning it or not
     */
    public Partition(int n, int m, boolean shuffling){ this( create(n,m,shuffling), m); }

    /**
     * Constructor: Creates a partition of a randomized set in m random groups
     * @param idx Randomized Set. The data set already permuted
     * @param m Number of groups
     */
    public Partition(int[] idx, int m) {
        index = idx;
        n = index.length;
        if(n<m){
            idx = new int[m];
            for( int i=0; i<m; i++ ){
                idx[i] = index[i%n];
            }
            index = idx;
        }
        start = new int[m + 1];
        start[0] = 0;
        start[m] = n;
        for (int k = 1; k < m; k++) {
            start[k] = n * k / m;
        }
    }

    /**
     * Constructor: Creates a partition of a randomized set and the size of groups
     * @param index1 Randomized Set. The data set already permuted
     * @param groupsSize Size of groups
     */
    public Partition(int[] index1, int[] groupsSize) {
        index = index1;
        n = index.length;
        int m = groupsSize.length;
        start = new int[m + 1];
        start[0] = 0;
        for (int i = 0; i < m; i++) {
            start[i + 1] = start[i] + groupsSize[i];
        }
        n = start[m];
    }

    /**
     * Returns the elements in the set that does not belongs to the <i>k-th</i> group
     * @param k The group to be skipped
     * @return The set without the elements in the <i>k-th</i> group
     */
    public int[] skipGroup(int k) {
        int length = n - groupSize(k);
        int[] an = new int[length];
        System.arraycopy(index, 0, an, 0, start[k]);
        System.arraycopy(index, start[k+1], an, start[k], n-start[k+1]);
        return an;
    }

    /**
     * Returns the elements in the set that belongs to the <i>k-th</i> group according to the random partition
     * @param k the group to be returned
     * @return The elements in the <i>k-th</i> group
     */
    public int[] getGroup(int k) {
        int length = groupSize(k);
        int[] an = new int[length];
        System.arraycopy(index, start[k], an, 0, length);
        return an;
    }

    /**
     * Calculates the size of a group in the partition
     * @param k The group to be analyzed
     * @return Size of the <i>k-th</i> group in the partition
     */
    public int groupSize(int k){ return start[k + 1] - start[k]; }

    /**
     * Return the number of groups in the partition
     * @return Number of groups in the partition
     */
    public int size(){ return start.length - 1; }

    /**
     * Return the random distribution of index
     * @return Random distribution of index
     */
    public int[] get(){ return index; }
}