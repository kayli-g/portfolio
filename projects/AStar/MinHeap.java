package astar;

import java.util.ArrayList;

public class MinHeap{

   private static ArrayList<Integer> heap;

   public static void main(String[] args){
      
      heap = new ArrayList<Integer>();
      heap.add(new Integer(-1));
      
      addValue(5);
      addValue(15);
      addValue(23);
      addValue(9);
      addValue(1);
      addValue(51);
      addValue(18);
      addValue(28);
      addValue(95);
      addValue(12);
      
      
      remove();
      remove();
      remove();
      remove();
      remove();
   }
  
   public static void remove(){
      //value at root of heap
      System.out.println("Removing value: " + heap.get(1).intValue());
      
      //index to the last element in the heap
      int index = heap.size() - 1;
   
      //copy last element to the root
      heap.set(1, heap.get(index));
      
      //delete the last element
      heap.remove(index);
   
      //set index to root
      index = 1;
      
      //while there is a left child of index
      while(index * 2 < heap.size()){
         //store value of children
         int leftChild = heap.get(index * 2).intValue();
         
         //so parent doesn't swap with right child if it doesn't exist
         int rightChild = -1;
         
         //does index have a right child?
         if((index * 2) + 1 < heap.size()){
            rightChild = heap.get((index * 2) + 1).intValue();
         }
         
         //is parent less than the larger of the two children
         
         // changed to min
         if(heap.get(index).intValue() > Math.min(leftChild, rightChild)){
            //swap with left child
            
            //swapped
            if(leftChild < rightChild){
               Integer temp = heap.get(index * 2);
               heap.set(index * 2, heap.get(index));
               heap.set(index, temp);
               index = index * 2;
            }else{   //swap with right child
               Integer temp = heap.get((index * 2) + 1);
               heap.set((index * 2) + 1, heap.get(index));
               heap.set(index, temp);
               index = (index * 2) + 1;
            }
         }else{
            break;
         }
      }
   }
   public static void addValue(int n){
      //wrap int value inside Integer obj
      Integer value = new Integer(n);
  
      //add value to next position in heap
      heap.add(value);
      
      //index to the value we just added
      int index = heap.size() - 1;
      
      while(index > 1){
         //get value of parent and child
         int childValue = heap.get(index).intValue();
         int parentValue = heap.get(index / 2).intValue(); 
  
         //determine if swap needs to occur
         if(parentValue > childValue){
            //swap values
            Integer temp = heap.get(index / 2);
            heap.set(index / 2, heap.get(index));
            heap.set(index, temp);
            index = index / 2;
         }else{
            //no swap
            break;
         }
      }  
   }
}