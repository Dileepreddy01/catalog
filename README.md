# catalog
Shamir's Secret Sharing - Constant Term Calculation
Overview
This project implements a simplified version of Shamir's Secret Sharing algorithm. The main objective is to decode polynomial roots provided in a JSON format and compute the constant term of the polynomial using Lagrange interpolation.

Problem Statement
Given a polynomial of degree ( m ) represented as:

[ f(x) = a_m x^m + a_{m-1} x^{m-1} + \ldots + a_1 x + c ]

The task is to find the constant term ( c ) using the roots provided in a specific format. The roots are encoded in different bases, and the goal is to decode these values and use them to find the polynomial coefficients.

Requirements
Node.js (version 12 or higher)
NPM (Node Package Manager)
