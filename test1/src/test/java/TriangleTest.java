import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TriangleTest {
    @Test
    @DisplayName("非整数,请输入整数")
    void integer_test() {
        Triangle triangle = new Triangle();
        String type = triangle.JudegeTriangele(3,4.5, 5);
        assertEquals("非整数,请输入整数", type);
    }

    @Test
    @DisplayName("输入错误，请输入1-100之间的数字")
    void parameters_error_test() {
        Triangle triangle = new Triangle();
        String type = triangle.JudegeTriangele(50, 50, 0);
        assertEquals("输入错误，请输入1-100之间的数字", type);
    }

    @Test
    @DisplayName("不等边三角形")
    void scalene_test() {
        Triangle triangle = new Triangle();

        String type = triangle.JudegeTriangele(50 ,  51, 100  );
        assertEquals("不等边三角形", type);
    }

    @Test
    @DisplayName("等边三角形")
    void equilateral_triangle_test() {
        Triangle triangle = new Triangle();
        String type = triangle.JudegeTriangele(1, 1, 1);
        assertEquals("等边三角形", type);
    }

    @Test
    @DisplayName("非三角形")
    void triangle_test() {
        Triangle triangle = new Triangle();

        String type = triangle.JudegeTriangele(50, 50, 100);
        assertEquals("非三角形", type);
    }
    @Test
    @DisplayName("等腰三角形")
    void isosceles_test() {
        Triangle triangle = new Triangle();
        String type = triangle.JudegeTriangele(50, 50, 1);
        assertEquals("等腰三角形", type);
    }

    @ParameterizedTest
    @CsvSource({
            "3,4,6,不等边三角形",
            "3,3,3,等边三角形",
            "3,3,6,非三角形"
    })
    void paramTriangle(double a, double b,double c,String expected) {
        Triangle triangle = new Triangle();
        String type = triangle.JudegeTriangele(a, b, c);
        assertEquals(expected, type);
    }


    @DisplayName(value="三角形一般边界测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形一般边界测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest(int m,int a, int b,int c,String expected){
        System.out.println(m+":"+m+"("+a+","+b+","+c+":"+expected);
        Triangle triangle=new Triangle();
        String type = triangle.JudegeTriangele(a, b, c);
        assertEquals(expected, type);
    }


    @DisplayName(value="三角形健壮性最坏情况测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形健壮性最坏情况测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest1(int m,int a, int b,int c,String expected){
        System.out.println(m+":"+m+"("+a+","+b+","+c+":"+expected);
        Triangle triangle=new Triangle();
        String type = triangle.JudegeTriangele(a, b, c);
        assertEquals(expected, type);
    }
    @DisplayName(value="三角形最坏情况测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形最坏情况测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest2(int m,int a, int b,int c,String expected){
        System.out.println(m+":"+m+"("+a+","+b+","+c+":"+expected);
        Triangle triangle=new Triangle();

        String type = triangle.JudegeTriangele(a, b, c);

        assertEquals(expected, type);
    }
    @DisplayName(value="三角形健壮测试用例")
    @ParameterizedTest
    @CsvFileSource(resources = "/三角形健壮测试用例.csv",numLinesToSkip =1,encoding = "UTF-8")
    void fileTest4(int m,int a, int b,int c,String expected){
        System.out.println(m+":"+m+"("+a+","+b+","+c+":"+expected);
        Triangle triangle=new Triangle();

        String type = triangle.JudegeTriangele(a, b, c);

        assertEquals(expected, type);
    }
}