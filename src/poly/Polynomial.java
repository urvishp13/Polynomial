package poly;

import java.io.*;
import java.util.StringTokenizer;

/**
 * This class implements a term of a polynomial.
 * 
 * @author runb-cs112
 *
 */
class Term {
	/**
	 * Coefficient of term.
	 */
	public float coeff;
	
	/**
	 * Degree of term.
	 */
	public int degree;
	
	/**
	 * Initializes an instance with given coefficient and degree.
	 * 
	 * @param coeff Coefficient
	 * @param degree Degree
	 */
	public Term(float coeff, int degree) {
		this.coeff = coeff;
		this.degree = degree;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		return other != null &&
		other instanceof Term &&
		coeff == ((Term)other).coeff &&
		degree == ((Term)other).degree;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (degree == 0) {
			return coeff + "";
		} else if (degree == 1) {
			return coeff + "x";
		} else {
			return coeff + "x^" + degree;
		}
	}
}

/**
 * This class implements a linked list node that contains a Term instance.
 * 
 * @author runb-cs112
 *
 */
class Node {
	
	/**
	 * Term instance. 
	 */
	Term term;
	
	/**
	 * Next node in linked list. 
	 */
	Node next;
	
	/**
	 * Initializes this node with a term with given coefficient and degree,
	 * pointing to the given next node.
	 * 
	 * @param coeff Coefficient of term
	 * @param degree Degree of term
	 * @param next Next node
	 */
	public Node(float coeff, int degree, Node next) {
		term = new Term(coeff, degree);
		this.next = next;
	}
}

/**
 * This class implements a polynomial.
 * 
 * @author runb-cs112
 * @author Urvish Patel
 *
 */
public class Polynomial {
	
	/**
	 * Pointer to the front of the linked list that stores the polynomial. 
	 */ 
	Node poly;
	
	/** 
	 * Initializes this polynomial to empty, i.e. there are no terms.
	 *
	 */
	public Polynomial() {
		poly = null;
	}
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param br BufferedReader from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 */
	public Polynomial(BufferedReader br) throws IOException {
		String line;
		StringTokenizer tokenizer;
		float coeff;
		int degree;
		
		poly = null;
		
		while ((line = br.readLine()) != null) {
			tokenizer = new StringTokenizer(line);
			coeff = Float.parseFloat(tokenizer.nextToken());
			degree = Integer.parseInt(tokenizer.nextToken());
			poly = new Node(coeff, degree, poly);
		}
	}
	
	
	/**
	 * Returns the polynomial obtained by adding the given polynomial p
	 * to this polynomial - DOES NOT change this polynomial
	 * 
	 * @param p Polynomial to be added
	 * @return A new polynomial which is the sum of this polynomial and p.
	 */
	public Polynomial add(Polynomial p) { // the second Polynomial p is already made
		/** COMPLETE THIS METHOD **/
		Polynomial sum = new Polynomial();
		Node thisPolyNode = this.poly; // Got this polynomial's Linked List
		Node secondPolyNode = p.poly; // Got the polynomial-to-be-added's Linked List
		Node prevAddedTerm = null;
		
		/* Start adding the coefficients of the Terms from the two polynomials and Store them in sum */
		while (thisPolyNode != null && secondPolyNode != null) {
			//System.out.println("current thisPoly term: " + thisPolyNode.term.toString());
			//System.out.println("current secondPoly term: " + secondPolyNode.term.toString());
			// If the degrees of the Terms from the two polynomials are the same,
			//		AND the sum of their coefficients does not equal 0
			if (thisPolyNode.term.degree == secondPolyNode.term.degree) {
				if (thisPolyNode.term.coeff + secondPolyNode.term.coeff != 0) { 
					// Add their coefficients
					float coeff = thisPolyNode.term.coeff + secondPolyNode.term.coeff;
					int degree = thisPolyNode.term.degree;
					// Create a new Node with the new coefficient
					Node newSummedTerm = new Node(coeff, degree, null);
					// Add it to sum
					prevAddedTerm = addTermNodeToPolynomial(sum, newSummedTerm, prevAddedTerm);
					// Go on to the next term in both polynomials
					thisPolyNode = thisPolyNode.next;
					secondPolyNode = secondPolyNode.next;
				}
				else { // the two terms' coefficients do equal to zero
					// Go on to the next term in both polynomials
					thisPolyNode = thisPolyNode.next;
					secondPolyNode = secondPolyNode.next;
				}
			}
			// Else, (the degrees are different)
			else {
				// If this polynomial's degree > the second polynomial's degree
				if (thisPolyNode.term.degree > secondPolyNode.term.degree) {
					// Add the second polynomial's term Node to the sum
					if (prevAddedTerm != null) { // if not dealing with the first term Node in the LL
						prevAddedTerm.next = secondPolyNode;
					}
					else { // else, (if dealing with the first term in polynomial)
						sum.poly = secondPolyNode; // simply, add it to the sum
					}
					prevAddedTerm = secondPolyNode;
					// Go on to the next term in the second polynomial
					secondPolyNode = secondPolyNode.next;
				}
				// Else, (this polynomial's degree < the second polynomial's degree)
				else {
					// Add this polynomial's term Node to the sum
					if (prevAddedTerm != null) {
						prevAddedTerm.next = thisPolyNode;
					}
					else {
						sum.poly = thisPolyNode;
					}
					prevAddedTerm = thisPolyNode;
					// Go on to the next term in this polynomial
					thisPolyNode = thisPolyNode.next;
				}
			}
			
		}
		
		/* While loop ended meaning reached the end of at least one of the polynomials */
		// If the sum polynomial Linked List has values
		if (prevAddedTerm != null) {
			// If reached the end of the second polynomial
			if (secondPolyNode == null) {
				// Add everything remaining from this polynomial
				prevAddedTerm.next = thisPolyNode;
				//System.out.println(sum);
			}
			// If reached the end of this polynomial
			if (thisPolyNode == null) {
				// Add everything remaining from the second polynomial
				prevAddedTerm.next = secondPolyNode;
			}
		}
		/* The above 2 if-statements take into consideration the case in which the end of both the polynomials
		 * has been reached
		 */
		// Else, (no terms have been added to the sum LL)
		else {
			// If the second polynomial is empty
			if (p.poly == null) {
				// Add everything from this polynomial to the sum LL
				sum.poly = this.poly;
			}
			// If this polynomial is empty
			if (this.poly == null) {
				// Add everything from the second polynomial to the sum LL
				sum.poly = p.poly;
			}
		}
		
		return sum;
	} 
	
	/**
	 * Used to add the termNode to the Polynomial
	 * 
	 * @param p the Polynomial
	 * @param termNode the new node to be added to the Polynomial
	 * @param prevAddedTerm the previous added term to the Polynomial
	 * @return the most recent term added to the Polynomial
	 */
	private Node addTermNodeToPolynomial(Polynomial p, Node termNode, Node prevAddedTerm) {
		// Add new node to the polynomial
		if (p.poly == null) { // first term in poly
			p.poly = termNode;
			prevAddedTerm = termNode;
		}
		else {
			prevAddedTerm.next = termNode;
			prevAddedTerm = termNode;
		}
		return prevAddedTerm;
	}
	
	/**
	 * Returns the polynomial obtained by multiplying the given polynomial p
	 * with this polynomial - DOES NOT change this polynomial
	 * 
	 * @param p Polynomial with which this polynomial is to be multiplied
	 * @return A new polynomial which is the product of this polynomial and p.
	 */
	public Polynomial multiply(Polynomial p) {
		/** COMPLETE THIS METHOD **/
		Node thisPoly = this.poly;
		Node secondPoly = p.poly;
		Polynomial product = new Polynomial(); // stores the product from each iteration (line) of multiplication
		Polynomial sum = new Polynomial(); // used to add all the product lines to get the final product
		Node prevAddedTerm = null;
		
		// Go through each coeff in second poly
		for (Node currSecondPolyTermNode = secondPoly; 
				currSecondPolyTermNode != null; 
				currSecondPolyTermNode = currSecondPolyTermNode.next) {
			float currSecondPolyTermCoeff = currSecondPolyTermNode.term.coeff;
			/* Multiply it with each coeff in this poly */
			for (Node currThisPolyTermNode = thisPoly;
					currThisPolyTermNode != null;
					currThisPolyTermNode = currThisPolyTermNode.next) {
				float currThisPolyTermCoeff = currThisPolyTermNode.term.coeff;
				float twoTermsMultipliedCoeff = currSecondPolyTermCoeff * currThisPolyTermCoeff;
				// Create new Node term of multiplied coeff with degree of second poly's coeff's degree
				//		+ this poly's coeff's degree
				Node twoTermsMultiplied = new Node(twoTermsMultipliedCoeff, 
						currSecondPolyTermNode.term.degree + currThisPolyTermNode.term.degree, null);
				// Add node to product
				prevAddedTerm = addTermNodeToPolynomial(product, twoTermsMultiplied, prevAddedTerm);
			}
			// add() product to sum
			//System.out.println("Product: " + product);
			sum = sum.add(product);
			//System.out.println("Sum: " + sum);
			product.poly = null; // reset the product line
		}
		// Return sum
		return sum;
	}
	
	/**
	 * Evaluates this polynomial at the given value of x
	 * 
	 * @param x Value at which this polynomial is to be evaluated
	 * @return Value of this polynomial at x
	 */
	public float evaluate(float x) {
		/** COMPLETE THIS METHOD **/
		// Start out with sum equal to 0
		float sum = 0;
		// Set multiplier equal to x (for the monomial encountered with degree of 1)
		float multiplier = x;
		
		// Go through this polynomial's entire LL
		for (Node currTermNode = this.poly; currTermNode != null; currTermNode = currTermNode.next) {
			// If the degree of the first term in the LL is 0
			if (currTermNode.term.degree == 0) {
				// Add the coefficient value to sum
				sum += currTermNode.term.coeff;
			}
			// Else, (have encountered a monomial)
			else {
				// Multiply multiplier with coeff of currTermNode and Store in product
				float product = multiplier * currTermNode.term.coeff;
				// Add product to sum
				sum += product;
				// Update multiplier to be a value of itself raised to the power of the degree of the 
				//		next term
				if (currTermNode.next != null) { // if haven't reached the last monomial in this polynomial
					multiplier = (float) Math.pow(x, currTermNode.next.term.degree);
				}
			}
		}
		// Return sum
		return sum;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String retval;
		
		if (poly == null) {
			return "0";
		} else {
			retval = poly.term.toString();
			for (Node current = poly.next ;
			current != null ;
			current = current.next) {
				retval = current.term.toString() + " + " + retval;
			}
			return retval;
		}
	}
}
