package domain;

/**
 * Created by researchers Norman Fenton and Austin Melton, the Fenton and Melton metric is
 * commonly used to measure the degree of an architecture's coupling. The following formula
 * calculates the level of coupling, C, between components a and b:
 *
 * C(a, b) = i + n / (n + 1)
 *
 * In this formula, the variable n represents the actual number of direct dependencies that
 * exist between components a and b. The variable i will produce a value that indicates the
 * level of coupling that exists between a and b. Developers can determine i by examining
 * each of those components and identifying the tightest dependency relationship, with 0
 * representing the lowest level of dependency and 5 representing the highest.
 */
public class FentonAndMeltonMetric extends CouplingMetric{

}
