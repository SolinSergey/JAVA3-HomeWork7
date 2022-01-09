public class ToTest {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite");
    }

    @Test (priority = 10)
    public void mostImportantTest(){
        System.out.println("mostImportantTest");
    }

    @Test (priority = 10)
    public void mostImportantTest2(){
        System.out.println("mostImportantTest2");
    }

    @Test (priority = 5)
    public void notSoImportantTest1(){
        System.out.println("notSoImportantTest1");
    }

    @Test (priority = 5)
    public void notSoImportantTest2(){
        System.out.println("notSoImportantTest2");
    }

    @Test
    public void lowPriorityTest1(){
        System.out.println("lowPriorityTest1");
    }

    @Test
    public void lowPriorityTest2(){
        System.out.println("lowPriorityTest2");
    }
}
