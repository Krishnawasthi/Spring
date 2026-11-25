# Spring Web MVC – Complete Request-Response Flow

## 1. What is Spring Web MVC?

Spring Web MVC is a web framework built on the Model-View-Controller (MVC) design pattern. It is used to develop web applications by separating application responsibilities into three main parts:

- **Model:** Handles and carries application data.
- **View:** Responsible for presenting data to the user.
- **Controller:** Handles incoming requests and coordinates application processing.

Spring Web MVC follows the **Front Controller Design Pattern**, where `DispatcherServlet` acts as the central entry point for incoming HTTP requests.

---

## 2. Spring Web MVC Architecture

The overall request-response flow:

```text
                 CLIENT / BROWSER
                        |
                        | HTTP Request
                        v
                DispatcherServlet
                  (Front Controller)
                        |
                        v
                  HandlerMapping
                        |
                        v
                  HandlerAdapter
                        |
                        v
                    Controller
                        |
                +-------+-------+
                |               |
                v               v
              Model         View Name
                |               |
                +-------+-------+
                        |
                        v
                 DispatcherServlet
                        |
                        v
                   ViewResolver
                        |
                        v
                      View
                    (JSP/HTML)
                        |
                        v
                 HTTP Response
                        |
                        v
                 CLIENT / BROWSER
```

**Note:** This represents the traditional view-rendering flow. REST-style responses using `@ResponseBody` or `@RestController` follow a different path.

---

## 3. Important Components of Spring Web MVC

### 3.1 DispatcherServlet

DispatcherServlet is the Front Controller of Spring MVC.

Responsibilities:

- Receives incoming HTTP requests.
- Coordinates request processing.
- Communicates with HandlerMapping.
- Uses HandlerAdapter to invoke the selected controller.
- Coordinates view resolution and response rendering.

It acts as the central coordinator of the Spring MVC request-response lifecycle.

### 3.2 HandlerMapping

HandlerMapping identifies which controller or controller method should handle an incoming request.

For example:

```java
@GetMapping("/checkPNR")
public String checkPNR() {
    return "index";
}
```

When a request arrives for `/checkPNR`, HandlerMapping identifies the corresponding handler method.

### 3.3 HandlerAdapter

HandlerAdapter is responsible for invoking the handler selected by HandlerMapping.

It provides a common mechanism for DispatcherServlet to execute different types of handlers.

### 3.4 Controller

A Controller handles incoming requests and coordinates application logic.

Example:

```java
@Controller
public class PNRController {

    @GetMapping("/checkPNR")
    public String checkPNR(Model model) {

        model.addAttribute("pnr", "1234567890");

        return "index";
    }
}
```

Responsibilities:

- Handles incoming requests.
- Processes request parameters.
- Calls business or service-layer logic.
- Adds data to Model.
- Returns a logical view name or response data.

### 3.5 Model

Model is used to carry data from the Controller to the View.

Example:

```java
model.addAttribute("pnr", "1234567890");

model.addAttribute("status", "CONFIRMED");
```

Here:

- `pnr` is the attribute name.
- `1234567890` is the attribute value.
- `status` is another attribute name.
- `CONFIRMED` is its value.

The JSP can access these attributes using Expression Language (EL).

```jsp
<h2>PNR Number: ${pnr}</h2>

<h3>Status: ${status}</h3>
```

### 3.6 ViewResolver

ViewResolver resolves the logical view name returned by the Controller into an actual view resource.

Example configuration:

```xml
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">

    <property name="prefix" value="/WEB-INF/views/"/>

    <property name="suffix" value=".jsp"/>

</bean>
```

Suppose the Controller returns:

```java
return "index";
```

Spring combines:

| Configuration | Value |
|---|---|
| Prefix | `/WEB-INF/views/` |
| View Name | `index` |
| Suffix | `.jsp` |

Final resolved path:

```text
/WEB-INF/views/index.jsp
```

### 3.7 View

The View is responsible for presenting data to the user.

Examples:

- JSP
- Thymeleaf
- Other supported view technologies

The View uses Model data and generates the final HTML response.

### 3.8 HttpMessageConverter

HttpMessageConverter is used when Spring needs to convert Java objects into HTTP response formats such as JSON or XML, or convert incoming request bodies into Java objects.

It is commonly used with REST APIs.

---

## 4. Complete Request-Response Flow

Let's understand the complete lifecycle using the IRCTC PNR Checker application.

### Step 1: Client Sends an HTTP Request

The user opens the application and clicks the Check PNR button.

Example:

```html
<a href="checkPNR">Check PNR</a>
```

The browser sends:

```http
GET /com-check-pnr/checkPNR
```

The request reaches the Tomcat server.

### Step 2: Tomcat and web.xml

Tomcat reads the web application's deployment configuration from `web.xml`.

Example:

```xml
<servlet>

    <servlet-name>pnr-check</servlet-name>

    <servlet-class>
        org.springframework.web.servlet.DispatcherServlet
    </servlet-class>

</servlet>

<servlet-mapping>

    <servlet-name>pnr-check</servlet-name>

    <url-pattern>/</url-pattern>

</servlet-mapping>
```

This configures DispatcherServlet as the default servlet mapping for the application.

### Step 3: DispatcherServlet Receives the Request

DispatcherServlet receives the incoming request.

It acts as the Front Controller and coordinates the remaining request-processing steps.

### Step 4: HandlerMapping Identifies the Controller

DispatcherServlet consults HandlerMapping to identify the appropriate controller method.

Example:

```java
@GetMapping("/checkPNR")
public String checkPNR(Model model) {

    return "index";

}
```

HandlerMapping identifies this method as the matching handler for `/checkPNR`.

### Step 5: HandlerAdapter Invokes the Controller

DispatcherServlet uses HandlerAdapter to invoke the selected controller method.

HandlerAdapter handles the actual invocation of the handler.

### Step 6: Controller Processes the Request

The Controller executes the application logic.

Example:

```java
@Controller
public class PNRController {

    @GetMapping("/checkPNR")
    public String checkPNR(Model model) {

        model.addAttribute("pnr", "1234567890");

        model.addAttribute("status", "CONFIRMED");

        return "index";
    }
}
```

Here, the Controller:

1. Receives the request.
2. Prepares dummy PNR information.
3. Adds the information to Model.
4. Returns the logical view name `index`.

### Step 7: Model Carries Data to the View

The Model contains the attributes added by the Controller.

Example:

```java
model.addAttribute("pnr", "1234567890");

model.addAttribute("status", "CONFIRMED");
```

The Model makes this information available to the selected view during request processing.

### Step 8: ViewResolver Resolves the View

The Controller returns:

```java
return "index";
```

The InternalResourceViewResolver combines the prefix, logical view name and suffix.

Result:

```text
/WEB-INF/views/index.jsp
```

### Step 9: View Generates the HTML

The JSP contains:

```jsp
<h2>IRCTC PNR Status</h2>

<p>PNR Number: ${pnr}</p>

<p>Current Status: ${status}</p>
```

JSP Expression Language retrieves the corresponding request attributes.

The JSP engine generates the final HTML.

### Step 10: HTTP Response Goes to the Browser

The generated HTML is sent back through the HTTP response.

The browser receives the HTML, applies CSS and displays the final PNR result page.

---

## 5. Two Major Response Approaches in Spring MVC

Spring MVC supports both traditional view-based responses and REST-style responses.

### Approach 1: Traditional MVC – View-Based Response

Example:

```java
@GetMapping("/checkPNR")
public String checkPNR(Model model) {

    model.addAttribute("pnr", "1234567890");

    model.addAttribute("status", "CONFIRMED");

    return "index";
}
```

Execution flow:

```text
Client
  |
  v
DispatcherServlet
  |
  v
HandlerMapping
  |
  v
HandlerAdapter
  |
  v
Controller
  |
  v
Model + View Name
  |
  v
ViewResolver
  |
  v
JSP
  |
  v
HTML Response
  |
  v
Browser
```

**Important:** In this approach, Spring resolves a logical view name and renders a view.

### Approach 2: REST-Style – Data-Based Response

Example:

```java
@GetMapping("/checkPNR")
@ResponseBody
public String checkPNR() {

    return "PNR Status: CONFIRMED";

}
```

Execution flow:

```text
Client
  |
  v
DispatcherServlet
  |
  v
HandlerMapping
  |
  v
HandlerAdapter
  |
  v
Controller
  |
  v
HttpMessageConverter
  |
  v
HTTP Response
  |
  v
Client
```

Here, `@ResponseBody` tells Spring to write the returned value directly into the HTTP response body.

Spring does not resolve a JSP view for this method.

### Difference Between Both Approaches

| Traditional MVC | REST-Style |
|---|---|
| Uses `@Controller` | Uses `@RestController` or `@ResponseBody` |
| Returns logical view name | Returns response data |
| Uses ViewResolver | Uses HttpMessageConverter where applicable |
| Commonly renders JSP/HTML | Commonly returns JSON/XML |
| Suitable for server-rendered web applications | Suitable for APIs and client-side applications |

---

## 6. Other Important Components

### HandlerInterceptor

HandlerInterceptor allows custom logic to execute around controller processing.

Common methods:

- `preHandle()` – Executes before the controller.
- `postHandle()` – Executes after the controller but before view rendering.
- `afterCompletion()` – Executes after request processing and view rendering.

### HandlerExceptionResolver

It handles exceptions that occur during Spring MVC request processing.

It helps generate appropriate error responses instead of exposing unhandled exceptions.

### MultipartResolver

It provides support for handling multipart requests, especially file uploads.

---

## 7. Important Interview Questions

1. What is Spring Web MVC?
2. What is the Front Controller design pattern?
3. What is DispatcherServlet?
4. What is the role of HandlerMapping?
5. What is the difference between HandlerMapping and HandlerAdapter?
6. How does DispatcherServlet identify the controller method?
7. What is the role of Model in Spring MVC?
8. What is ViewResolver?
9. How does InternalResourceViewResolver work?
10. What happens internally when a Controller returns a String?
11. What is the difference between `@Controller` and `@RestController`?
12. What is the difference between `@ResponseBody` and returning a view name?
13. What is HttpMessageConverter?
14. What is HandlerInterceptor?
15. What is HandlerExceptionResolver?
16. How does Spring MVC handle exceptions?
17. What is the difference between Model, ModelMap and ModelAndView?
18. Can we use Spring MVC without JSP?
19. What happens when no matching HandlerMapping is found?
20. What happens when the ViewResolver cannot find the requested view?

---

## 8. Quick Revision – Complete Flow

Remember this sequence for interviews:

**Traditional MVC View Rendering:**

```text
Client
  ↓
DispatcherServlet
  ↓
HandlerMapping
  ↓
HandlerAdapter
  ↓
Controller
  ↓
Model + Logical View Name
  ↓
ViewResolver
  ↓
View (JSP)
  ↓
HTML Response
  ↓
Client
```

**REST-Style Response:**

```text
Client
  ↓
DispatcherServlet
  ↓
HandlerMapping
  ↓
HandlerAdapter
  ↓
Controller
  ↓
HttpMessageConverter (when applicable)
  ↓
HTTP Response
  ↓
Client
```

### Final Takeaway

- DispatcherServlet is the Front Controller.
- HandlerMapping identifies the appropriate handler.
- HandlerAdapter invokes the handler.
- Controller processes the request.
- Model carries data to the view.
- ViewResolver resolves the logical view name.
- View generates the presentation.
- HttpMessageConverter supports conversion between Java objects and HTTP message formats.

**The most important concept:** Spring Web MVC separates request handling, business processing, data transfer and presentation into different responsibilities. This separation makes web applications easier to maintain, test and extend.
