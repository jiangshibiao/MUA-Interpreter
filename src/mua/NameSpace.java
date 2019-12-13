package src.mua;
import src.mua.MyError;
import java.util.HashMap;
import java.util.Scanner;
public class NameSpace {
    private HashMap<String, Data> map = new HashMap<>();
    private Scanner readScanner;
    private NameSpace parentSpace;
    public OperationRun operationRun;
    public Data FinalResult;
    public boolean stopFlag = false;
    public NameSpace(Scanner readScanner){
        this.readScanner = readScanner;
        this.operationRun = new OperationRun();
        this.parentSpace = null;
        this.FinalResult = null;
    }
    public NameSpace(NameSpace parentSpace){
        this.readScanner = parentSpace.readScanner;
        this.operationRun = parentSpace.operationRun;
        this.parentSpace = parentSpace;
        this.FinalResult = null;
    }
    public NameSpace getMainSpace(){
        return (parentSpace == null) ? this : parentSpace.getMainSpace();
    }
    public boolean thisSpaceContainsName(String name){
        return map.containsKey(name);
    }
    public boolean wholeSpaceContainsName(String name){
        return thisSpaceContainsName(name) || parentSpace != null && parentSpace.wholeSpaceContainsName(name);
    }
    public Data thisSpaceGetName(String name){
        return map.get(name);
    }
    public Data wholeSpaceGetName(String name){
        if (thisSpaceContainsName(name)) return thisSpaceGetName(name);
        return parentSpace == null ? null : parentSpace.wholeSpaceGetName(name);
    }
    public void thisSpacePutName(String name, Data curData){
        //System.out.println("!!" + name + " " + curData);
        map.put(name, curData);
        //System.out.println(map.get(name));
    }
    public void mainSpacePutName(String name, Data curData){
        getMainSpace().thisSpacePutName(name, curData);
    }
    public void thisSpaceRemoveName(String name){
        //make sure name is already in map.
        map.remove(name);
    }
    public void thisSpaceListName(){
        for (String name: map.keySet())
            System.out.println(name);
    }
    public void thisSpaceRemoveAllName(){
        map.clear();
    }
    public void wholeSpaceRemoveName(String name) throws MyError{
        if (thisSpaceContainsName(name))
            thisSpaceRemoveName(name);
        else if (parentSpace != null)
            parentSpace.wholeSpaceRemoveName(name);
        else
            throw new MyError(MyError.ErrorType.UnexpectedError,
                    "You should assure that [wholeSpaceRemoveName] will be executed correctly but actually not.");
    }
    public HashMap<String, Data> thisSpaceAllName(){
        return map;
    }
    public String readNext(){
        return readScanner.next();
    }
    public String readNextLine() {
        return readScanner.nextLine();
    }
}
