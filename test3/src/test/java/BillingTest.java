import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillingTest {

    @ParameterizedTest
    @CsvSource(value = {
            "2022,0,29,1,1,29,输入日期不合法，请检查后重新输入",
            "2022,13,29,1,1,29,输入日期不合法，请检查后重新输入",
            "2000,2,30,1,1,29,输入日期不合法，请检查后重新输入",
            "2022,2,29,1,1,29,输入日期不合法，请检查后重新输入",
            "2022,3,32,1,1,29,输入日期不合法，请检查后重新输入",
            "2022,4,31,1,1,29,输入日期不合法，请检查后重新输入",
            "2022,2,9,-1,1,29,输入日期不合法，请检查后重新输入",
            "2022,2,9,24,1,29,输入日期不合法，请检查后重新输入",
            "2022,2,9,1,-1,29,输入日期不合法，请检查后重新输入",
            "2022,2,9,1,60,29,输入日期不合法，请检查后重新输入",
            "2022,2,9,1,1,-1,输入日期不合法，请检查后重新输入",
            "2022,2,9,1,1,60,输入日期不合法，请检查后重新输入"
    })
    void Judge(int y,int m, int d, int h, int min, int s, String expected) {
        TelephoneBilling billing = new TelephoneBilling();
        String type = "合法";
        int time[] = {y,m,d,h,min,s};
        if( billing.Judgedate(time) == false)
            type = "输入日期不合法，请检查后重新输入";
        assertEquals(expected, type);
    }

    @Test
    @DisplayName("通话时间小于1min")
    void T1_S1test(){
        int time1[] = {2022,1,1,1,1,59}; //通话结束时间
        int time2[] = {2022,1,1,1,1,0}; //通话开始时间
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话不足1min，费用为0.05", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时长小于1min")
    void T1_S2test(){
        int time1[] = {2022,3,27,3,0,54}; //通话结束时间
        int time2[] = {2022,3,27,1,59,55}; //通话开始时间
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] > time2[5])
                Min += 1;
            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话不足1min，费用为0.05", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时长小于1min")
    void T1_S3test(){
        int time1[] = {2022,11,6,2,00,54}; //通话结束时间
        int time2[] = {2022,11,6,2,59,55}; //通话开始时间
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] > time2[5])    //起止时间转换为minutes
                Min += 1;
            System.out.println(Min);
            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;
            System.out.println(Min);
            String type = billing.CostCalculation(Min);
            assertEquals("通话不足1min，费用为0.05", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时长小于1min")
    void T1_S1boundarytest1(){
        int time1[] = {2022,3,27,13,39,59}; //通话结束时间
        int time2[] = {2022,3,27,13,40,59}; //通话开始时间
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] > time2[5])    //起止时间转换为minutes
                Min += 1;
            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话不足1min，费用为0.05", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }


    @Test
    @DisplayName("通话时长小于1min")
    void T1_S2boundarytest(){
        int time1[] = {2022,3,27,3,00,49}; //通话结束时间
        int time2[] = {2022,3,27,1,59,49}; //通话开始时间
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] > time2[5])    //起止时间转换为minutes
                Min += 1;
            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话不足1min，费用为0.05", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }
    @Test
    @DisplayName("通话时长小于1min")
    void T1_S3boundarytest1(){
        int time1[] = {2022,11,6,2,00,10}; //通话结束时间
        int time2[] = {2022,11,6,2,59,10}; //通话开始时间
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] > time2[5])    //起止时间转换为minutes
                Min += 1;
            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话不足1min，费用为0.05", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时间大于1min,小于等于20min")
    void T2_S1test(){
        int time1[] = {2022,1,1,1,20,0};
        int time2[] = {2022,1,1,1,1,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时间大于1min,小于等于20min,通话时长为19min,总费用为0.95", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时间大于1min,小于等于20min")
    void T2_S1boundarytest(){
        int time1[] = {2022,3,27,13,50,01};
        int time2[] = {2022,3,27,13,49,00};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时间大于1min,小于等于20min,通话时长为2min,总费用为0.10", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时间大于1min,小于等于20min")
    void T2_S1boundarytest1(){
        int time1[] = {2022,3,27,14,19,59};
        int time2[] = {2022,3,27,14,00,00};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时间大于1min,小于等于20min,通话时长为20min,总费用为1.00", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }
    @Test
    @DisplayName("通话时间大于1min,小于等于20min")
    void T2_S1boundarytest2(){
        int time1[] = {2022,3,27,14,20,00};
        int time2[] = {2022,3,27,14,00,00};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时间大于1min,小于等于20min,通话时长为20min,总费用为1.00", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }


    @Test
    @DisplayName("通话时间大于1min,小于等于20min")
    void T2_S2test(){
        int time1[] = {2022,3,27,3,14,0};
        int time2[] = {2022,3,27,1,55,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时间大于1min,小于等于20min,通话时长为19min,总费用为0.95", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时间大于1min,小于等于20min")
    void T2_S2boundarytest(){
        int time1[] = {2022,3,27,3,14,59};
        int time2[] = {2022,3,27,1,55,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时间大于1min,小于等于20min,通话时长为20min,总费用为1.00", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时间大于1min,小于等于20min")
    void T2_S2boundarytest1(){
        int time1[] = {2022,3,27,3,15,0};
        int time2[] = {2022,3,27,1,55,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时间大于1min,小于等于20min,通话时长为20min,总费用为1.00", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时间大于1min,小于等于20min")
    void T2_S3test(){
        int time1[] = {2022,11,6,2,14,0};
        int time2[] = {2022,11,6,2,55,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时间大于1min,小于等于20min,通话时长为19min,总费用为0.95", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }
    @Test
    @DisplayName("通话时间大于1min,小于等于20min")
    void T2_S3boundarytest(){
        int time1[] = {2022,11,6,2,14,59};
        int time2[] = {2022,11,6,2,55,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时间大于1min,小于等于20min,通话时长为20min,总费用为1.00", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时间大于1min,小于等于20min")
    void T2_S3boundarytest1(){
        int time1[] = {2022,11,6,2,14,59};
        int time2[] = {2022,11,6,2,55,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时间大于1min,小于等于20min,通话时长为20min,总费用为1.00", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时长大于20min")
    void T3_S1test(){
        int time1[] = {2022,3,27,3,21,1};
        int time2[] = {2022,3,27,3,1,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时长大于20min,通话时长为21min,总费用为21.10", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }
    @Test
    @DisplayName("通话时长大于20min")
    void T3_S1boundarytest(){
        int time1[] = {2022,3,27,3,31,0};
        int time2[] = {2022,3,27,3,10,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;

            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时长大于20min,通话时长为21min,总费用为21.10", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时长大于20min")
    void T3_S2test(){
        int time1[] = {2022,3,27,3,10,1};
        int time2[] = {2022,3,27,1,50,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;


            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时长大于20min,通话时长为21min,总费用为21.10", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时长大于20min")
    void T3_S2boundarytest(){
        int time1[] = {2022,3,27,3,11,0};
        int time2[] = {2022,3,27,1,50,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if (time1[5] != time2[5])    //起止时间转换为minutes
                Min += 1;


            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;

            String type = billing.CostCalculation(Min);
            assertEquals("通话时长大于20min,通话时长为21min,总费用为21.10", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

    @Test
    @DisplayName("通话时长大于20min")
    void T3_S3test(){
        int time1[] = {2022,11,6,2,15,1};
        int time2[] = {2022,11,6,2,55,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if(time1[5] != time2[5] )
                Min+=1;
            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;
            String type = billing.CostCalculation(Min);
            assertEquals("通话时长大于20min,通话时长为21min,总费用为21.10", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }
    @Test
    @DisplayName("通话时长大于20min")

    void T3_S3boundarytest(){
        int time1[] = {2022,11,6,2,16,0};
        int time2[] = {2022,11,6,2,55,0};
        TelephoneBilling billing = new TelephoneBilling();
        if(billing.Judgedate(time1) && billing.Judgedate(time2)) {
            int Min = (time1[2] - time2[2]) * 24 + (time1[3] - time2[3]) * 60 + (time1[4] - time2[4]);
            if(time1[5] != time2[5] )
                Min+=1;
            Min = billing.testdate1(time1[0], time1[1], time1[2], Min, time2[3], time1[3]); //是否需要进行时间转化;
            String type = billing.CostCalculation(Min);
            assertEquals("通话时长大于20min,通话时长为21min,总费用为21.10", type);
        }
        else{
            String type = "输入日期不合法，请检查后重新输入";
            assertEquals("输入日期不合法，请检查后重新输入",type);
        }
    }

}
