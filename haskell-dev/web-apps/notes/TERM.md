
# Yesod Web Framework (Haskell Community)
> <https://www.yesodweb.com/book/haskell>  
> > version 1.6

## Terminology

Even for those familiar with Haskell as a language, there can sometimes be some confusion about terminology. Establishing rapport...

### Data type

This is one of the core building blocks for a strongly typed language like Haskell. Some data types, like `Int`, can be treated as primitive values,  
while other data types will build on top of these to create complicated values. For example, you might represent a person with:  

```haskell
data Person = Person Text Int
```
Here, the `Text` would give the *person's name*, and the `Int` would give the *person's age*. Due to its **simplicity**, this specific example type will  
recur throughout the book. There are essentially three ways you can create a new *data type:*  

- A `type` declaration such as `type GearCount = Int` merely creates a **synonym** for an existing type. The **type system** will do nothing to prevent  
you from using an `Int` where you asked for a `GearCount`. Using this can make your code more self-documenting :)  

- A `newtype` decalaration such as `newtype Make = Make Text` In this case, you cannot accidentally use a `Text` in place of a `Make`; the compiler will  
stop you :) The **newtype wrapper** always disappears during compilation, and will introduce no overhead  

- A `data` declaration, such as `Person` above. You can also create Algebraic Data Types (ADTs), such as  
`data Vehicle = Bicycle GearCount | Car Make Model`

### Data constructor

In our examples above, `Person`, `Make`, and `Vehicle` are all **data constructors**

### Type constructor

Consider the data type `data Maybe a = Just a | Nothing`. In this case, `a` is a type variable

> In both our `Person` and `Make` data types above, our data type and data constructor both share the same name. This is a common practice when dealing  
with a single data constructor. However, there is nothing requiring this be followed; you can always name the data types and data constructors differently  
### Tools

**Stack** is a complete build tool for Haskell which deals with:  
- Glasgow Haskell Compiler, aka GHC  
- libraries (including Yesod)  
- additional build tools (like alex and happy)...and much more
