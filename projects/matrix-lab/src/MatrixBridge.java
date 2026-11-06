public class MatrixBridge {
 public String multiply(String left,String right,int n){if(n!=2&&n!=4)throw new IllegalArgumentException("Only 2x2 and 4x4 supported");int[][] a=parse(left,n),b=parse(right,n);StrassenMultiplication alg=new StrassenMultiplication();int[][] c=alg.strassenmultiply(a,b);StringBuilder out=new StringBuilder();out.append(alg.multiplication_count()).append("#");for(int i=0;i<n;i++)for(int j=0;j<n;j++){if(i!=0||j!=0)out.append(",");out.append(c[i][j]);}return out.toString();}
 private int[][] parse(String csv,int n){String[] v=csv.split(",");if(v.length!=n*n)throw new IllegalArgumentException("Expected "+(n*n)+" numbers per matrix");int[][] a=new int[n][n];for(int i=0;i<v.length;i++)a[i/n][i%n]=Integer.parseInt(v[i].trim());return a;}
}
