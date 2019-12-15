# MUA-Interpreter
> the final project for the course *Principle of Programming Language*.

It's an interpreter for the functional programming language Mua implemented in Java.
*Notice: It's not the final version and will be updated soon.*

## What's Mua?

Mua (**M**ade-**u**p l**a**nguage) is a functional programming language.

#### Basic concept

+ Mua is make up of several operation lists.
+ Typically a operation list is written in one line, but you can split them in several lines if you like, especially for function definition.
+ Each unit is seperated by **space** (In mua, Space is a broad concept including  space, tab and enter).
+ There are four primitive data types
	- `number`: **Mua doesn't distinguish float or integer**. All Numbers will be stored as float.
    - `word`: It is similar to *string*, encoded by UTF8. **Note that if you want to directly declare a `word`, you must start with `"`.** It's just a representation. i.e. This word actually does not start with `"`. **`Word` can contain `"` or `[]`**, and all the content before the next space will be considered as a part of it.
    - `bool`: only two value can be assigned: `true` and `false`.
    - `list`: **`list` starts with `[` and ends with `]`**. The elements in `list` are seperated by spaces, each of them is a part of this `list`. `list` can be nested, like `[1234.0 2 "Hello [true "false]]`

+ Although stored as `word`, elements should be changed automatically if necessary. For example, the value of `or true "false` is `true`, and the value of `add "1.0 2` is `3.0`.

+ `name` is a part of `word` which satisfied that all its characters are in `[0-9, A-Z, a-z, _]`. `name` is case sensitive.

#### Basic funtions

- important functions:
    - `make <name> <value>`： Bind `value` to `name`.
    - `thing <name>`: Return the value that this `name` binds to.
    - `:<word>`：Syntactic sugar. The same as `thing "word`.
    - `erase <name>`：Erase the connection between `name` and its value.
    - `print <value>`：Print out `value`.
    - `read`：Read a word or a number from input.
    - `readlinst`：Read a list from input, elements are seperated by space.
        ```
        make "a 1
        make "b "a
        print thing "a // => 1.0
        print thing :b // => 1.0
        ```

- functions that check data types:
    ```
    print isnumber 7654321 // => true
    print isword "some_word // => true
    print islist [7654321 "some_word] // => true
    print isbool false // => true
    ```

- functions which is similar to operators
  - `add`, `sub`, `mul`, `div`, `mod`：`<operator> <number> <number>`
  - `eq`, `gt`, `lt`：`<operator> <number|word> <number|word>`
  - `and`, `or`：`<operator> <bool> <bool>`
  - `not`：`not <bool>`

- All functions will be executed in prefix order. `sub add 1 4 2 //->3`

#### Advanced functions

- `if <bool> <list1> <list2>`: if `bool` is `true` then do `list1`, else do `list2`.
- `repeat <number> <list>` Execute `list` for `number` numbers.
- functions defined by user
	+ The form for definition: `make <name> [<list1>, <list2>]`.
	+ `<list1>` is a list contain all the parameters' name that may be used in function.
	+ `<list2>` is the function body, which is made up by several operaion lists.
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
- useful system funtions helped for defined funtion
	+ `stop`: Stop this function immediately.
	+ `output<value>`: The return value of this function is `value`.
	+ `export <name>`: Export the value of `name` from local space to the main space.

#### Expressions

+ For convenience, expression must be surrounded by `()`.
+ Expressions can be mixed up with functions.
```
print (:a + sub 4 3 * 2) // => 3.0 (1 + (4 - 3) * 2 = 3)
print (:a + sub 4 (3*2)) // => -1.0 (1 + (4 - (3*2)) = -1)
```

Continued...

## About this interpreter

Continued...
