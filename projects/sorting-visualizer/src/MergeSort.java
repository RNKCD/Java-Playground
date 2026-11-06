class MergeSort<T extends Comparable<T>>
{
   private T[] list;
   
   public MergeSort(T[] l)
   {
      list = l;
   }
   
   public String toString()
   {
      //System.out.println(list.length);
      String rs = "";
      for(T item : list)
      {
         rs += item + ", ";
      }
      rs = rs.substring(0, rs.length()-2);
      return rs;
   }
   
   public T[] listForBridge(){ return list; }

   public void sort()
   {
      mergesort(list.length, list);
   }
   
   public void mergesort(int n, T[] S)
   {
      if(n > 1)
      {
         int h = n/2;
         int m = n - h;
         T[] u = (T[]) new Comparable[h];
         T[] v = (T[]) new Comparable[m];
         copy(0, h, S, u);
         copy(h, m, S, v);
         mergesort(h, u);
         mergesort(m, v);
         merge(h, m, u, v, S);
      }
   }
   
   public void copy(int s, int n, T[] a, T[] b)
   {
      int k=0;
      for(int i = s; i < n+s; i++)
      {
         b[k] = a[i];
         k++;
      }
   }
   
   public void merge(int h, int m, T[] u, T[] v, T[] S)
   {
      int i=0, j=0, k=0;
      
      while(i<h && j<m)
      {
         if(((T)u[i]).compareTo(v[j]) < 0)
         {
            S[k] = u[i];
            i++;
         } else 
         {
            S[k] = v[j];
            j++;
         }
         k++;
       }
       if(i>=h)
       {
         for(int p = j; p<m; p++)
         {
            S[k] = v[p];
            k++;
         }
       } else 
       {
         for(int p = i; p<h; p++)
         {
            S[k] = u[p];
            k++;
         }
       }
   }
}