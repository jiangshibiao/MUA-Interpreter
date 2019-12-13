package src.mua;

import src.mua.Data;
import src.mua.MyError;
import src.mua.Operation.Operation;
import src.mua.Parse;

import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

public class Expression {
    NameSpace space;
    public Expression(NameSpace space){
        this.space = space;
    }
    private static final HashMap<Character, String> operatorMap = new HashMap<Character, String>() {{
        put('+', "add");
        put('-', "sub");
        put('*', "mul");
        put('/', "div");
        put('%', "mod");
    }};
    private static final HashMap<Character, Integer> operatorPri = new HashMap<Character, Integer>() {{
        put('+', 1);
        put('-', 1);
        put('*', 2);
        put('/', 2);
        put('%', 2);
    }};
    private class Triple{
        public Data data;
        public int end;
        public boolean stopFlag;
        Triple(Data data, int end, boolean stopFlag){
            this.data = data;
            this.end = end;
            this.stopFlag = stopFlag;
        }
    }

    public Data calcExpression(String str) throws MyError {
        if (str == null || str.isEmpty())
            throw new MyError(MyError.ErrorType.UnexpectedError,
                "Expression is empty.");
        ///*debug*/System.out.println("!!!"+str);
        if (str.charAt(0) == '(')
            if (str.charAt(str.length() - 1) != ')')
                throw new MyError(MyError.ErrorType.SyntaxError,
                        "Expression starts by ( but not find ) in the end.");
        Stack<Character> optStack = new Stack<>();
        Stack<Data> dataStack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (isBlank(str.charAt(i))) continue;
            if (operatorMap.containsKey(str.charAt(i))) {
                //It is an operator.
                if (dataStack.isEmpty())
                    throw new MyError(MyError.ErrorType.SyntaxError,
                            "No parameter is supported before operator " + str.charAt(i) + ".");
                optStack.push(str.charAt(i));
                ///*debug*/System.out.println(str.charAt(i));
            } else {
                //It is a piece of data.
                Triple resultData = getNextData(str, i);
                if (resultData.stopFlag)
                    throw new MyError(MyError.ErrorType.RuntimeError,
                            "Can not use [stop] when calculating expression.");
                if (!optStack.isEmpty() && operatorPri.get(optStack.peek()) == 2) {
                    if (dataStack.isEmpty())
                        throw new MyError(MyError.ErrorType.UnexpectedError,
                                "There is no number before operator " + optStack.peek() + ".");
                    Operation nowOperation = space.operationRun.newOperation(operatorMap.get(optStack.pop()));
                    nowOperation.addArgs(dataStack.pop());
                    nowOperation.addArgs(resultData.data);
                    nowOperation.exec(space);
                    dataStack.push(nowOperation.ReturnValue);
                    ///*debug*/System.out.println(str.charAt(i));
                } else {
                    dataStack.push(resultData.data);
                    ///*debug*/System.out.println(resultData.data + " " + resultData.end);
                }
                i = resultData.end;
            }
        }
        Stack<Character>optReverseStack = new Stack<>();
        Stack<Data>dataReverseStack = new Stack<>();
        while (!optStack.isEmpty()) optReverseStack.push(optStack.pop());
        while (!dataStack.isEmpty()) dataReverseStack.push(dataStack.pop());

        while (!optReverseStack.isEmpty()){
            if (dataReverseStack.size() < 2)
                throw new MyError(MyError.ErrorType.UnexpectedError,
                        "No enough parameters for operators.");
            Data t1 = dataReverseStack.pop();
            Data t2 = dataReverseStack.pop();
            //System.out.println(t1 + " " + t2);
            Operation nowOperation = space.operationRun.newOperation(operatorMap.get(optReverseStack.pop()));
            nowOperation.addArgs(t1);
            nowOperation.addArgs(t2);
            nowOperation.exec(space);
            dataReverseStack.push(nowOperation.ReturnValue);
        }

        if (dataReverseStack.size() != 1)
             throw new MyError(MyError.ErrorType.SyntaxError,
                     "The expression can not be calculated correctly.");
        return dataReverseStack.pop();
    }
    public void runList(Data program) throws MyError{
        String str = program.toString();
        if (str.charAt(0) != '[' || str.charAt(str.length() - 1) != ']')
            throw new MyError(MyError.ErrorType.UnexpectedError,
                    "Expect type LIST but [ or ] are not found.");
        str = str.substring(1, str.length() - 1);
        ///*debug*/ System.out.println("RunList: " + program);
        Parse parse = new Parse(space);
        for (int i = 0; i < str.length(); i++){
            char cur = str.charAt(i);
            if (isBlank(cur)) continue;
            Triple resultData = getNextUnit(str, i);
            //System.out.println(i + " " + resultData.data);
            parse.push(resultData.data);
            if (space.stopFlag) break;
            i = resultData.end;
        }
        if (parse.peek() != null)
            throw new MyError(MyError.ErrorType.RuntimeError,
                    "Function [" + parse.peek().getName() + "] needs more parameters.");
    }
    public Data generateList(String str) throws MyError{
        ///*debug*/ System.out.println("generateList: " + str);
        if (str == null || str.charAt(0) != '[' || str.charAt(str.length() - 1) != ']')
            throw new MyError(MyError.ErrorType.TypeError,
                    "Expect type LIST but [ or ] are not found.");

        str = str.substring(1, str.length() - 1);
        Vector<Data>resultList = new Vector<>();
        for (int i = 0; i < str.length(); i++){
            char cur = str.charAt(i);
            if (isBlank(cur)) continue;
            if (cur == '['){
                int j = findMatchedSquareBracket(str, i);
                if (j == -1)
                    throw new MyError(MyError.ErrorType.SyntaxError, "Miss ].");
                resultList.add(generateList(str.substring(i, j + 1)));
                i = j;
            }
            else {
                int end = nextSegment(str, i);
                resultList.add(new Data (str.substring(i, end), false));
                i = end;
            }
        }
        ///*debug*/ System.out.println("generateListResult: " + new Data(resultList));
        return new Data(resultList);
    }
    private Triple getNextData(String str, int start) throws MyError{
        Parse parse = new Parse(space);
        ///*debug*/System.out.println("getNextData " + str + " " + start);
        int i = start;
        for (; i < str.length(); i++) {
            Triple resultData = getNextUnit(str, i);
            ///*debug*/System.out.println("getNextData " + "{" + i + "," + resultData.end + "}" + resultData.data);
            parse.push(resultData.data);
            if (space.stopFlag) break;
            i = resultData.end;
            if (parse.restValue != null) break;
        }
        if (parse.restValue == null)
            throw new MyError(MyError.ErrorType.RuntimeError,
                    "Need a piece of data for calculation but eof find.");
        return new Triple(parse.restValue, i, space.stopFlag);
    }
    private Triple getNextUnit(String str, int start) throws MyError{
        for (int i = start; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (isBlank(cur)) continue;
            if (cur == '[') {
                int j = findMatchedSquareBracket(str, i);
                if (j == -1)
                    throw new MyError(MyError.ErrorType.SyntaxError, "Miss ].");
                return new Triple(generateList(str.substring(i, j + 1)), j, false);
            }
            if (cur == '(') {
                int j = findMatchedParentheses(str, i);
                if (j == -1)
                    throw new MyError(MyError.ErrorType.SyntaxError, "Miss ).");
                return new Triple(calcExpression(str.substring(i + 1, j)), j, false);
            }
            if (cur == ']')
                throw new MyError(MyError.ErrorType.SyntaxError, "Find ] before [.");
            if (cur == ')')
                throw new MyError(MyError.ErrorType.SyntaxError, "Find ) before (.");
            if (cur == ':') {
                Parse parse = new Parse(space);
                int end = nextWord(str, i + 1);
                parse.push(new Data("thing", true));
                parse.push(new Data(str.substring(i + 1, end), false));
                if (parse.restValue == null)
                    throw new MyError(MyError.ErrorType.UnexpectedError,
                            "Function [thing] doesn't have return value.");
                return new Triple(parse.restValue, end - 1, false);
            }
            try {
                //We should parse it as a primitive type first and then operation.
                //Because the expression may be "true".
                int end = nextWord(str, i);
                Data tryData = new Data (str.substring(i, end), true);
                if (tryData.type == Data.Type.OP)
                    throw new MyError(MyError.ErrorType.NameError, "It will be caught below.");
                return new Triple(tryData, end - 1, false); //NUMBER, WORD or BOOLEAN.
            } catch (MyError e) {
                //It is not a type. Thus it is an operation or function.
                if (Character.isLetter(cur)){
                    int end = nextWord(str, i);
                    return new Triple(new Data(str.substring(i, end), true), end - 1, false);
                }
                else
                    throw new MyError(MyError.ErrorType.SyntaxError,
                        "This character " + cur + " can not be used to recognize.");
            }
        }
        throw new MyError(MyError.ErrorType.SyntaxError,
                    "Need a type of data but eof find.");
    }
    int nextWord(String str, int i){
        int j = i;
        for (; j < str.length() && !isBlank(str.charAt(j)) && !operatorMap.containsKey(str.charAt(j)); j++);
        return j;
    }
    int nextSegment(String str, int i){
        int j = i;
        for (; j < str.length() && !isBlank(str.charAt(j)); j++);
        return j;
    }
    int findMatchedParentheses(String str, int start){
        int Parentheses = 0;
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) == '(') Parentheses++;
            if (str.charAt(i) == ')') Parentheses--;
            if (Parentheses == 0) return i;
        }
        return -1;
    }
    int findMatchedSquareBracket(String str, int start){
        int SquareBracket = 0;
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) == '[') SquareBracket++;
            if (str.charAt(i) == ']') SquareBracket--;
            if (SquareBracket == 0) return i;
        }
        return -1;
    }
    boolean isBlank(char ch){return ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r';}

}
