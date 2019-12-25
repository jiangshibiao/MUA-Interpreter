# MUA-Interpreter
> the final project for the course *Principle of Programming Language*.

It's an interpreter for the functional programming language Mua implemented in Java.
*Notice: It's not the final version and will be updated soon.*

## What's Mua?

Mua (**M**ade-**u**p l**a**nguage) is a functional programming language created by my instructor Kai Weng.
It is well defined and can be easily parsed without the knowledge of compiling principle.

#### Basic concept

+ Mua is made up of several operation lists. Typically a operation list is written in one line, but you can split them in several lines if you like, especially for function definition.
+ Each unit is seperated by ***space***. In mua, *space* is a generalized concept including space, tab and enter.
+ Consider the concept of ***literal notation***: If you want to create a variable in your code, you may use some *notation* to let the interpreter know the *type* of it. But it's just a representation. i.e. Actually this variable doesn't contain this *notation*.
+ There are four primitive data types: `number`, `word`, `bool`, `list`.
	- `number`: Mua doesn't distinguish float or integer. i.e. All Numbers will be stored as float.
    - `word`: It is similar to *string*, encoded by UTF8. The *literal notation* of *word* is a starting character `"`, and all the characters before the next *space* will be considered as a part of it. In particular, we suppose that *word* will not contain`[` and `]`（For the convenience of interpreting *list*）.
    - `bool`: only two value can be assigned: `true` and `false`.
    - `list`: The *literal notation* of *list* is a pair of bracket. i.e. Starts with `[` and ends with `]`. The elements in *list* are seperated by spaces, each of them serves as a element in this *list*. *list* can be nested, like `[1234.0 2 "Hello [true "false]]`.
+ Although stored as `word`, elements should be changed automatically if necessary. For example, the value of `or true "false` is `true`, and the value of `add "1.0 2` is `3.0`.
+ `name` is a subset of `word` which satisfied that all the characters are in `[0-9, A-Z, a-z, _]` and the first character isn't a digit. `name` is used to define variables and functions, and is case sensitive.
+ Functions will be executed in prefix order.

#### Some important tips

+ Review the definition of `list`: All the elements seperated by space belong to  it without parsing. So `[(2+4)]` $\ne$ `[6]`, and the elements of `[(2 +4)]` are `(2` and `+4`.
+ A great advantange of mua is: One can quickly distinguish a phrase by looking at its first character.
	- If it is `"`, then a `word` comes later.
	- If it is a digit or `-`, then a `number` comes later.
	- If it is `[`, then a `list` comes later.
	- If it is `(`, then a expression comes later.
	- If it is in `[+,-,*,/,%]`, then it is a operator.
	- Otherwise, if this phrase isn't equal to `true` or `false`(In this case, a `bool` comes later), then it must be a function.


#### System Funtions

+ some important functions:
    - `make <name> <value>`： Bind `value` to `name`.
    - `thing <name>`: Return the value that `name` binds to.
    - `:<word>`：Syntactic sugar. The same as `thing "word`.
    	```
        make "a 1
        make "b "a
        print thing "a // => 1.0
        print thing :b // => 1.0
        ```
    - `erase <name>`：Erase the connection between `name` and its binding value.
    - `print <value>`：Print out `value`.
    - `read`：Read a `word` from input. Do not need *literal notation* `"`.
    - `readlist`：Read a `list` from input, elements are seperated by space. Do not need *literal notation* `[` and `]`.
    - `if <bool> <list1> <list2>`: if `bool` is `true` then do `list1`, else do `list2`.
	- `repeat <number> <list>` Execute `list` for `number` numbers.


+ functions that check data types:
    ```
    print isnumber 7654321 // => true
    print isword "some_word // => true
    print islist [7654321 "some_word] // => true
    print isbool false // => true
    ```

+ functions which is similar to operators
  - `add`, `sub`, `mul`, `div`, `mod`：`<operator> <number> <number>`
  - `eq`, `gt`, `lt`：`<operator> <number|word> <number|word>`
  - `and`, `or`：`<operator> <bool> <bool>`
  - `not`：`not <bool>`

Continued...

#### Functions defined by user

+ The form for definition: `make <name> [<list1>, <list2>]`.
	- `<list1>` is a list contain all the parameters' name that may be used in function.
	- `<list2>` is the function body, which is made up by several operaion lists.
    ```
    make "prt [
        [a]
        [print :a]
    ]
    ```
    + The form for call: `<name> <arglist>`
    + `arglist` is a list whose length is the same as `list1`. `arglist` contains parameters conveyed to this function, and Mua will binds each value to its corresponding parameter and then execute this function.
    ```
    make "b 233
    prt "b
    ```
- useful system funtions helped for designing funtion
	+ `stop`: Stop this function immediately.
	+ `output<value>`: The return value of this function is `value`.
	+ `export <name>`: Export the value of `name` from local space to the main space.

#### Expressions

+ For convenience, expression should be surrounded by `()`.
+ Expressions can be mixed up with functions and other expressions.
+ Consecutive negative indications should be supported.
```
print (:a + sub 4 3 * 2) // => 3.0 (1 + (4 - 3) * 2 = 3)
print (:a + sub 4 (3*2)) // => -1.0 (1 + (4 - (3*2)) = -1)
print (1+---2) // --> -1.0
```

Continued...

## About this interpreter

#### Basic Concept

~~~mermaid
graph TB
    id1((myServer))-->id2[clientQueue]
    id1-->id3[messageQueue]
    id2==1..*==>id4((clientHandle))
    id4-->id5[socket]
    id4-->id6[ID]
    id4-.parent.->id1
~~~
