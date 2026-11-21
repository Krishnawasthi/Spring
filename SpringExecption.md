# Spring Bean Exceptions

## 1. NoSuchBeanDefinitionException

**Meaning:** Spring throws this exception when it cannot find a bean matching the requested name or type in the Spring container.

**Example:**
```java
context.getBean("emp3", Employee.class);
```
If only `emp1` and `emp2` are registered, Spring cannot find `emp3`.

**Solution:**
- Check whether the bean is registered.
- Check the bean name.
- Check `@ComponentScan` configuration.
- Ensure the required bean type exists.

## 2. NoUniqueBeanDefinitionException

**Meaning:** Spring throws this exception when multiple beans of the same type are available, and Spring cannot decide which one to inject.

**Example:**
```java
@Autowired
Employee employee;
```
Suppose two Employee beans are registered:
- `emp1`
- `emp2`

Spring gets confused about which bean to inject.

**Solution 1: @Qualifier**

```java
@Autowired
@Qualifier("emp2")
Employee employee;
```
Selects a specific bean by name.

**Solution 2: @Primary**

```java
@Bean
@Primary
public Employee employee1() {
    return new Employee(...);
}
```
Makes `emp1` the default preferred bean.

## Difference Between Both

| Exception | Reason |
|---|---|
| NoSuchBeanDefinitionException | No matching bean found. |
| NoUniqueBeanDefinitionException | Multiple matching beans found. |

### Remember
- **NoSuch** = Bean does not exist.
- **NoUnique** = Multiple matching beans exist.

Both exceptions are related to bean resolution in the Spring IoC container.
