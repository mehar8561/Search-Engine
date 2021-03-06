package algorithms;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;




public class QPHashTable2<AnyType>
{
 
 public QPHashTable2( )
 {
     this( DEFAULT_TABLE_SIZE );
 }

 
 public QPHashTable2( int size )
 {
     allocateArray( size );
     doClear( );
 }


 public boolean insert( AnyType x , String file_name)
 {
        
     int currentPos = findPos( x );
     HashEntry<AnyType> b = array[currentPos];
	if( isActive( currentPos )) {
		b.freq += 1;
		if (!b.table.containsKey(file_name)) {

			b.table.put(file_name, 1);}
		else {
			int new_freq = Integer.parseInt(b.table.get(file_name).toString());
			if (x.equals("from")) System.out.println(new_freq);
			b.table.replace(file_name,  new_freq+1);}

         return false;}

	array[currentPos] = new HashEntry<>( x, true, file_name);

     theSize++;

       
     if( ++occupied > array.length / 2 )
         rehash( file_name);
     index_arr.add(currentPos);
     return true;
 }


 private void rehash( String file_name)
 {
     HashEntry<AnyType> [ ] oldArray = array;
   
     allocateArray( 2 * oldArray.length );
     occupied = 0;
     theSize = 0;


     for( HashEntry<AnyType> entry : oldArray )
         if( entry != null && entry.isActive )
             insert( entry.element , file_name);
 }

 
 private int findPos( AnyType x )
 {
     int offset = 1;
     int currentPos = myhash( x );

     while( array[ currentPos ] != null &&
             !array[ currentPos ].element.equals( x ) )
     {
         currentPos += offset;  // Compute ith probe
         offset += 2;
         if( currentPos >= array.length )
             currentPos -= array.length;
     }

     return currentPos;
 }

 
 public boolean remove( AnyType x )
 {
     int currentPos = findPos( x );
     if( isActive( currentPos ) )
     {
         array[ currentPos ].isActive = false;
         theSize--;
         return true;
     }
     else
         return false;
 }


 public int size( )
 {
     return theSize;
 }

 
 public int capacity( )
 {
     return array.length;
 }


 public boolean contains( AnyType x )
 {
     int currentPos = findPos( x );
     return isActive( currentPos );
 }

 
 private boolean isActive( int currentPos )
 {
     return array[ currentPos ] != null && array[ currentPos ].isActive;
 }

 public void makeEmpty( )
 {
     doClear( );
 }

 private void doClear( )
 {
     occupied = 0;
     for( int i = 0; i < array.length; i++ )
         array[ i ] = null;
 }

 private int myhash( AnyType x )
 {
     int hashVal = x.hashCode( );

     hashVal %= array.length;
     if( hashVal < 0 )
         hashVal += array.length;

     return hashVal;
 }


 public int return_freq( AnyType x) {
	 int currentPos = findPos( x );
	 if (isActive( currentPos )){
		 HashEntry<AnyType> b = array[currentPos];
		 int freq = array[currentPos].freq;
		 return freq;
	 }
	 return -1;
 }

 public HashEntry return_obj( AnyType x) {
	 int currentPos = findPos( x );
	 if (isActive( currentPos )){
		 return array[currentPos];
	 }
	 return null;
 }

 public static class HashEntry<AnyType>
 {
     public AnyType  element;  
     public boolean isActive;  
     public String filename;
     public int freq;
     public Map table;
     public ArrayList display_file;


     public HashEntry( AnyType e, String file_name)
     {
         this( e, true, file_name);
     }

     public HashEntry( AnyType e, boolean i, String file_name)
     {
    	 Map<String, Integer> table2 = new Hashtable<>();
         element  = e;
         isActive = i;
         freq = 1;
         table = table2;
         table.put(file_name,1);
     }
 }

 private static final int DEFAULT_TABLE_SIZE = 19999999;

 public HashEntry<AnyType> [ ] array; 
 private int occupied;                 
 private int theSize;                 
 public ArrayList<Integer> index_arr = new ArrayList<>();

 
 private void allocateArray( int arraySize )
 {
     array = new HashEntry[ nextPrime( arraySize ) ];
 }

 
 private static int nextPrime( int n )
 {
     if( n % 2 == 0 )
         n++;

     for( ; !isPrime( n ); n += 2 )
         ;

     return n;
 }

 /**
  * Internal method to test if a number is prime.
  * Not an efficient algorithm.
  * @param n the number to test.
  * @return the result of the test.
  */
 private static boolean isPrime( int n )
 {
     if( n == 2 || n == 3 )
         return true;

     if( n == 1 || n % 2 == 0 )
         return false;

     for( int i = 3; i * i <= n; i += 2 )
         if( n % i == 0 )
             return false;

     return true;
 }



public static void main( String [ ] args )
{

   final int NUMS = 2000000;

   System.out.println( "Fill in the table..." );

   QPHashTable2<String> H = new QPHashTable2<>( );

   for( int i = 0; i < NUMS; i++)
       H.insert( ""+i , "aa.txt");

   for( int i = 0; i < NUMS; i++)
       H.contains( ""+i );

   for( int i = 0; i < NUMS; i++)
       H.remove( ""+i );

   System.out.println( "Finishing... ");
}

}
