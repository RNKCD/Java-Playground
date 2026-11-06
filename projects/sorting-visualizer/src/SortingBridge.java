import java.util.*;
public class SortingBridge {
  private String[] steps=new String[0];
  public int prepare(String method,String csv){String trace=run(method,csv);steps=trace.split("\n");return steps.length;}
  private String[] fields(int step){return steps[step].split("\\|",4);}
  public int stepCount(){return steps.length;}
  public int arrayLength(){return steps.length==0?0:fields(0)[3].split(",").length;}
  public int valueAt(int step,int index){return Integer.parseInt(fields(step)[3].split(",")[index]);}
  public int highlightA(int step){return Integer.parseInt(fields(step)[1]);}
  public int highlightB(int step){return Integer.parseInt(fields(step)[2]);}
  public int actionCode(int step){String a=fields(step)[0];return a.equals("Done")?5:a.equals("Swap")?2:a.equals("Compare")?1:a.equals("Start")?0:3;}
  public String run(String method, String csv) {
    String[] tokens=csv.trim().split(","); if(tokens.length<1||tokens.length>24) throw new IllegalArgumentException("Use 1–24 numbers");
    int[] a=new int[tokens.length]; for(int i=0;i<a.length;i++) a[i]=Integer.parseInt(tokens[i].trim());
    int[] original=a.clone(); StringBuilder trace=new StringBuilder(); record(trace,a,-1,-1,"Start");
    if(method.equals("bubble")){for(int i=0;i<a.length;i++)for(int j=0;j<a.length-i-1;j++){record(trace,a,j,j+1,"Compare");if(a[j]>a[j+1]){swap(a,j,j+1);record(trace,a,j,j+1,"Swap");}} BubbleSort.bubbleSort(original);}
    else if(method.equals("exchange")){for(int i=0;i<a.length;i++)for(int j=i+1;j<a.length;j++){record(trace,a,i,j,"Compare");if(a[i]>a[j]){swap(a,i,j);record(trace,a,i,j,"Swap");}}ExchangeSort.exchangeSort(original);}
    else if(method.equals("selection")){for(int i=0;i<a.length;i++){int m=i;for(int j=i+1;j<a.length;j++){record(trace,a,m,j,"Compare");if(a[j]<a[m])m=j;}if(m!=i){swap(a,i,m);record(trace,a,i,m,"Swap");}}SelectionSort.selectionSort(original);}
    else if(method.equals("insertion")){for(int i=1;i<a.length;i++){int v=a[i],j=i-1;while(j>=0&&a[j]>v){record(trace,a,j,j+1,"Compare");a[j+1]=a[j];record(trace,a,j,j+1,"Shift");j--;}a[j+1]=v;record(trace,a,j+1,-1,"Insert");}InsertionSort.insertionSort(original);}
    else if(method.equals("merge")){merge(a,0,a.length,trace);new MergeSortAlgorithm(); MergeSort<Integer> m=new MergeSort<>(Arrays.stream(original).boxed().toArray(Integer[]::new));m.sort();original=Arrays.stream(m.listForBridge()).mapToInt(Integer::intValue).toArray();}
    else if(method.equals("quick")){quick(a,0,a.length-1,trace);Arrays.sort(original);}
    else throw new IllegalArgumentException("Unknown algorithm");
    if(!Arrays.equals(a,original))throw new IllegalStateException("Trace differs from Java algorithm");
    record(trace,a,-1,-1,"Done");return trace.toString();
  }
  private void swap(int[] a,int i,int j){int t=a[i];a[i]=a[j];a[j]=t;}
  private void record(StringBuilder s,int[] a,int i,int j,String label){if(s.length()>0)s.append("\n");s.append(label).append("|").append(i).append("|").append(j).append("|");for(int k=0;k<a.length;k++){if(k>0)s.append(",");s.append(a[k]);}}
  private void merge(int[] a,int lo,int hi,StringBuilder t){if(hi-lo<=1)return;int mid=(lo+hi)/2;merge(a,lo,mid,t);merge(a,mid,hi,t);int[] tmp=Arrays.copyOfRange(a,lo,hi);int i=0,j=mid-lo,k=lo;while(i<mid-lo&&j<tmp.length){record(t,a,lo+i,lo+j,"Compare");a[k++]=tmp[i]<=tmp[j]?tmp[i++]:tmp[j++];record(t,a,k-1,-1,"Write");}while(i<mid-lo){a[k++]=tmp[i++];record(t,a,k-1,-1,"Write");}while(j<tmp.length){a[k++]=tmp[j++];record(t,a,k-1,-1,"Write");}}
  private void quick(int[] a,int lo,int hi,StringBuilder t){if(lo>=hi)return;int pivot=lo,j=lo;for(int i=lo+1;i<=hi;i++){record(t,a,pivot,i,"Compare");if(a[i]<a[pivot]){j++;swap(a,i,j);record(t,a,i,j,"Swap");}}swap(a,j,pivot);record(t,a,j,pivot,"Pivot placed");quick(a,lo,j-1,t);quick(a,j+1,hi,t);}
}
