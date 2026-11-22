
# Spring Bean Scope

## What is Bean Scope?

Bean Scope defines how many instances of a bean Spring creates and how those instances are shared within the Spring Container.

In simple words, it decides whether Spring creates one object or multiple objects of a bean.

## Types of Bean Scope

Spring provides 6 types of bean scope:

| Scope | Description |
|---|---|
| Singleton | Creates only one object per Spring IoC container. (Default scope) |
| Prototype | Creates a new object every time the bean is requested. |
| Request | Creates one object for each HTTP request. |
| Session | Creates one object for each HTTP session. |
| Application | Creates one object per ServletContext. |
| WebSocket | Creates one object per WebSocket session. |

## 1. Singleton Scope

- Default scope in Spring.
- Spring creates only one instance of the bean.
- Every request returns the same object.

## 2. Prototype Scope

- Spring creates a new instance every time the bean is requested.
- Each request returns a different object.

## Example

```java
Employee e1 = context.getBean(Employee.class);
Employee e2 = context.getBean(Employee.class);

System.out.println(e1 == e2);
```

**Output:**

- Singleton: `true`
- Prototype: `false`

## How to Define Scope?

Using `@Scope` annotation:

```java
@Component
@Scope("prototype")
public class Employee {
}
```

For singleton:

```java
@Component
@Scope("singleton")
public class Employee {
}
```

## Important Points

- Singleton beans are shared across the Spring Container.
- Prototype beans are created whenever requested.
- Spring does not manage the complete destruction lifecycle of prototype beans.
- Singleton is the default scope.

## Key Difference

**Singleton:** One object, multiple references.

**Prototype:** Multiple requests, multiple objects.
