# Ex2 - Foundation of Object-Oriented and Recursion

## Introduction

This project is an implementation of a basic Spreadsheet system, as part of an assignment for the course *Introduction to Computer Science* at Ariel University, School of Computer Science.

The main objective of this assignment is to implement a `Spreadsheet` that supports **Cells**, where each cell can either hold:
- **Text**
- **Number**
- **Formula**

Formulas can be simple expressions such as:
- `=number`
- `=(Formula)`
- `=Formula op Formula` (where op is one of `{+,-,*,/}`)
- `=cell` (referencing another cell in the spreadsheet)

Additionally, the system should support cycle detection (to prevent infinite references between cells), formula evaluation, and error handling (for invalid formulas).

## Features

### **Cell Types:**
- **Number:** Cells that store numerical values (e.g., `=1.2`, `=2.5`).
- **Text:** Cells that store textual values (e.g., `"Hello"`, `"2a"`).
- **Formula:** Cells that store formulas, which could be:
  - Basic arithmetic expressions (`=1+2*2`)
  - Referencing other cells (`=A1`)
  - Nested formulas (`=(A1+B1)*2`)

### **Spreadsheet:**
- A 2D array of `Cells` where each cell can hold one of the three data types (Number, Text, Formula).
- Support for retrieving and setting cell values.
- Evaluation of cells and formulas to compute their actual values.

### **Main Methods:**
- **isNumber(String text):** Checks if the given text is a valid number.
- **isText(String text):** Checks if the given text is a valid text.
- **isForm(String text):** Checks if the given text is a valid formula.
- **computeForm(String form):** Computes the value of a formula (e.g., `=1+2*2`).

### **Spreadsheet Methods:**
- **generate(int x, int y):** Initializes a new spreadsheet with dimensions `x` by `y`.
- **get(int x, int y):** Returns the `Cell` at position `(x, y)`.
- **set(int x, int y, Cell c):** Sets the `Cell` at position `(x, y)` to the specified `Cell` object.
- **eval(int x, int y):** Returns the evaluated value (as a string) of the `Cell` at `(x, y)`.
- **evalAll():** Returns the evaluated values of all cells in the spreadsheet.
- **depth():** Computes the computational depth of each cell, considering dependencies between cells.

## Project Structure

### **Cell Class**
The `Cell` class represents an individual cell in the spreadsheet, with the following key methods:
- `boolean isNumber(String text)`
- `boolean isText(String text)`
- `boolean isForm(String text)`
- `Double computeForm(String form)`

### **Spreadsheet Class**
The `Spreadsheet` class contains a 2D array of `Cells`, and provides methods to interact with individual cells, evaluate formulas, and compute the depth of dependencies.

### **Formula Evaluation**
The formula evaluation logic supports handling complex formulas, including nested formulas and arithmetic operations. It also ensures that circular references are detected (i.e., if a cell refers to itself directly or indirectly).

### **Cycle Detection**
A formula that references itself directly or indirectly will result in a cycle. This is detected and flagged as an error.

## Setup & Running

### Requirements:
- Java 11 or higher.
- Integrated Development Environment (IDE) such as IntelliJ IDEA (or any other IDE that supports Java).

### Running the Project:
1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/yourusername/Ex2.git
   ```

2. Open the project in your preferred IDE (IntelliJ IDEA recommended).

3. Build and run the main class that contains the GUI or the command-line interface for testing.

4. To test the functionality, you can implement a simple Spreadsheet with some test cases for formulas and text.

### Running with GUI:
- The project includes a basic GUI to visualize and interact with the spreadsheet. To use the GUI, run the main class `Ex2GUI`.

### Example:
- After starting the application, you can load a spreadsheet, input formulas into cells, and see the calculated results.

## Error Handling

- **ERR_FORM**: This error occurs if the cell contains invalid input such as non-numeric text in a cell expected to hold a number.
- **ERR_CYCLE**: A cycle error occurs if a formula directly or indirectly references the cell it resides in, causing an infinite loop.
- **ERR_WRONG_FORM**: This error is triggered when a formula is malformed or contains illegal characters.
![image](https://github.com/user-attachments/assets/541861c7-3607-460f-a582-f91ed882674e)


