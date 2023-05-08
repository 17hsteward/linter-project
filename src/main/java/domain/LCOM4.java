package domain;

/**
 * LCOM4 is the lack of cohesion metric we recommend for Visual Basic programs.
 * LCOM4 measures the number of "connected components" in a class. A connected
 * component is a set of related methods (and class-level variables). There
 * should be only one such a component in each class. If there are 2 or more
 * components, the class should be split into so many smaller classes.
 *
 * Which methods are related? Methods A and B are related if:
 *
 * they both access the same class-level variable, or
 * A calls B or vice versa.
 * After determining the related methods, we draw a graph linking the related
 * methods to each other. LCOM4 equals the number of connected groups of methods.
 *
 * LCOM4=1 indicates a cohesive class, which is the "good" class.
 * LCOM4>=2 indicates a problem. The class should be split into so many smaller classes.
 * LCOM4=0 happens when there are no methods in a class. This is also a "bad" class.
 */
public class LCOM4 extends CohesionMetric{

}
