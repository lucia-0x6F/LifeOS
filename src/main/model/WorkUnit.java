package model;

public interface WorkUnit {
    String getName();

    void setName(String name);

    boolean getCompleteStatus();

    void markAsCompleted();
    
    void markAsUncompleted();
}
