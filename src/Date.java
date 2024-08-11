public class Date {
    int day;
    int month;
    int year;

    public Date(int month, int day, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static int compare(Date d1, Date d2) {
        if (d1.day == d2.day && d1.month == d2.month && d1.year == d2.year) {
            return 0;
        }

        if (d1.year > d2.year) {
            return 1;
        } else if (d1.year == d2.year) {
            if (d1.month > d2.month) {
                return 1;
            } else if (d1.month == d2.month) {
                if (d1.day > d2.day) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public static boolean verify(Date d1) {
        if (d1.year < 0)
            return false;
        if (d1.month > 12 || d1.month < 1)
            return false;
        if (d1.day > 31 || d1.day < 1)
            return false;
        return true;
    }
}