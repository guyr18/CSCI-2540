package assg5_guyr18;

public abstract class KeyedItem<String extends Comparable<? super String>> 
{

   private String searchKey;

   public KeyedItem(String key) 
   {
	   
       searchKey = key;
       
   }

   public String getKey() 
   {
	   
       return searchKey;
       
   }
}