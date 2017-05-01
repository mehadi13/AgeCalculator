package learn.mehadihossain.agecalculator;

import android.widget.Toast;

/**
 * Created by Mehadi Hossain on 5/1/2017.
 */

public class AgeCalculate {
    int birthDay,birthMonth,birthYear;
    int toDay,toMonth,toYear;
    boolean isCalculatable;

    public void  setBirth(int birthDay, int birthMonth, int birthYear) {
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
    }

    public void setTo(int toDay, int toMonth, int toYear) {
        this.toDay = toDay;
        this.toMonth = toMonth;
        this.toYear = toYear;
    }

    public int[] CalculateAge() {
        int date = 0, mon = 0, year = 0;

        /*if (1 < birthDay && 1 < birthMonth) {
            date = ((month[birthMonth - 1] + 01) - birthDay);
            mon = (12 - birthMonth);
            year = (toYear - birthYear);
        } else if (1 < birthDay && 1 >= birthMonth) {
            date = ((month[birthMonth - 1] + 01) - birthDay);
            mon = (12 - birthMonth);
            year = (toYear - birthYear);
        } else if (birthDay == 1 && 1 < birthMonth) {
            date = 1 - birthDay;
            mon = (13 - birthMonth);
            year = (toYear - birthYear);
        } else if (birthDay == 1 && birthMonth == 1) {
            date = 1 - birthDay;
            mon = (13 - birthMonth);
            year = (toYear - birthYear);
        }*/

        if(toYear>birthYear){
            isCalculatable = true;
        }else
        if(toYear==birthYear){
          //  System.out.println("equal");
            if(toMonth>birthMonth){
                isCalculatable = true;
              //  System.out.println("greater");
            }else if(toMonth==birthMonth){
               // System.out.println("month equal");
                if(toDay>birthDay){
                    isCalculatable = true;
                //    System.out.println("month greater");
                }else {
                    isCalculatable = false;
                }
            }else {
                isCalculatable = false;
            }
        }else {
         //   System.out.println("less");
            isCalculatable = false;
        }
        //System.out.println(isCalculatable);

        if(isCalculatable) {
            if (toDay < birthDay) {
                toDay = toDay + 30;
                toMonth--;
            }

            if (toMonth < birthMonth) {
                toMonth = toMonth + 12;
                toYear--;
            }

            date = toDay - birthDay;
            mon = toMonth - birthMonth;
            year = toYear - birthYear;
        }
       // System.out.println(year + " years " + mon + " months " + date + " days");
        return new int[]{date,mon,year};
    }

}

