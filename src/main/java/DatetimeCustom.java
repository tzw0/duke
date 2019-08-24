import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DatetimeCustom extends Exception{
    public static String format(String str1) throws DukeException{
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(0);
//        cal.set(year, month, day, hour, minute, second);
//        Date date = cal.getTime();
        try {
            str1 = str1.trim();
            String[] datetime = str1.split(" ");
            String[] ddmmyyyy = datetime[0].split("/");
            String day = ddmmyyyy[0];
            String month = ddmmyyyy[1];
            String year = ddmmyyyy[2];
            String time = datetime[1];
            int[] daysinmonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int month_i = Integer.parseInt(month) ;
            int year_i = Integer.parseInt(year);
            Integer day_i = Integer.parseInt(day);
            String min = time.substring(2);
            String hr = time.substring(0, 2);
            Integer min_i = Integer.parseInt(min);
            Integer hr_i = Integer.parseInt(hr);
            if (hr_i > 23 || min_i > 59 || month_i > 12 || year_i < 0 || day_i > daysinmonth[month_i - 1]) throw new DukeException("datetime_N");
            return str1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("datetime");
        } catch (NumberFormatException e) {
            throw new DukeException("datetime");
        }
    }
    public static String view(String str1) throws DukeException{
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(0);
//        cal.set(year, month, day, hour, minute, second);
//        Date date = cal.getTime();
        try {
            str1 = str1.trim();
            String[] datetime = str1.split(" ");
            String[] ddmmyyyy = datetime[0].split("/");
            String day = ddmmyyyy[0];
            String month = ddmmyyyy[1];
            String year = ddmmyyyy[2];
            String time = datetime[1];
            String[] stndrd = {"st", "nd", "rd"};
            String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            String month_x = months[Integer.parseInt(month) - 1];
            Integer day_i = Integer.parseInt(day);
            String day_x;
            String time_x;
            if (day_i >= 10 && day_i <= 20 || day_i % 10 > 3 || day_i % 10 == 0) {
                day_x = day_i.toString() + "th";
            } else {
                day_x = day_i.toString() + stndrd[day_i % 10 - 1];
            }
            String min = time.substring(2);
            String hr = time.substring(0, 2);
            Integer min_i = Integer.parseInt(min);
            Integer hr_i = Integer.parseInt(hr);
            if (min_i != 0) {
                time_x = ":" + min;
            } else {
                time_x = "";
            }
            if (hr_i > 12) {
                hr_i = hr_i - 12;
                time_x = hr_i.toString() + time_x + "pm";
            } else {
                time_x = hr_i.toString() + time_x + "am";
            }
            return day_x + " of " + month_x + " " + year + ", " + time_x;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("datetime");
        } catch (NumberFormatException e) {
            throw new DukeException("datetime");
        }
    }
}