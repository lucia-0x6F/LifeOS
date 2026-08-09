package model;

/**
 * A unit of work that has a name and completion status.
 */

public interface WorkUnit {
    void setName(String name);

    void setAsCompleted();
    
    void setAsUncompleted();
}
