# Demo: openapi project

Example on how to use OpenAPI specification in your project.

# Why do we need OpenAPI?
OpenAPI Specification (formerly Swagger Specification) is an API description format for REST APIs. An OpenAPI file allows you to describe your entire API, including:

Available endpoints (e.g.:/users) and operations on each endpoint (e.g.: GET /users, POST /users)

Operation parameters for each operation

Authentication methods

Contact information, license, terms of use and other information

API specifications can be written in YAML or JSON.

# Why documentation matters
Good documentation stimulates development and consumption and reduces the money and time needed for meetings / support calls. Documentation is part of the software development and is one of the biggest factors for increased API growth and usage.

# Why OpenAPI?
The OpenAPI Specification, previously known as the Swagger Specification, is a specification for a

- language-agnostic

- machine & human readable

- interface definition language

for describing, producing, consuming and visualizing web services.

In 2015 SmartBear donated the Swagger specification to a new organization called the OpenAPI Initiative, with founding member companies like Apigee, Capital One, Google, IBM, Microsoft, PayPal. So in simple words, it has become the de facto standard for defining RESTful APIs.

OAS establishes a clear agreement for an API, enabling all stakeholders, whether they belong to your development team or are end consumers, to comprehend the API's functionality and interact with its various components without needing to integrate it into their own applications.

This contract is language-agnostic and human-readable, allowing both machines and humans to parse and understand what the API is supposed to do.

# Workflows
There are 2 kind of workflows you can go with:

Define your API in YAML or JSON format, adhering to OpenAPI specs of course and then using Swagger Codegen to generate your code in Java language.

Have your API ready and use springdoc-openapi library to annotate your API according to specs that will be reflected in the Swagger UI.

How to add to a Spring Boot project

## Spring Boot 3
Add to pom.xml:
```
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.3.0</version>
</dependency>
```

## Spring Boot 2

Add to pom.xml:
```
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-ui</artifactId>
  <version>1.7.0</version>
</dependency>
```

# First check

This will automatically deploy swagger-ui to a spring-boot application:

- Documentation will be available in HTML format, using the official swagger-ui jars

- The Swagger UI page will then be available at
  - http://server:port/context-path/swagger-ui.html 
  - or / and
  - http://server:port/context-path/swagger-ui/index.html

- OpenAPI description will be available at

  - http://server:port/context-path/v3/api-docs in JSON format
  - http://server:port/context-path/v3/api-docs.yaml in YAML format