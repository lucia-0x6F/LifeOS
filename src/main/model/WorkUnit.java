package model;

public interface WorkUnit {
    void setName(String name);

    void markAsCompleted();
    
    void markAsUncompleted();
}
