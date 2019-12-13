package src.mua;
import src.mua.NameSpace;
import src.mua.Operation.Operation;

import java.util.Vector;

public class Function extends Operation {
    private NameSpace space;
    private Vector<Data> parameters;
    private Data programs;
    Function(String name, NameSpace space) throws MyError{
        if (!space.wholeSpaceContainsName(name))
            throw new MyError(MyError.ErrorType.NameError,
                "Can not find the function [" + name + "].");
        Data value = space.wholeSpaceGetName(name);
        if (value == null)
            throw new MyError(MyError.ErrorType.UnexpectedError,
                    "Expect the function [" + name + "] but actually not find.");
        if (value.type != Data.Type.LIST)
            throw new MyError(MyError.ErrorType.NameError,
                    "Type name [" + name + "] can not be converted to function name.");
        Vector<Data> content = value.getList();
        if (content.size() != 2 || content.get(0).type != Data.Type.LIST || content.get(1).type != Data.Type.LIST)
            throw new MyError(MyError.ErrorType.TypeError,
                    "Name [" + name + "] can not be serving as function.");
        parameters = content.get(0).getList();
        arg_num = parameters.size();
        int sameTimes = 0;
        for (Data p: parameters)
            for (Data q: parameters)
                if (p.getName().equals(q.getName())) sameTimes++;
        if (sameTimes > arg_num)
            throw new MyError(MyError.ErrorType.NameError,
                    "Parameters are duplicated for the function.");
        programs = content.get(1);
        this.name = name;
        this.space = new NameSpace(space);
    }
    public void exec(NameSpace space) throws MyError{
        //It is an interface for class [Operation]
        exec();
    }
    public void exec() throws MyError{
        for (int i = 0; i < parameters.size(); i++)
            space.thisSpacePutName(parameters.get(i).getName(), args.get(i));
        Expression expr = new Expression(space);
        //System.out.println(programs);
        expr.runList(programs);
        if (space.FinalResult != null) {
            this.hasReturnValue = true;
            this.ReturnValue = space.FinalResult;
        }
    }
}
