# Portfolio Management System
## _Developed using Spring Boot Microservices_



A leading Financial Services Organization wants to strengthen its Middleware by exposing the core logic related to Portfolio Management as Microservices. This middle ware Microservices will be hosted on Cloud so that all the up/downstream applications can get an access to this for performing business transactions. There will also be a customer Portal to be developed part of this scope that consumes these Microservices to view their portfolio information and sell their assets.

- 3 Microservices
- 1 Spring MVC web portal


## Features

- Daily Share Price Module: daily Share Price Module is a Middleware Microservice that performs following operation:
    - Get daily share price of a stock

- Daily Mutual Fund NAV Module: Daily Mutual Fund NAV Module is a Middleware Microservice that performs the following operation: 
    -	Get NAV value of a Mutual Fund
- Calculate Net worth Module: Calculate Net worth Module is a Middleware Microservice that performs the following operations:
    - Calculate the current value of share holdings and mutual fund holdings and find out the total current value or net-worth
    - Sell Assets and determine the final net-worth
- Customer Portal Module: An Web Portal that allows a customer to Login and allows to do following operations:
    - Login
    - View the portfolio holdings & networth
    - Sell assets
