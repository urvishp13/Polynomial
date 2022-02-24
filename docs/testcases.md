# Test Cases

Note: Next to the test cases are the files used for the test case

### Testing add()

1. add 2 NON-ZERO polynomials: `ptest1.txt` and `ptest2.txt`
2. add 2 ZERO polynomials: `emptyPoly.txt` and `emptyPoly.txt`
3. add 1 NON-ZERO and 1 ZERO polynomial: `ptest1.txt` and `emptyPoly.txt`
4. add 1 polynomial with its OPPOSITE to get a 0 sum: `ptest1.txt` and `ptest1opp.txt`

### Testing multiply()

1. multiply 2 NON-ZERO polynomials: `ptest1.txt` and `ptest2.txt`
2. multiply 2 ZERO polynomials: `emptyPoly.txt` and `emptyPoly.txt`
3. multiple 1 NON-ZERO polynomial and 1 ZERO polynomial: `ptest1.txt` and `emptyPoly.txt`

### Testing evaluate()

1. evaluate `ptest1.txt` as shown in at `x = 2`, as shown in [problem_specs](https://github.com/urvishp13/Polynomial/blob/main/docs/problem_specs.pdf)
2. evaluate a polynomial with `x = 0`