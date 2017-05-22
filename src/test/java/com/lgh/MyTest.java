package com.lgh;

import java.util.Calendar;

import org.junit.Test;

public class MyTest {
    @Test
    public void test1(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        for(int i=0;i<3;i++){
           //c.set(Calendar.DAY_OF_MONTH, 1);
           //System.out.println("++"+c.get(Calendar.MONTH));
           //System.out.println(c.get(Calendar.MONTH)+"++");
          // if(i<2){
           c.add(Calendar.MONTH, 1);
           System.out.println(c.get(Calendar.MONTH)+"++");
           //}
           //System.out.println(String.valueOf(c.get(Calendar.MONTH)));
        }
    }
    
    
    
}
