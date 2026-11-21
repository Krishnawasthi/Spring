# Spring
<img width="816" height="270" alt="image" src="https://github.com/user-attachments/assets/596ad6b2-b3a4-43d4-aa4d-ec8af8c2b615" />

<img width="901" height="256" alt="image" src="https://github.com/user-attachments/assets/688e24f5-9279-4b3b-8464-a16d3300d2ff" />

## Responsiblity of managing object from Application to Inversion of Control(IOC).
#### IOC will give you object. You don't have to use new keyword or create object. IOC will give you…

<img width="1352" height="255" alt="image" src="https://github.com/user-attachments/assets/cf4a8a77-66ce-44b2-b47d-59919699726d" />

<img width="848" height="292" alt="image" src="https://github.com/user-attachments/assets/62ef1b68-f98a-4b70-8db3-2af23b29e7d3" />
### Every bean will have a unique Id. there so many beans in the IOC. to request the bean you need to supply the id.



# POJO Class in Java

## 1. What is POJO?

POJO stands for **Plain Old Java Object**.

A POJO is a simple Java class that does not depend on any specific framework or extend any framework-specific class.

It is generally used to represent and hold data.

## 2. Example

```java
public class Employee {

    private int id;
    private String name;
    private double salary;

    public Employee() {
    }

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

Here, `Employee` is a POJO class.

## 3. Characteristics

- It is an ordinary Java class.
- Generally contains private instance variables.
- Can have constructors, getters and setters.
- Can contain business methods.
- Does not need to extend a specific framework class.
- Does not need to implement a framework-specific interface.

## 4. POJO in Spring

Spring can manage a POJO as a Spring Bean.

**Using XML configuration:**

```xml
<bean id="employee" class="com.example.Employee"/>
```

**Using annotation:**

```java
@Component
public class Employee {
}
```

**Important:** Every Spring Bean is an object, but every POJO is not automatically a Spring Bean.

## 5. POJO vs JavaBean

| POJO | JavaBean |
|---|---|
| General term for ordinary Java objects | Follows specific conventions |
| No strict structural requirements | Traditionally has a public no-argument constructor |
| Getters and setters are optional | Uses getters and setters |
| Serialization is not mandatory | Traditionally implements Serializable |

## 6. Interview Answer

POJO stands for Plain Old Java Object. It is a simple Java class that is independent of any particular framework. It is commonly used to hold data and represent business entities. In Spring, a POJO can be registered in the IoC container, where Spring manages its lifecycle as a Spring Bean.

## 7. Important Relationship

- **POJO:** Ordinary Java object.
- **Spring Bean:** Object managed by the Spring Io
<img width="1247" height="282" alt="image" src="https://github.com/user-attachments/assets/1e4df0ed-f91c-4a91-b91a-4a0af3a48ed5" />
 <img width="1342" height="95" alt="image" src="https://github.com/user-attachments/assets/85a5b377-5cf4-4907-a10d-ba1b6fe197ec" />
 ### this tells us which bean to be injected .
<img width="667" height="133" alt="image" src="https://github.com/user-attachments/assets/264b65ab-2ee9-457a-a36c-6bfaadaede0f" />


## when @Autowwired (dependency ) is there then IOC get confused that which bean to be executed.

### when we have conflict or ambiguity when multiple same of bean definition are returning the same type of bean(here employee type) then used @qualifier.
 <img width="1156" height="496" alt="image" src="https://github.com/user-attachments/assets/4e96c519-fde9-455b-91b4-3b6a2cd980c1" />
## if there is no qualifier then it used @primaryto execute as a default bean.
## qualifier will get the priority.
