package net.codlin.sms;

//import net.codlin.sms.Age;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}
	
	private static String calculateAge(Date birthDate)
	   {
	      int years = 0;
	      int months = 0;
	      int days = 0;
	      //create calendar object for birth day
	      Calendar birthDay = Calendar.getInstance();
	      birthDay.setTimeInMillis(birthDate.getTime());
	      //create calendar object for current day
	      long currentTime = System.currentTimeMillis();
	      Calendar now = Calendar.getInstance();
	      now.setTimeInMillis(currentTime);
	      //Get difference between years
	      years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
	      int currMonth = now.get(Calendar.MONTH) + 1;
	      int birthMonth = birthDay.get(Calendar.MONTH) + 1;
	      //Get difference between months
	      months = currMonth - birthMonth;
	      //if month difference is in negative then reduce years by one and calculate the number of months.
	      if (months < 0)
	      {
	         years--;
	         months = 12 - birthMonth + currMonth;
	         if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	            months--;
	      } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	      {
	         years--;
	         months = 11;
	      }
	      //Calculate the days
	      if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
	         days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
	      else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	      {
	         int today = now.get(Calendar.DAY_OF_MONTH);
	         now.add(Calendar.MONTH, -1);
	         days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
	      } else
	      {
	         days = 0;
	         if (months == 12)
	         {
	            years++;
	            months = 0;
	         }
	      }
	      //Create new Age object
	      String YearsOld="";
	      
	      YearsOld = years+"a "+months+"m "+days+"d";
	      
	      //return new Age(days, months, years);
	      return YearsOld;
	   }

//	public static void main(String[] args) throws ParseException {
//
//		// TODO Auto-generated method stub
//	      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//	      Date birthDate = sdf.parse("29/11/1981"); //Yeh !! It's my date of birth :-)
//	      //Age age = calculateAge(birthDate);
//	      //My age is
//	      //System.out.println(age);
//	      String yearsold = calculateAge(birthDate);
//	      System.out.println(yearsold);
//
//		//System.out.println("\nListo..");
//
//
////	      LocalDate today = LocalDate.now();
////	      LocalDate birthday = LocalDate.of(1960, 1, 1);
////
////	      Period p = Period.between(birthday, today);
////	      long p2 = ChronoUnit.DAYS.between(birthday, today);
////	      System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
////	                         " months, and " + p.getDays() +
////	                         " days old. (" + p2 + " days total)");
//	}

}

