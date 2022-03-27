import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class TelephoneBilling {
    public boolean Judgedate(int []a) {
        boolean flag = false;
        if ((a[0]%4 == 0 && a[0]%100 !=0) || (a[0]%400==0)){
            flag = true;
        }
        if(a[1] < 1 || a[1] > 12 )
            return false;
        switch (a[1])
        {
            case 2:
                if(flag){
                    if(a[2] > 29 || a[2] < 1)
                        return false;
                }
                else {
                    if (a[2] > 28 || a[2] < 1)
                        return false;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if(a[2] > 30 || a[2] < 1)
                    return false;
                break;
            default:
                if(a[2] > 31 || a[2] < 1)
                    return false;
                break;
        }
        if(a[3] <0 || a[3] > 23)
            return false;
        if(a[4] < 0 || a[4] > 59)
            return false;
        if(a[5] < 0 || a[5] > 59)
            return false;
        return true;
    }


    public String CostCalculation(int time) {
        double bill = 0.0f;
        if (time <= 20){ //小于等于20min的情况
            if (time<=1) { //小于1min的情况{
                System.out.println("通话时长不足1min,按照1min收费，费用为0.05美元");
                return "通话不足1min，费用为0.05";
            } else { //  1 < time <= 20
                bill = (time) * 0.05;
                System.out.println("通话时长为" + time + "min,总费用为" + String.format("%.2f", bill));
                return "通话时间大于1min,小于等于20min,通话时长为"+time+"min,总费用为"+String.format("%.2f", bill);
            }
        }
        else{
            bill = time * 1 + (time-20)*0.1;
            log.println("通话时长为"+time+"min,总费用为"+String.format("%.2f",bill));
            return "通话时长大于20min,通话时长为"+time+"min,总费用为"+String.format("%.2f", bill);
        }
    }
    public  int testweek(int Year,int Month,int Day) {//判断某年某月某日为星期几
        int iweek = 0;
        int y=0,c=0,m=0,d=0;
        if(Month == 1 || Month == 2) {
            c=(Year-1)/100;
            y=(Year-1)%100;
            m=Month+12;
            d=Day;
        }
        else {
            c=Year/100;
            y=Year%100;
            m=Month;
            d=Day;
        }
        iweek=y+y/4+c/4-2*c+26*(m+1)/10+d-1;
        iweek=iweek>=0?(iweek%7):(iweek%7+7);
        if(iweek == 0) {
                iweek=7;
        }
        return iweek;
    }
    //判断日期是否为三月的某个星期日或者十月的最后一个星期日并对通话时间进行处理
    public int testdate1(int Year, int Month,int Day,int Min,int stime, int etime) {//stime etime 判断是否通话区间包含时间转换区间
        if((Month==3 && Day + 7 > 31)||(Month==4 && Day+1<8) || (Month == 3 && this.testweek(Year, Month, Day) == 7)){
            if(stime <= 2 && etime > 2) {
                Min = Min - 60;
                System.out.println("目前是三月的某个星期日，且开始通话时间在两点前，结束时间在两点后，进行春夏时间转换");
            }
        }

        else if(Month==11 && Day>=1&&Day<=7 && this.testweek(Year, Month, Day) == 7) {//判断是否为11月的第一个星期日
            if(stime <= 2 && etime >= 2) {
                Min = Min + 60;
                System.out.println("目前是十一月的第一个星期日，且开始通话时间在三点前，结束时间在三点后，进行夏秋时间转换");
            }
        }
        return Min;
    }
}
