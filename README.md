# My Personal Project - LifeOS

## Proposal
### *What will the application do?*

The **LifeOS** app's basic function is to break down goals and track progress, which helps users convert a big, long-term goal into more specific and executable steps, and understand their own progress through the visual interface. Most people have a high confidence level when they write down a goal, but when they are at the execution phase, it's easy for them to be interrupted by external factors and end up quitting. This app combines the **Long-term Module** and a **Short-term Module** to make the whole execution process clearer and give users a sense of control.

- The **Long-term Module** is used to ***show the progress of each long-term goal, minor step, or phase***.
- The **Short-term Module** is used to ***turn the long-term goals into smaller weekly tasks*** and include them in the **weekly task list** and **weekly schedule**.

## TODO
1. MainFrame is a class that has too much functionality, so it would be more appropriate to make its cohesion higher and coupling lower by introducing more classes such as LongTermPanel, ShortTermPanel, GoalPanel and TaskPanel. 

2. LongTerm and ShortTerm could extend Observable and MainFrame could implement Observer, so that when there are changes in LongTerm or ShortTerm, MainFrame will automatically update the GUI, and the manual update methods would no longer be needed.