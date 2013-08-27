
public class PercolationStats {
	private final int size;
	private final int num_of_exp;
	private double results[];
	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;
	public PercolationStats(int N, int T) throws IllegalArgumentException{
		
		if(N<=0 || T<=0)
			throw new IllegalArgumentException("Illegal arguments");
		size=N;
		num_of_exp=T;
		results= new double[num_of_exp];
		Percolation pn;
		System.out.println("Percolation demo");
		System.out.println("Grid Size is "+size +" X "+size);
		int row,column,sites_opened=0;
		
		for (int i = 0; i < num_of_exp; i++) {
		sites_opened=0;
		pn=new Percolation(size);
		while(!pn.percolates()){
			row=StdRandom.uniform(pn.getN());
			column=StdRandom.uniform(pn.getN());
			if(pn.isFull(row, column)){
			pn.open(row, column);
			sites_opened++;
			//System.out.println("sites opened "+sites_opened);
			}	
		}
		
		results[i]=(double)sites_opened/(size*size);
		//System.out.println(pn.getGrid(7,3));
	}
		
		//System.out.println("Number of sites opened for percolation "+sites_opened);
		//System.out.println(pn.getGrid(7,3));	//grid[7][3]
											// perform T independent computational experiments on an N-by-N grid
	}
	   public double mean(){
		   mean=StdStats.mean(results);
		   
		   return mean;											// sample mean of percolation threshold
	   }
	   
	  
	   public double stddev(){
		  stddev=StdStats.stddev(results);
		   return stddev;											// sample standard deviation of percolation threshold
	   }
	   public double confidenceLo(){
		  
		   confidenceLo=mean-((1.96*Math.sqrt(stddev))/num_of_exp);
		   return confidenceLo;											// returns lower bound of the 95% confidence interval
	   }
	   public double confidenceHi() {
		   
		   confidenceHi=mean+((1.96*Math.sqrt(stddev))/num_of_exp);
		   return confidenceHi;										// returns upper bound of the 95% confidence interval
	   }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		PercolationStats pobj=new PercolationStats(N,T);
		/*
		for (int j = 0; j < 50; j++) {
			System.out.println("results"+pobj.results[j]);  //+" no of exp "+T
			
		}
		*/
		System.out.println("mean                    ="+pobj.mean());
		System.out.println("stddev                  ="+pobj.stddev());
		System.out.println("95% confidence interval ="+pobj.confidenceLo()+" , "+pobj.confidenceHi());
		
	}

}
