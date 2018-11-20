package com.dbms;

import com.dbms.logic.ExpressionExecutor;

public final class DBRunner {

    public static void main(String[] args) throws Exception {
        
        /*SqlParser parser = new SqlParser(new FileReader("src/test/java/resources/TestSandbox.txt"));
        parser.parse();

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        String salary = "80000";
        Object result = engine.eval("4*5+" + salary);
        System.out.println(result);
        */
        ExpressionExecutor expressionExecutor = new ExpressionExecutor();
        expressionExecutor.execute();
    }
}
