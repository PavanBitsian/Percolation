
public class Percolation {
	private WeightedQuickUnionUF obj;
	private int N;
	private int[][] grid;
	private final int OPEN = 1;
	private final int FULL = 0;
	public Percolation(int n){
	N=n;
	grid= new int[N][N];
	obj=new WeightedQuickUnionUF((N*N) + 2);
	}
	
	public int getN(){
		return this.N;
	}
	
	public int getGrid(int i,int j) {
		return grid[i][j];
	}

	public void open(int i, int j) {
		if(i<0 || i>N || j<0 || j> N)   
			throw new ArrayIndexOutOfBoundsException("Illegal arguments");
		grid[i][j]=OPEN;
		if(i==0)//row is 0
			obj.union(0,(i*N)+j);			// open site (row i, column j) if it is not already
	    if(i==N-1)
	    	obj.union((N*N)+1,(i*N)+j);
	    if((i+1)<N)
	    {
	    	if(isOpen(i+1,j)){    		
	    		obj.union((i*N+j), ((i+1)*N)+j);
	    	}
	    }
	    if((i-1)>=0)
	    {	
	    	if(isOpen(i-1,j)){    		
	    		obj.union((i*N+j), ((i-1)*N)+j);
	    	}
	    }
	    if((j+1)<N)
	    {	
	    	if(isOpen(i,j+1)){    		
	    		obj.union((i*N+j), (i*N)+j+1);
	    	}
	    }
	    if((j-1)>=0)
	    {
	    	if(isOpen(i,j-1)){    		
	    		obj.union((i*N+j), (i*N)+j-1);
	    	}
	    	
	    }
	
	}
	
	public boolean isOpen(int i, int j) {
		if(i<0 || i>N || j<0 || j> N)   
			throw new ArrayIndexOutOfBoundsException("Illegal arguments");
		else return grid[i][j]==OPEN;					// is site (row i, column j) open?
	}
	
	public boolean isFull(int i, int j){
		if(i<0 || i>N || j<0 || j> N)   
			throw new ArrayIndexOutOfBoundsException("Illegal arguments");
		else return grid[i][j]==FULL;
	}
	public boolean percolates()      {
			
		   return obj.connected(0, (N*N)+1);										// does the system percolate?
	   }
	
	
	
}
