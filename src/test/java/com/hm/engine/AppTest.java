//package com.hm.engine;
//
//import com.hm.engine.common.Comparetors;
//import com.hm.engine.common.annotation.HmRequired;
//import com.hm.engine.common.exception.HmAssert;
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//
///**
// * Unit test for simple App.
// */
//public class AppTest
//    extends TestCase
//{
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public AppTest( String testName )
//    {
//        super( testName );
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( AppTest.class );
//    }
//
//    /**
//     * Rigourous Test :-)
//     */
//    public void testApp()
//    {
//        assertTrue( true );
//    }
//
//    @org.junit.Test
//    public void testAssert(){
//        TestAssert testAsser=new TestAssert();
//        TestAssert testNotRequired=new TestAssert();
//        testAsser.setAaa("aaa");
//        HmAssert.valiadateByAnotation(testAsser);
//        HmAssert.valiadateByAnotation(testNotRequired);
//    }
//    public static class TestAssert{
//        @HmRequired
//        private String aaa;
//
//        public String getAaa() {
//            return aaa;
//        }
//
//        public void setAaa(String aaa) {
//            this.aaa = aaa;
//        }
//    }
//
//
//    public void test() throws IOException {
//
//        Comparetors.TestClass  testClass=new Comparetors.TestClass();
//        testClass.setId(1l);
//        testClass.setName("xxx");
//        long c=System.currentTimeMillis();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        InputStream in = new ByteArrayInputStream("剩asdfasdfahsjdfkashghutdrfyghjlyrtwsghjkmewsrdtfgjhklesrdtfyghijokjyreysdtfyguhijrudtfyguhj下的财富规划比较困难了么周日宣布今年可每次邪恶天才 v 你们".getBytes(StandardCharsets.UTF_8));
////        in.
//
//
//        HmAssert.valiadateByAnotation(testClass);
//
////        byte[] buf = new byte[1024];
////        for (;;) {
////            int len = in.read(buf);
////            if (len == -1) {
////                break;
////            }
////
////            if (len > 0) {
////                baos.write(buf, 0, len);
////            }
////        }
//
////        int byteCount = 0;
////        byte[] buffer = new byte[4096];
////        int bytesRead = -1;
////        while ((bytesRead = in.read(buffer)) != -1) {
////            baos.write(buffer, 0, bytesRead);
////            byteCount += bytesRead;
////        }
////        baos.flush();
////        byte[] bytes = baos.toByteArray();
////        System.out.println(new String(bytes));
//
//      System.out.printf("%d millis",System.currentTimeMillis()-c);
//
//    }
//}
