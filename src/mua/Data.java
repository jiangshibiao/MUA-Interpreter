package src.mua;
import src.mua.MyError;

import javax.security.auth.callback.CallbackHandler;
import java.io.Serializable;
import java.util.Vector;

public class Data implements Serializable {
    public enum Type{
        OP, BOOLEAN, NUMBER, WORD, LIST, NONE
    }
    public Type type = Type.NONE;
    private double valNumber;
    private String valWord;
    //If it is a list, [valWord] records its original string
    //If it is a operation, [valWord] records its name
    private boolean valBoolean;
    private Vector<Data> valList;


    public Data(double data){
        type = Type.NUMBER;
        valNumber = data;
    }
    public Data(boolean data){
        type = Type.BOOLEAN;
        valBoolean = data;
    }
    public Data(Vector<Data>data){
        type = Type.LIST;
        valList = data;
    }
    public Data(Data old) throws MyError{
        if (old.type == Type.LIST){
            valList = new Vector<Data>();
            Vector<Data>oldList = old.getList();
            for (int i = 0; i < oldList.size(); i++)
                valList.add(new Data(oldList.get(i)));
            type = Type.LIST;
        }
        else{
            type = old.type;
            valNumber = old.valNumber;
            valWord = old.valWord;
            valBoolean = old.valBoolean;
        }
    }
    public Data(String literal, boolean startLiteral) throws MyError{
        if (!startLiteral){
            type = Type.WORD;
            valWord = literal;
        }
        else {
            //literal all types except LIST, which will be parsed in class Expression.
            if (literal == null || literal.isEmpty()){
                type = Type.NONE;
            } else if (literal.charAt(0) == '"') {
                type = Type.WORD;
                valWord = literal.substring(1);
            } else if (literal.equals("true") || literal.equals("false")) {
                type = Type.BOOLEAN;
                valBoolean = Boolean.parseBoolean(literal);
            } else if (Character.isLetter(literal.charAt(0))) {
                type = Type.OP;
                valWord = literal;
            } else if (Character.isDigit(literal.charAt(0)) || literal.charAt(0) == '-') {
                int k = 0, sign = 1;
                for (; k < literal.length() && literal.charAt(k) == '-'; k++) sign *= -1;
                type = Type.NUMBER;
                valNumber = Double.parseDouble(literal.substring(k)) * sign;
            }
            else
                throw new MyError(MyError.ErrorType.TypeError,
                        "Can not distinguish this type");
        }
    }
    public double getNumber() throws MyError{
        if (type == Type.WORD) {
            try{
                return Double.parseDouble(valWord);
            }
            catch (Exception e){
                throw new MyError(MyError.ErrorType.ConvertError,
                        "Can not convert [WORD] to [NUMBER].");
            }
        }
        if (type != Type.NUMBER)
            throw new MyError(MyError.ErrorType.TypeError,
                    "Expect [NUMBER] but [" + this + "] find.");
        return valNumber;
    }
    public long getInt() throws MyError{
        double val = getNumber();
        int tryInt = (int)Math.round(val);
        if (Math.abs(tryInt - val) > 1e-7)
            throw new MyError(MyError.ErrorType.ConvertError,
                    val + " can not be converted into integer.");
        return tryInt;
    }
    public boolean getBoolean() throws MyError{
        if (type == Type.WORD) {
            try{
                return Boolean.parseBoolean(valWord);
            }
            catch (Exception e){
                throw new MyError(MyError.ErrorType.ConvertError,
                        "Can not convert [WORD] to [BOOLEAN].");
            }
        }
        if (type != Type.BOOLEAN)
            throw new MyError(MyError.ErrorType.TypeError,
                    "Expect [BOOLEAN] but [" + this + "] find.");
        return valBoolean;
    }
    public String getWord() throws MyError{
        if (type != Type.WORD)
            throw new MyError(MyError.ErrorType.TypeError,
                    "Expect [WORD] but find [" + type + "].");
        return valWord;
    }
    public String getName() throws MyError{
        String name = getWord();
        if (!isLegal(name))
            throw new MyError(MyError.ErrorType.SyntaxError,
                    "Name should contain only letters, '_' and numbers, and should start by letter");
        return name;
    }
    public String getOp() throws MyError{
        if (type != Type.OP)
            throw new MyError(MyError.ErrorType.TypeError,
                    "Expect [operation name] but find [" + type + "].");
        return valWord;
    }
    public Vector<Data> getList() throws MyError{
        if (type != Type.LIST)
            throw new MyError(MyError.ErrorType.TypeError,
                    "Expect [LIST] but find [" + type + "].");
        return valList;
    }

    public void listAdd(Data t) throws MyError{
        if (type != Type.LIST)
            throw new MyError(MyError.ErrorType.UnexpectedError,
                    "Error in listAdd.");
        valList.add(t);
    }

    @Override
    public String toString() {
        switch (type) {
            case BOOLEAN:
                return valBoolean ? "true" : "false";
            case NUMBER:
                return Double.toString(valNumber);
            case OP:
                return valWord;
            case WORD:
                return valWord;
            case LIST:{
                StringBuffer str = new StringBuffer();
                str.append("[ ");
                for (Data each: valList)
                    str.append(each.toString()).append(" ");
                str.append("]");
                return str.toString();
            }
        }
        return "??? Unexpected things happen ???";
    }
    boolean isLegal(String name) {
        if (name.length() == 0 || !Character.isLetter(name.charAt(0)) && name.charAt(0) != '_') return false;
        for (int i = 1; i < name.length(); i++){
            char ch = name.charAt(i);
            if (!Character.isDigit(ch) && !Character.isLetter(ch) && ch != '_') return false;
        }
        return true;
    }
}
